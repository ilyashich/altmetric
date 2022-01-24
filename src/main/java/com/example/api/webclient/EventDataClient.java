package com.example.api.webclient;

import com.example.api.dto.eventdata.news.EventDataEventNewsDto;
import com.example.api.dto.eventdata.twitter.EventDataEventTwitterDto;
import com.example.api.model.eventdata.news.EventDataNews;
import com.example.api.model.eventdata.news.EventDataNewsEvents;
import com.example.api.model.eventdata.twitter.EventDataTwitter;
import com.example.api.model.eventdata.twitter.EventDataTwitterEvents;
import com.example.api.dto.eventdata.news.EventDataSearchNewsDto;
import com.example.api.dto.eventdata.twitter.EventDataSearchTwitterDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventDataClient
{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String EVENTDATA_API_URL = "https://api.eventdata.crossref.org/v1/events";

    public EventDataNews searchEventDataNews(String doi) throws JsonProcessingException
    {
        String newsString = callGetMethod(
                "?mailto=example@example.com&rows=100&obj-id={doi}&source=newsfeed",
                String.class,
                doi
        );
        if(!newsString.contains("\"status\":\"ok\""))
        {
            return EventDataNews.builder()
                    .events(new ArrayList<>())
                    .totalResults(0)
                    .build();
        }
        EventDataSearchNewsDto news = new ObjectMapper().readValue(newsString, EventDataSearchNewsDto.class);
        List<EventDataNewsEvents> events = new ArrayList<>();
        for(EventDataEventNewsDto event : news.message.events)
        {
            events.add(EventDataNewsEvents.builder()
                    .ocurredAt(event.occurredAt)
                    .link(event.subjId)
                    .title(event.subj.title)
                    .build());

        }
        return EventDataNews.builder()
                .totalResults(news.message.totalResults)
                .events(events)
                .build();
    }

    public EventDataTwitter searchEventDataTwitter(String doi) throws JsonProcessingException
    {
        String twitterString = callGetMethod(
                "?mailto=example@example.com&rows=100&obj-id={doi}&source=twitter",
                String.class,
                doi
        );
        if(!twitterString.contains("\"status\":\"ok\""))
        {
            return EventDataTwitter.builder()
                    .events(new ArrayList<>())
                    .totalResults(0)
                    .build();
        }
        EventDataSearchTwitterDto twitter = new ObjectMapper().readValue(twitterString, EventDataSearchTwitterDto.class);
        List<EventDataTwitterEvents> events = new ArrayList<>();
        for(EventDataEventTwitterDto event : twitter.message.events)
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
            events.add(EventDataTwitterEvents.builder()
                    .occurredAt(event.occurredAt)
                    .tweetId(tweet)
                    .authorName(author)
                    .build());
        }

        return EventDataTwitter.builder()
                .events(events)
                .totalResults(twitter.message.totalResults)
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object...objects)
    {
        return restTemplate.getForObject(EVENTDATA_API_URL + url, responseType, objects);
    }
}
