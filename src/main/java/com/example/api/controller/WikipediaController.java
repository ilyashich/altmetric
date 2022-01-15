package com.example.api.controller;


import com.example.api.model.wikipedia.WikipediaDto;
import com.example.api.service.WikipediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class WikipediaController
{
    private final WikipediaService wikipediaService;

    @GetMapping("/wiki/doi/{doiPrefix}/{doiSuffix}")
    public WikipediaDto getCitationsByDoi(@PathVariable String doiPrefix, @PathVariable String doiSuffix)
    {
        String doi = doiPrefix + "/" + doiSuffix;

        return wikipediaService.getCitations(doi);
    }

    @GetMapping("/wiki/title/**")
    public WikipediaDto getCitationsByTitle(HttpServletRequest request)
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
        String encoded = requestURL.split("/wiki/title/")[1];
        String title = URLDecoder.decode(encoded, StandardCharsets.UTF_8);
        return wikipediaService.getCitations(title);
    }
}

//id: 10.1111/gcbb.12488
