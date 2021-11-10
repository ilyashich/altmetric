package com.example.api.controller;

import com.example.api.service.ScopusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ScopusController
{
    private final ScopusService scopusService;

    @GetMapping("/scopus_count")
    public String getCitationsByDoi()
    {
        return scopusService.getCitationsByDoi();
    }

}