package com.example.api.controller;

import com.example.api.model.twitter.Twitter;
import com.example.api.service.TwitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TwitterController
{
    private final TwitterService twitterService;

    @GetMapping("/twitter/urlsearch")
    public Twitter searchTwitterByUrl(@RequestParam String url)
    {
        return twitterService.searchTwitterByUrl(url);
    }

    @GetMapping("/twitter/titlesearch")
    public Twitter searchTwitterByTitle(@RequestParam String title)
    {
        return twitterService.searchTwitterByTitle(title);
    }
}

//https://academic.oup.com/cid/article/71/9/2311/5867798
