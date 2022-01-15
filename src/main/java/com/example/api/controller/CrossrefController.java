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

    @GetMapping("/crossref/title/**")
    public CrossrefDto searchCrossrefByTitle(HttpServletRequest request)
    {
        String requestURL;
        if(request.getQueryString() == null)
        {
            requestURL = request.getRequestURL().toString();
        }
        else
        {
            requestURL = request.getRequestURL().append('?').append(request.getQueryString()).toString();
        }
        String encoded = requestURL.split("/crossref/title/")[1];
        String title = URLDecoder.decode(encoded, StandardCharsets.UTF_8);
        return crossrefService.searchCrossrefByTitle(title);
    }
}
