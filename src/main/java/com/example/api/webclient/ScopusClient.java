package com.example.api.webclient;

import com.example.api.model.scopus.Scopus;
import com.example.api.dto.scopus.ScopusLinkDto;
import com.example.api.dto.scopus.ScopusSearchDto;
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

    public Scopus getCitationsByDoi(String doi)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-ELS-APIKey", API_KEY);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<ScopusSearchDto> response = callExchangeMethod(
                "?query=DOI({doi})",
                HttpMethod.GET,
                request,
                ScopusSearchDto.class,
                doi
        );
        return getScopusDto(response);
    }

    public Scopus getCitationsByTitleAndAuthor(String title, String author)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-ELS-APIKey", API_KEY);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<ScopusSearchDto> response = callExchangeMethod(
                "?query=TITLE(\"{title}\") AND AUTHOR-NAME({author})",
                HttpMethod.GET,
                request,
                ScopusSearchDto.class,
                title,
                author
        );
        return getScopusDto(response);
    }

    private Scopus getScopusDto(ResponseEntity<ScopusSearchDto> response)
    {
        ScopusSearchDto scopusSearchDto = response.getBody();
        List<ScopusLinkDto> links = new ArrayList<>();
        String citationsCount = "0";
        if (scopusSearchDto != null)
        {
            links = scopusSearchDto.searchResults.entry.get(0).link;
            if(scopusSearchDto.searchResults.entry.get(0).citedbyCount != null)
            {
                citationsCount = scopusSearchDto.searchResults.entry.get(0).citedbyCount;
            }

        }
        String selected = null;
        if(links != null)
        {
            for (ScopusLinkDto link : links)
            {
                if (link.ref.equals("scopus-citedby"))
                {
                    selected = link.href;
                }
            }
        }
        return Scopus.builder()
                .citationsCount(Integer.parseInt(citationsCount))
                .link(selected)
                .build();
    }

    private <T> ResponseEntity<T> callExchangeMethod(String url, HttpMethod method, HttpEntity<String> request, Class<T> responseType, Object... objects)
    {
        return restTemplate.exchange(
                SCOPUS_URL + url,
                method,
                request,
                responseType,
                objects
        );
    }
}

