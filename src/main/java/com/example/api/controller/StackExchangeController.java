package com.example.api.controller;

import com.example.api.model.stackexchange.StackExchange;
import com.example.api.service.StackExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StackExchangeController
{
    private final StackExchangeService stackExchangeService;

    @GetMapping("/stackexchange/urlsearch")
    public StackExchange searchStackExchangeByUrl(@RequestParam String url)
    {
        return stackExchangeService.searchStackExchangeByUrl(url);
    }

    @GetMapping("/stackexchange/titlesearch")
    public StackExchange searchStackExchangeByTitle(@RequestParam String title)
    {
        return stackExchangeService.searchStackExchangeByTitle(title);
    }
}
