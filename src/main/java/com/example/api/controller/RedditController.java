package com.example.api.controller;

import com.example.api.model.reddit.RedditDto;
import com.example.api.service.RedditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RedditController
{
    private final RedditService redditService;

    @GetMapping("/reddit/url/**")
    public RedditDto searchRedditByUrl(HttpServletRequest request) throws JsonProcessingException
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
        String url = requestURL.split("/reddit/url/")[1];
        return redditService.searchRedditByUrl(url);
    }

    @GetMapping("/reddit/title/**")
    public RedditDto searchRedditByTitle(HttpServletRequest request) throws JsonProcessingException
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
        String encoded = requestURL.split("/reddit/title/")[1];
        String title = URLDecoder.decode(encoded, StandardCharsets.UTF_8);
        return redditService.searchRedditByTitle(title);
    }
}

//https://www.nature.com/articles/s41591-020-0820-9
