package com.example.api.service;

import com.example.api.model.scopus.Scopus;
import com.example.api.webclient.ScopusClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScopusService
{
    private final ScopusClient scopusClient;

    public Scopus getCitationsByDoi(String doi)
    {
        return scopusClient.getCitationsByDoi(doi);
    }

    public Scopus getCitationsByTitleAndAuthor(String title, String author)
    {
        return scopusClient.getCitationsByTitleAndAuthor(title, author);
    }
}