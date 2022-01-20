package com.example.api.controller;

import com.example.api.model.reddit.Reddit;
import com.example.api.service.RedditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RedditController
{
    private final RedditService redditService;

    @GetMapping("/reddit/urlsearch")
    public Reddit searchRedditByUrl(@RequestParam String url) throws JsonProcessingException
    {
        return redditService.searchRedditByUrl(url);
    }

    @GetMapping("/reddit/titlesearch")
    public Reddit searchRedditByTitle(@RequestParam String title) throws JsonProcessingException
    {
        return redditService.searchRedditByTitle(title);
    }
}

//https://www.nature.com/articles/s41591-020-0820-9
