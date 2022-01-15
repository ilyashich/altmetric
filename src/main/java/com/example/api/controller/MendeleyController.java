package com.example.api.controller;


import com.example.api.model.mendeley.MendeleyDto;
import com.example.api.service.MendeleyService;
import com.example.api.webclient.mendeley.dto.MendeleyCatalogDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MendeleyController
{
    private final MendeleyService mendeleyService;

    @GetMapping("/mendeley/catalog/{doiPrefix}/{doiSuffix}")
    public MendeleyDto getCatalog(@PathVariable String doiPrefix, @PathVariable String doiSuffix) throws JsonProcessingException
    {
        String doi = doiPrefix + "/" + doiSuffix;

        return mendeleyService.getCatalog(doi);
    }

    @GetMapping("/mendeley/title/**")
    public MendeleyDto searchCatalogByTitle(HttpServletRequest request) throws JsonProcessingException
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
        String encoded = requestURL.split("/mendeley/title/")[1];
        String title = URLDecoder.decode(encoded, StandardCharsets.UTF_8);
        return mendeleyService.searchCatalogByTitle(title);
    }

    @GetMapping("/mendeley/doi/{doiPrefix}/{doiSuffix}")
    public String getReadersByDoi(@PathVariable String doiPrefix, @PathVariable String doiSuffix)
    {
        String doi = doiPrefix + "/" + doiSuffix;

        return mendeleyService.getReadersByDoi(doi);
    }

    @GetMapping("/mendeley/scopus_id/**")
    public String getReadersByScopusId(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        String scopusId = requestURL.split("/mendeley/scopus_id/")[1];
        return mendeleyService.getReadersByScopusId(scopusId);
    }

}

//doi: 10.1007/s10844-015-0393-0
//scopus: 2-s2.0-84954552432
