package com.example.api.controller;

import com.example.api.model.youtube.YoutubeDto;
import com.example.api.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class YoutubeController
{
    private final YoutubeService youtubeService;

    @GetMapping("/youtube/url/**")
    public YoutubeDto searchYoutubeByUrl(HttpServletRequest request)
    {
        String requestURL;
        if(request.getQueryString() == null)
        {
            requestURL = request.getRequestURL().toString();
        }
        else
        {
            requestURL = request.getRequestURL().append('?').append(request.getQueryString()).toString();
        }
        String url = requestURL.split("/youtube/url/")[1];
        return youtubeService.searchYoutubeByUrl(url);
    }

    @GetMapping("/youtube/title/**")
    public YoutubeDto searchYoutubeByTitle(HttpServletRequest request)
    {
        String requestURL;
        if(request.getQueryString() == null)
        {
            requestURL = request.getRequestURL().toString();
        }
        else
        {
            requestURL = request.getRequestURL().append('?').append(request.getQueryString()).toString();
        }
        String encoded = requestURL.split("/youtube/title/")[1];
        String title = URLDecoder.decode(encoded, StandardCharsets.UTF_8);
        return youtubeService.searchYoutubeByTitle(title);
    }
}
