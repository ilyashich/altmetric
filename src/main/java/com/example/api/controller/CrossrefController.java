package com.example.api.controller;

import com.example.api.model.crossref.Crossref;
import com.example.api.service.CrossrefService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CrossrefController
{
    private final CrossrefService crossrefService;

    @GetMapping("/crossref/doisearch")
    public Crossref searchCrossrefByDoi(@RequestParam String doi)
    {
        return crossrefService.searchCrossrefByDoi(doi);
    }

    @GetMapping("/crossref/titlesearch")
    public Crossref searchCrossrefByTitleAndAuthor(@RequestParam String title, @RequestParam String author)
    {
        return crossrefService.searchCrossrefByTitleAndAuthor(title, author);
    }
}
