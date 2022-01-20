package com.example.api.controller;

import com.example.api.model.scopus.Scopus;
import com.example.api.service.ScopusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ScopusController
{
    private final ScopusService scopusService;

    @GetMapping("/scopus/doisearch")
    public Scopus getCitationsByDoi(@RequestParam String doi)
    {
        return scopusService.getCitationsByDoi(doi);
    }

    @GetMapping("/scopus/titlesearch")
    public Scopus getCitationsByTitleAndAuthor(@RequestParam String title, @RequestParam String author)
    {
        return scopusService.getCitationsByTitleAndAuthor(title, author);
    }

}