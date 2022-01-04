package com.example.api.controller;

import com.example.api.model.eventdata.news.EventDataNewsDto;
import com.example.api.model.eventdata.twitter.EventDataTwitterDto;
import com.example.api.service.EventDataService;
import com.example.api.webclient.eventdata.dto.news.EventDataSearchNewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EventDataController
{
    private final EventDataService eventDataService;

    @GetMapping("/eventdata/news/{doiPrefix}/{doiSuffix}")
    public EventDataNewsDto searchEventDataNews(@PathVariable String doiPrefix, @PathVariable String doiSuffix)
    {
        String doi = doiPrefix + "/" + doiSuffix;

        return eventDataService.searchEventDataNews(doi);
    }

    @GetMapping("/eventdata/twitter/{doiPrefix}/{doiSuffix}")
    public EventDataTwitterDto searchEventDataTwitter(@PathVariable String doiPrefix, @PathVariable String doiSuffix)
    {
        String doi = doiPrefix + "/" + doiSuffix;

        return eventDataService.searchEventDataTwitter(doi);
    }
}
