package com.example.api.service;

import com.example.api.model.crossref.Crossref;
import com.example.api.webclient.CrossrefClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CrossrefService
{
    private final CrossrefClient crossrefClient;

    public Crossref searchCrossrefByDoi(String doi)
    {
        return crossrefClient.searchCrossrefByDoi(doi);
    }

    public Crossref searchCrossrefByTitleAndAuthor(String title, String author)
    {
        return crossrefClient.searchCrossrefByTitleAndAuthor(title, author);
    }
}
