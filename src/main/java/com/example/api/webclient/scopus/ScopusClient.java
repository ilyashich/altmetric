package com.example.api.webclient.scopus;

import com.example.api.model.scopus.ScopusDto;
import com.example.api.webclient.scopus.dto.Link;
import com.example.api.webclient.scopus.dto.ScopusSearchDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Component
public class ScopusClient
{
    public final String SCOPUS_URL = "https://api.elsevier.com/content/search/scopus";
    public final String API_KEY = "XXXXXXX";
    public RestTemplate restTemplate = new RestTemplate();
    public HttpHeaders headers = new HttpHeaders();
    public HttpEntity<String> request;

    public ScopusDto getCitationsByDoi(String doi)
    {
        headers.set("X-ELS-APIKey", API_KEY);
        request = new HttpEntity<>(headers);
        ResponseEntity<ScopusSearchDto> response = restTemplate.exchange(SCOPUS_URL + "?query=DOI({doi})", HttpMethod.GET, request, ScopusSearchDto.class, doi);
        ScopusSearchDto scopusSearchDto = response.getBody();
        List<Link> links = new ArrayList<>();
        String citationsCount = "";
        if (scopusSearchDto != null)
        {
            links = scopusSearchDto.searchResults.entry.get(0).link;
            citationsCount = scopusSearchDto.searchResults.entry.get(0).citedbyCount;
        }
        Link selected = new Link();
        for(Link link : links)
        {
            if(link.ref.equals("scopus-citedby"))
            {
                selected = link;
            }
        }
        return ScopusDto.builder()
                .citationsCount(citationsCount)
                .link(selected.href)
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(SCOPUS_URL + url, responseType, objects);
    }

    private <T> ResponseEntity<T> callPostMethod(String url, HttpEntity<String> request, Class<T> responseType, Object... objects)
    {
        return restTemplate.postForEntity(url, request, responseType, objects);
    }

}

