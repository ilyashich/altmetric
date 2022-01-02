package com.example.api.service;

import com.example.api.model.wikipedia.WikipediaDto;
import com.example.api.webclient.wikipedia.WikipediaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WikipediaService
{
    private final WikipediaClient wikipediaClient;

    public WikipediaDto getCitationsById(String doi)
    {
        return wikipediaClient.getCitationsById(doi);
    }
}
