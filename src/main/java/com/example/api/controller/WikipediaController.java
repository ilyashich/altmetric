package com.example.api.controller;


import com.example.api.model.wikipedia.WikipediaDto;
import com.example.api.service.WikipediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class WikipediaController
{
    private final WikipediaService wikipediaService;

    @GetMapping("/wiki/**")
    public WikipediaDto getCitationsByDoi(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.split("/wiki/")[1];
        return wikipediaService.getCitationsById(url);
    }
}

//id: 10.1111/gcbb.12488
