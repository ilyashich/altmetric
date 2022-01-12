package com.example.api.webclient.eventdata;

import com.example.api.model.eventdata.news.EventDataNewsDto;
import com.example.api.model.eventdata.news.EventDataNewsEventsDto;
import com.example.api.model.eventdata.twitter.EventDataTwitterDto;
import com.example.api.model.eventdata.twitter.EventDataTwitterEventsDto;
import com.example.api.webclient.eventdata.dto.news.EventDataSearchNewsDto;
import com.example.api.webclient.eventdata.dto.twitter.EventDataSearchTwitterDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventDataClient
{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String EVENTDATA_API_URL = "https://api.eventdata.crossref.org/v1/events";

    public EventDataNewsDto searchEventDataNews(String doi)
    {
        EventDataSearchNewsDto news = callGetMethod("?mailto=wigger1918@gmail.com&rows=100&obj-id={doi}&source=newsfeed", EventDataSearchNewsDto.class, doi);
        List<EventDataNewsEventsDto> events = new ArrayList<>();
        for(var event : news.message.events)
        {
            events.add(EventDataNewsEventsDto.builder()
                    .ocurredAt(event.occurredAt)
                    .link(event.subjId)
                    .title(event.subj.title)
                    .build());

        }
        return EventDataNewsDto.builder()
                .totalResults(news.message.totalResults)
                .events(events)
                .build();
    }

    public EventDataTwitterDto searchEventDataTwitter(String doi)
    {
        EventDataSearchTwitterDto twitter = callGetMethod("?mailto=wigger1918@gmail.com&rows=100&obj-id={doi}&source=twitter", EventDataSearchTwitterDto.class, doi);
        List<EventDataTwitterEventsDto> events = new ArrayList<>();
        for(var event : twitter.message.events)
        {
            String author;
            String tweet;
            if(event.subj.author.url.contains("twitter://user?screen_name"))
            {
                author = event.subj.author.url.split("=")[1];
                tweet = event.subj.originalTweetUrl.split("=")[1];
            }
            else
            {
                author = event.subj.author.url.split("www.twitter.com/")[1];
                tweet = event.subj.originalTweetUrl.split("/statuses/")[1];
            }
            events.add(EventDataTwitterEventsDto.builder()
                    .occurredAt(event.occurredAt)
                    .tweetId(tweet)
                    .authorName(author)
                    .build());
        }

        return EventDataTwitterDto.builder()
                .events(events)
                .totalResults(twitter.message.totalResults)
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object...objects)
    {
        return restTemplate.getForObject(EVENTDATA_API_URL + url, responseType, objects);
    }
}
