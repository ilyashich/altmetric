package com.example.api.webclient.crossref;

import com.example.api.model.crossref.CrossrefAuthorsDto;
import com.example.api.model.crossref.CrossrefDto;
import com.example.api.webclient.crossref.dto.CrossrefAuthorDto;
import com.example.api.webclient.crossref.dto.CrossrefSearchDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrossrefClient
{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String CROSSREF_API_URL = "https://api.crossref.org/works/";

    public CrossrefDto searchCrossref(String doi)
    {
        CrossrefSearchDto response = callGetMethod("q={link}", CrossrefSearchDto.class, doi);
        List<CrossrefAuthorsDto> authors = new ArrayList<>();
        for(CrossrefAuthorDto author : response.message.author)
        {
            authors.add(CrossrefAuthorsDto.builder()
                    .name(author.given + " " + author.family)
                    .build());
        }
        String published = response.message.published.dateParts.get(0).get(1).toString() + "-1-" +
                response.message.published.dateParts.get(0).get(0).toString();

        return CrossrefDto.builder()
                .referencedByCount(response.message.isReferencedByCount)
                .issue(response.message.issue)
                .volume(response.message.volume)
                .page(response.message.page)
                .publisher(response.message.publisher)
                .authors(authors)
                .issn(response.message.issn)
                .source(response.message.containerTitle.get(0))
                .title(response.message.title.get(0))
                .published(published)
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(CROSSREF_API_URL + url, responseType, objects);
    }
}
