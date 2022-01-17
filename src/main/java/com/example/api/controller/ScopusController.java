package com.example.api.controller;

import com.example.api.model.scopus.ScopusDto;
import com.example.api.service.ScopusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ScopusController
{
    private final ScopusService scopusService;

    @GetMapping("/scopus/{doiPrefix}/{doiSuffix}")
    public ScopusDto getCitationsByDoi(@PathVariable String doiPrefix, @PathVariable String doiSuffix)
    {
        String doi = doiPrefix + "/" + doiSuffix;

        return scopusService.getCitationsByDoi(doi);
    }

    @GetMapping("/scopus")
    public ScopusDto getCitationsByTitleAndAuthor(@RequestParam String title, @RequestParam String author)
    {
        return scopusService.getCitationsByTitleAndAuthor(title, author);
    }

}