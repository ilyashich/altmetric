package com.example.api.controller;


import com.example.api.model.wikipedia.Wikipedia;
import com.example.api.service.WikipediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class WikipediaController
{
    private final WikipediaService wikipediaService;

    @GetMapping("/wiki/doisearch")
    public Wikipedia getCitationsByDoi(@RequestParam String doi)
    {
        return wikipediaService.getCitations(doi);
    }

    @GetMapping("/wiki/titlesearch")
    public Wikipedia getCitationsByTitle(@RequestParam String title)
    {
        return wikipediaService.getCitations(title);
    }
}

//id: 10.1111/gcbb.12488
