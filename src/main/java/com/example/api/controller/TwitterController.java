package com.example.api.controller;

import com.example.api.model.twitter.TwitterDto;
import com.example.api.service.TwitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TwitterController
{
    private final TwitterService twitterService;

    @GetMapping("/twitter/**")
    public TwitterDto searchTwitter(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.split("/twitter/")[1];
        return twitterService.searchTwitter(url);
    }
}

//https://academic.oup.com/cid/article/71/9/2311/5867798
