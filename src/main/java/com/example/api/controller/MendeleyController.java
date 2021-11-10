package com.example.api.controller;


import com.example.api.model.mendeley.MendeleyDto;
import com.example.api.service.MendeleyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class MendeleyController
{
    private final MendeleyService mendeleyService;

    @GetMapping("/mendeley/catalog/**")
    public MendeleyDto getCatalog(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        String query = requestURL.split("/mendeley/catalog/")[1];
        return mendeleyService.getCatalog(query);
    }

    @GetMapping("/mendeley/doi/**")
    public String getReadersByDoi(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        String query = requestURL.split("/mendeley/doi/")[1];
        return mendeleyService.getReadersByDoi(query);
    }

    @GetMapping("/mendeley/scopus_id/**")
    public String getReadersByScopusId(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        String query = requestURL.split("/mendeley/scopus_id/")[1];
        return mendeleyService.getReadersByScopusId(query);
    }

}

//doi: 10.1007/s10844-015-0393-0
//scopus: 2-s2.0-84954552432
