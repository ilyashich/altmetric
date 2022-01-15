package com.example.api.service;

import com.example.api.model.stackexchange.StackExchangeDto;
import com.example.api.webclient.stackexchange.StackExchangeClient;
import com.example.api.webclient.stackexchange.dto.StackExchangeSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StackExchangeService
{
    private final StackExchangeClient stackExchangeClient;

    public StackExchangeDto searchStackExchangeByUrl(String url)
    {
        return stackExchangeClient.searchStackExchangeByUrl(url);
    }

    public StackExchangeDto searchStackExchangeByTitle(String title)
    {
        return stackExchangeClient.searchStackExchangeByTitle(title);
    }
}
