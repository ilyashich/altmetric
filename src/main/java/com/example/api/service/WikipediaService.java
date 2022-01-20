package com.example.api.service;

import com.example.api.model.wikipedia.Wikipedia;
import com.example.api.webclient.WikipediaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WikipediaService
{
    private final WikipediaClient wikipediaClient;

    public Wikipedia getCitations(String doi)
    {
        return wikipediaClient.getCitations(doi);
    }
}
