package com.example.api.service;

import com.example.api.model.reddit.Reddit;
import com.example.api.webclient.RedditClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedditService
{
    private final RedditClient redditClient;

    public Reddit searchRedditByUrl(String url) throws JsonProcessingException
    {
        return redditClient.searchRedditByUrl(url);
    }

    public Reddit searchRedditByTitle(String title) throws JsonProcessingException
    {
        return redditClient.searchRedditByTitle(title);
    }
}
