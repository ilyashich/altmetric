package com.example.api.controller;

import com.example.api.model.youtube.Youtube;
import com.example.api.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class YoutubeController
{
    private final YoutubeService youtubeService;

    @GetMapping("/youtube/urlsearch")
    public Youtube searchYoutubeByUrl(@RequestParam String url)
    {
        return youtubeService.searchYoutubeByUrl(url);
    }

    @GetMapping("/youtube/titlesearch")
    public Youtube searchYoutubeByTitle(@RequestParam String title)
    {
        return youtubeService.searchYoutubeByTitle(title);
    }
}
