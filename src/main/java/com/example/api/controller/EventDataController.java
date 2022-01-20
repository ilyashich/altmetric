package com.example.api.controller;

import com.example.api.model.eventdata.news.EventDataNews;
import com.example.api.model.eventdata.twitter.EventDataTwitter;
import com.example.api.service.EventDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EventDataController
{
    private final EventDataService eventDataService;

    @GetMapping("/eventdata/news")
    public EventDataNews searchEventDataNews(@RequestParam String doi)
    {
        return eventDataService.searchEventDataNews(doi);
    }

    @GetMapping("/eventdata/twitter")
    public EventDataTwitter searchEventDataTwitter(@RequestParam String doi)
    {
        return eventDataService.searchEventDataTwitter(doi);
    }
}
