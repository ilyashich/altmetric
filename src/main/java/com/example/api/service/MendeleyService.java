package com.example.api.service;


import com.example.api.model.mendeley.MendeleyDto;
import com.example.api.webclient.mendeley.MendeleyClient;
import com.example.api.webclient.mendeley.dto.MendeleyCatalogDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MendeleyService
{
    private final MendeleyClient mendeleyClient;

    public MendeleyDto getCatalog(String doi)
    {
        return mendeleyClient.getCatalog(doi);
    }

    public MendeleyDto searchCatalogByTitle(String title) throws JsonProcessingException
    {
        return mendeleyClient.searchCatalogByTitle(title);
    }

    public String getReadersByDoi(String doi)
    {
        return mendeleyClient.getReadersByDoi(doi);
    }

    public String getReadersByScopusId(String scopusId)
    {
        return mendeleyClient.getReadersByScopusId(scopusId);
    }
}
