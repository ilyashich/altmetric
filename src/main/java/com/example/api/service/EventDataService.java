package com.example.api.service;

import com.example.api.model.eventdata.news.EventDataNewsDto;
import com.example.api.model.eventdata.twitter.EventDataTwitterDto;
import com.example.api.webclient.eventdata.EventDataClient;
import com.example.api.webclient.eventdata.dto.news.EventDataSearchNewsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventDataService
{
    private final EventDataClient eventDataClient;

    public EventDataNewsDto searchEventDataNews(String doi)
    {
        return eventDataClient.searchEventDataNews(doi);
    }

    public EventDataTwitterDto searchEventDataTwitter(String doi)
    {
        return eventDataClient.searchEventDataTwitter(doi);
    }
}
