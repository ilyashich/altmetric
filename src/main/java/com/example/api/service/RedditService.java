package com.example.api.service;

import com.example.api.model.reddit.RedditDto;
import com.example.api.webclient.reddit.RedditClient;
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

    public RedditDto searchReddit(String query) throws JsonProcessingException
    {
        return redditClient.searchReddit(query);
    }
}
