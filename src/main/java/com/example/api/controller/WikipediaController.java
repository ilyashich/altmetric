package com.example.api.controller;


import com.example.api.model.wikipedia.WikipediaDto;
import com.example.api.service.WikipediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class WikipediaController
{
    private final WikipediaService wikipediaService;

    @GetMapping("/wiki/{doiPrefix}/{doiSuffix}")
    public WikipediaDto getCitationsByDoi(@PathVariable String doiPrefix, @PathVariable String doiSuffix)
    {
        String doi = doiPrefix + "/" + doiSuffix;

        return wikipediaService.getCitationsById(doi);
    }
}

//id: 10.1111/gcbb.12488
