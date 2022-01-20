package com.example.api.webclient;

import com.example.api.model.wikipedia.WikipediaArticle;
import com.example.api.model.wikipedia.Wikipedia;
import com.example.api.dto.wikipedia.WikipediaResultDto;
import com.example.api.dto.wikipedia.WikipediaSearchResultDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class WikipediaClient
{
    public final String API_URL = "https://en.wikipedia.org/w/api.php";
    public RestTemplate restTemplate = new RestTemplate();

    public Wikipedia getCitations(String query)
    {
        WikipediaResultDto wikipediaResultDto = callGetMethod("?srsearch=\"{id}\"&action=query&list=search&utf8=&format=json&srlimit=100", WikipediaResultDto.class, query);

        List<WikipediaArticle> search = new ArrayList<>();
        for(WikipediaSearchResultDto wikipediaSearchResultDto : wikipediaResultDto.getQuery().getSearch())
        {

            search.add(WikipediaArticle.builder()
                    .pageId(wikipediaSearchResultDto.getPageid())
                    .title(wikipediaSearchResultDto.getTitle())
                    .build());

        }

        return Wikipedia.builder()
                .totalHits(wikipediaResultDto.getQuery().getSearchinfo().getTotalhits())
                .citationInfo(search)
                .build();

    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(API_URL + url, responseType, objects);
    }

}
