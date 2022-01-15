package com.example.api.service;

import com.example.api.model.crossref.CrossrefDto;
import com.example.api.webclient.crossref.CrossrefClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CrossrefService
{
    private final CrossrefClient crossrefClient;

    public CrossrefDto searchCrossrefByDoi(String doi)
    {
        return crossrefClient.searchCrossrefByDoi(doi);
    }

    public CrossrefDto searchCrossrefByTitle(String title)
    {
        return crossrefClient.searchCrossrefByTitle(title);
    }
}
