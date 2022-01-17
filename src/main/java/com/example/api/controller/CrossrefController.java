package com.example.api.controller;

import com.example.api.model.crossref.CrossrefDto;
import com.example.api.service.CrossrefService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CrossrefController
{
    private final CrossrefService crossrefService;

    @GetMapping("/crossref/doi/{doiPrefix}/{doiSuffix}")
    public CrossrefDto searchCrossrefByDoi(@PathVariable String doiPrefix, @PathVariable String doiSuffix)
    {
        String doi = doiPrefix + "/" + doiSuffix;

        return crossrefService.searchCrossrefByDoi(doi);
    }

    @GetMapping("/crossref")
    public CrossrefDto searchCrossrefByTitleAndAuthor(@RequestParam String title, @RequestParam String author)
    {
        return crossrefService.searchCrossrefByTitleAndAuthor(title, author);
    }
}
