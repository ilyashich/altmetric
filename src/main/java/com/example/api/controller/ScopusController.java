package com.example.api.controller;

import com.example.api.model.scopus.ScopusDto;
import com.example.api.service.ScopusService;
import com.example.api.webclient.scopus.dto.ScopusSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class ScopusController
{
    private final ScopusService scopusService;

    @GetMapping("/scopus/**")
    public ScopusDto getCitationsByDoi(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        String doi = requestURL.split("/scopus/")[1];
        return scopusService.getCitationsByDoi(doi);
    }

}