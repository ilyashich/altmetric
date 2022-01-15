package com.example.api.webclient.wikipedia;

import com.example.api.model.wikipedia.WikipediaArticleDto;
import com.example.api.model.wikipedia.WikipediaDto;
import com.example.api.webclient.wikipedia.dto.WikipediaResultDto;
import com.example.api.webclient.wikipedia.dto.WikipediaSearchResultDto;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class WikipediaClient
{
    public final String API_URL = "https://en.wikipedia.org/w/api.php";
    public RestTemplate restTemplate = new RestTemplate();

    public WikipediaDto getCitations(String query)
    {
        WikipediaResultDto wikipediaResultDto = callGetMethod("?srsearch=\"{id}\"&action=query&list=search&utf8=&format=json&srlimit=100", WikipediaResultDto.class, query);

        List<WikipediaArticleDto> search = new ArrayList<>();
        for(WikipediaSearchResultDto wikipediaSearchResultDto : wikipediaResultDto.getQuery().getSearch())
        {

            search.add(WikipediaArticleDto.builder()
                    .pageId(wikipediaSearchResultDto.getPageid())
                    .title(wikipediaSearchResultDto.getTitle())
                    .build());

        }

        return WikipediaDto.builder()
                .totalHits(String.valueOf(search.size()))
                .citationInfo(search)
                .build();

    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(API_URL + url, responseType, objects);
    }

}
