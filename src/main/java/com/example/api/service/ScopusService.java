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

    public ScopusDto getCitationsByDoi(String query)
    {
        return scopusClient.getCitationsByDoi(query);
    }
}