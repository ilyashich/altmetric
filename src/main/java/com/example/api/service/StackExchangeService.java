package com.example.api.service;

import com.example.api.model.stackexchange.StackExchange;
import com.example.api.webclient.StackExchangeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class StackExchangeService
{
    private final StackExchangeClient stackExchangeClient;

    public StackExchange searchStackExchangeByUrl(String url)
    {
        return stackExchangeClient.searchStackExchangeByUrl(url);
    }

    public StackExchange searchStackExchangeByTitle(String title)
    {
        return stackExchangeClient.searchStackExchangeByTitle(title);
    }
}
