package com.example.api.controller;

import com.example.api.model.reddit.RedditDto;
import com.example.api.service.RedditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class RedditController
{
    private final RedditService redditService;

    @GetMapping("/reddit/**")
    public RedditDto searchReddit(HttpServletRequest request) throws JsonProcessingException
    {
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.split("/reddit/")[1];
        return redditService.searchReddit(url);
    }
}

//https://www.nature.com/articles/s41591-020-0820-9
