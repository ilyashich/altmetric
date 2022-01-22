package com.example.api.webclient;

import com.example.api.model.crossref.CrossrefAuthors;
import com.example.api.model.crossref.Crossref;
import com.example.api.dto.crossref.bydoi.CrossrefAuthorDto;
import com.example.api.dto.crossref.bydoi.CrossrefSearchDto;
import com.example.api.dto.crossref.bytitle.CrossrefByTitleAuthorDto;
import com.example.api.dto.crossref.bytitle.CrossrefByTitleSearchDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrossrefClient
{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String CROSSREF_API_URL = "https://api.crossref.org/works/";

    public Crossref searchCrossrefByDoi(String doi)
    {
        CrossrefSearchDto response = callGetMethod("q={link}", CrossrefSearchDto.class, doi);

        List<CrossrefAuthors> authors = new ArrayList<>();
        for(CrossrefAuthorDto author : response.message.author)
        {
            authors.add(CrossrefAuthors.builder()
                    .name(author.given + " " + author.family)
                    .build());
        }
        String published = response.message.published.dateParts.get(0).get(1).toString() + "-1-" +
                response.message.published.dateParts.get(0).get(0).toString();

        return Crossref.builder()
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

    public Crossref searchCrossrefByTitleAndAuthor(String title, String authorName)
    {
        CrossrefByTitleSearchDto response = callGetMethod(
                "?query.title={name}&query.author={author}&rows=1",
                CrossrefByTitleSearchDto.class,
                title,
                authorName
        );

        List<CrossrefAuthors> authors = new ArrayList<>();
        for(CrossrefByTitleAuthorDto author : response.message.items.get(0).author)
        {
            authors.add(CrossrefAuthors.builder()
                    .name(author.given + " " + author.family)
                    .build());
        }
        String published = response.message.items.get(0).published.dateParts.get(0).get(1).toString() + "-1-" +
                response.message.items.get(0).published.dateParts.get(0).get(0).toString();

        return Crossref.builder()
                .referencedByCount(response.message.items.get(0).isReferencedByCount)
                .issue(response.message.items.get(0).issue)
                .volume(response.message.items.get(0).volume)
                .page(response.message.items.get(0).page)
                .publisher(response.message.items.get(0).publisher)
                .authors(authors)
                .issn(response.message.items.get(0).issn)
                .source(response.message.items.get(0).containerTitle.get(0))
                .title(response.message.items.get(0).title.get(0))
                .doi(response.message.items.get(0).doi)
                .published(published)
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(CROSSREF_API_URL + url, responseType, objects);
    }
}
