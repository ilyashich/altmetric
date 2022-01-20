package com.example.api.service;

import com.example.api.model.eventdata.news.EventDataNews;
import com.example.api.model.eventdata.twitter.EventDataTwitter;
import com.example.api.webclient.EventDataClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventDataService
{
    private final EventDataClient eventDataClient;

    public EventDataNews searchEventDataNews(String doi)
    {
        return eventDataClient.searchEventDataNews(doi);
    }

    public EventDataTwitter searchEventDataTwitter(String doi)
    {
        return eventDataClient.searchEventDataTwitter(doi);
    }
}
