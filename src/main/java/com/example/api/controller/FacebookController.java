package com.example.api.controller;

import com.example.api.model.facebook.Facebook;
import com.example.api.service.FacebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FacebookController
{
    private final FacebookService facebookService;

    @GetMapping("/facebook/urlsearch")
    public Facebook searchFacebook(@RequestParam String url)
    {
        return facebookService.searchFacebook(url);
    }
}
