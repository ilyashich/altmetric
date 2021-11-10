package com.example.api.service;


import com.example.api.model.mendeley.MendeleyDto;
import com.example.api.webclient.mendeley.MendeleyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MendeleyService
{
    private final MendeleyClient mendeleyClient;

    public MendeleyDto getCatalog(String query)
    {
        return mendeleyClient.getCatalog(query);
    }

    public String getReadersByDoi(String query)
    {
        return mendeleyClient.getReadersByDoi(query);
    }

    public String getReadersByScopusId(String query)
    {
        return mendeleyClient.getReadersByScopusId(query);
    }
}
