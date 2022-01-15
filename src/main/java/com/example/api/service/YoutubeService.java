package com.example.api.service;


import com.example.api.model.youtube.YoutubeDto;
import com.example.api.webclient.youtube.YoutubeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class YoutubeService
{
    private final YoutubeClient youtubeClient;

    public YoutubeDto searchYoutubeByUrl(String url)
    {
        return youtubeClient.searchYoutubeByUrl(url);
    }

    public YoutubeDto searchYoutubeByTitle(String title)
    {
        return youtubeClient.searchYoutubeByTitle(title);
    }
}
