package com.example.api.controller;

import com.example.api.model.scopus.ScopusDto;
import com.example.api.service.ScopusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

}