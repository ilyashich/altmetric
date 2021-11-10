package com.example.api.service;

import com.example.api.model.twitter.TwitterDto;
import com.example.api.webclient.twitter.TwitterClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TwitterService
{
    private final TwitterClient twitterClient;

    public TwitterDto searchTwitter(String query)
    {
        return twitterClient.searchTwitter(query);
    }
}
