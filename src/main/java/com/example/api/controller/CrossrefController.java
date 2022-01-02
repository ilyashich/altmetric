package com.example.api.controller;

import com.example.api.model.crossref.CrossrefDto;
import com.example.api.service.CrossrefService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class CrossrefController
{
    private final CrossrefService crossrefService;

    @GetMapping("/crossref/**")
    public CrossrefDto searchTwitter(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        String doi = requestURL.split("/crossref/")[1];
        return crossrefService.searchCrossref(doi);
    }
}
