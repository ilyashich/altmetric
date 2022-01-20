package com.example.api.service;


import com.example.api.model.youtube.Youtube;
import com.example.api.webclient.YoutubeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class YoutubeService
{
    private final YoutubeClient youtubeClient;

    public Youtube searchYoutubeByUrl(String url)
    {
        return youtubeClient.searchYoutubeByUrl(url);
    }

    public Youtube searchYoutubeByTitle(String title)
    {
        return youtubeClient.searchYoutubeByTitle(title);
    }
}
