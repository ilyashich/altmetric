package com.example.api.service;

import com.example.api.model.scopus.ScopusDto;
import com.example.api.webclient.scopus.ScopusClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScopusService
{
    private final ScopusClient scopusClient;

    public ScopusDto getCitationsByDoi(String doi)
    {
        return scopusClient.getCitationsByDoi(doi);
    }

    public ScopusDto getCitationsByTitleAndAuthor(String title, String author)
    {
        return scopusClient.getCitationsByTitleAndAuthor(title, author);
    }
}