package com.example.api.service;

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

    public String getCitationsByDoi()
    {
        return scopusClient.getCitationsByDoi("10.1007/s10844-015-0393-0");
    }
}