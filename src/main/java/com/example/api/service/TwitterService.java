package com.example.api.service;

import com.example.api.model.twitter.Twitter;
import com.example.api.webclient.TwitterClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TwitterService
{
    private final TwitterClient twitterClient;

    public Twitter searchTwitterByUrl(String url)
    {
        return twitterClient.searchTwitterByUrl(url);
    }

    public Twitter searchTwitterByTitle(String title)
    {
        return twitterClient.searchTwitterByTitle(title);
    }
}
