package com.example.api.controller;

import com.example.api.model.eventdata.news.EventDataNews;
import com.example.api.model.eventdata.twitter.EventDataTwitter;
import com.example.api.service.EventDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EventDataController
{
    private final EventDataService eventDataService;

    @GetMapping("/eventdata/news")
    public EventDataNews searchEventDataNews(@RequestParam String doi) throws JsonProcessingException
    {
        return eventDataService.searchEventDataNews(doi);
    }

    @GetMapping("/eventdata/twitter")
    public EventDataTwitter searchEventDataTwitter(@RequestParam String doi) throws JsonProcessingException
    {
        return eventDataService.searchEventDataTwitter(doi);
    }
}
