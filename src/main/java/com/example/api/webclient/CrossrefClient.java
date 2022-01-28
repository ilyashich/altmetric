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
        for(CrossrefAuthorDto author : response.getMessage().getAuthor())
        {
            authors.add(CrossrefAuthors.builder()
                    .name(author.getGiven())
                    .surname(author.getFamily())
                    .build());
        }

        return Crossref.builder()
                .referencedByCount(response.getMessage().getIsReferencedByCount())
                .issue(response.getMessage().getIssue())
                .volume(response.getMessage().getVolume())
                .page(response.getMessage().getPage())
                .publisher(response.getMessage().getPublisher())
                .authors(authors)
                .issn(response.getMessage().getIssn())
                .source(response.getMessage().getContainerTitle().get(0))
                .title(response.getMessage().getTitle().get(0))
                .published(response.getMessage().getCreated().getDateTime())
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
        if(response.getMessage().getItems().size() > 0)
        {
            List<CrossrefAuthors> authors = new ArrayList<>();
            for (CrossrefByTitleAuthorDto author : response.getMessage().getItems().get(0).getAuthor())
            {
                authors.add(CrossrefAuthors.builder()
                        .name(author.getGiven())
                        .surname(author.getFamily())
                        .build());
            }

            return Crossref.builder()
                    .referencedByCount(response.getMessage().getItems().get(0).getIsReferencedByCount())
                    .issue(response.getMessage().getItems().get(0).getIssue())
                    .volume(response.getMessage().getItems().get(0).getVolume())
                    .page(response.getMessage().getItems().get(0).getPage())
                    .publisher(response.getMessage().getItems().get(0).getPublisher())
                    .authors(authors)
                    .issn(response.getMessage().getItems().get(0).getIssn())
                    .source(response.getMessage().getItems().get(0).getContainerTitle().get(0))
                    .title(response.getMessage().getItems().get(0).getTitle().get(0))
                    .doi(response.getMessage().getItems().get(0).getDoi())
                    .published(response.getMessage().getItems().get(0).getCreated().getDateTime())
                    .build();
        }
        return Crossref.builder().build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(CROSSREF_API_URL + url, responseType, objects);
    }
}
