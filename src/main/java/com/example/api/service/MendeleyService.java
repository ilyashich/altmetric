package com.example.api.service;


import com.example.api.model.mendeley.Mendeley;
import com.example.api.webclient.MendeleyClient;
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

    public Mendeley searchCatalogByDoi(String doi)
    {
        return mendeleyClient.getCatalog(doi);
    }

    public Mendeley searchCatalogByTitleAndAuthor(String title, String author) throws JsonProcessingException
    {
        return mendeleyClient.searchCatalogByTitleAndAuthor(title, author);
    }

    public int getReadersByDoi(String doi)
    {
        return mendeleyClient.getReadersByDoi(doi);
    }

    public int getReadersByScopusId(String scopusId)
    {
        return mendeleyClient.getReadersByScopusId(scopusId);
    }
}
