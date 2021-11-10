package com.example.api.controller;

import com.example.api.model.youtube.YoutubeDto;
import com.example.api.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class YoutubeController
{
    private final YoutubeService youtubeService;

    @GetMapping("/youtube/**")
    public YoutubeDto searchYoutube(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        String query = requestURL.split("/youtube/")[1];
        return youtubeService.searchYoutube(query);
    }
}
