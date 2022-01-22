package com.example.api.controller;


import com.example.api.model.mendeley.Mendeley;
import com.example.api.service.MendeleyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MendeleyController
{
    private final MendeleyService mendeleyService;

    @GetMapping("/mendeley/doisearch")
    public Mendeley searchCatalogByDoi(@RequestParam String doi) throws JsonProcessingException
    {
        return mendeleyService.searchCatalogByDoi(doi);
    }

    @GetMapping("/mendeley/titlesearch")
    public Mendeley searchCatalogByTitleAndAuthor(@RequestParam String title, @RequestParam String author) throws JsonProcessingException
    {
        return mendeleyService.searchCatalogByTitleAndAuthor(title, author);
    }
}

//doi: 10.1007/s10844-015-0393-0
//scopus: 2-s2.0-84954552432
