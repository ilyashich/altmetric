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
        return getScopusDto(response);
    }

    public ScopusDto getCitationsByTitleAndAuthor(String title, String author)
    {
        headers.set("X-ELS-APIKey", API_KEY);
        request = new HttpEntity<>(headers);
        ResponseEntity<ScopusSearchDto> response = restTemplate.exchange(SCOPUS_URL + "?query=TITLE(\"{title}\") AND AUTHOR-NAME({author})", HttpMethod.GET, request, ScopusSearchDto.class, title, author);
        return getScopusDto(response);
    }

    private ScopusDto getScopusDto(ResponseEntity<ScopusSearchDto> response)
    {
        ScopusSearchDto scopusSearchDto = response.getBody();
        List<Link> links = new ArrayList<>();
        String citationsCount = "0";
        if (scopusSearchDto != null)
        {
            links = scopusSearchDto.searchResults.entry.get(0).link;
            citationsCount = scopusSearchDto.searchResults.entry.get(0).citedbyCount;
        }
        String selected = null;
        if(links != null)
        {
            for (Link link : links)
            {
                if (link.ref.equals("scopus-citedby"))
                {
                    selected = link.href;
                }
            }
        }
        return ScopusDto.builder()
                .citationsCount(citationsCount)
                .link(selected)
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

