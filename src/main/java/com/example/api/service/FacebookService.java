package com.example.api.service;

import com.example.api.model.facebook.Facebook;
import com.example.api.webclient.FacebookClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FacebookService
{
    private final FacebookClient facebookClient;

    public Facebook searchFacebook(String url)
    {
        return facebookClient.searchFacebook(url);
    }
}
