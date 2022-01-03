package com.example.api.controller;

import com.example.api.model.crossref.CrossrefDto;
import com.example.api.service.CrossrefService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CrossrefController
{
    private final CrossrefService crossrefService;

    @GetMapping("/crossref/{doiPrefix}/{doiSuffix}")
    public CrossrefDto searchTwitter(@PathVariable String doiPrefix, @PathVariable String doiSuffix)
    {
        String doi = doiPrefix + "/" + doiSuffix;

        return crossrefService.searchCrossref(doi);
    }
}
