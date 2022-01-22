package com.example.api.webclient;

import com.example.api.model.eventdata.news.EventDataNews;
import com.example.api.model.eventdata.news.EventDataNewsEvents;
import com.example.api.model.eventdata.twitter.EventDataTwitter;
import com.example.api.model.eventdata.twitter.EventDataTwitterEvents;
import com.example.api.dto.eventdata.news.EventDataSearchNewsDto;
import com.example.api.dto.eventdata.twitter.EventDataSearchTwitterDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventDataClient
{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String EVENTDATA_API_URL = "https://api.eventdata.crossref.org/v1/events";

    public EventDataNews searchEventDataNews(String doi)
    {
        EventDataSearchNewsDto news = callGetMethod(
                "?mailto=example@example.com&rows=100&obj-id={doi}&source=newsfeed",
                EventDataSearchNewsDto.class,
                doi
        );
        List<EventDataNewsEvents> events = new ArrayList<>();
        for(var event : news.message.events)
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

    public EventDataTwitter searchEventDataTwitter(String doi)
    {
        EventDataSearchTwitterDto twitter = callGetMethod(
                "?mailto=example@example.com&rows=100&obj-id={doi}&source=twitter",
                EventDataSearchTwitterDto.class,
                doi
        );
        List<EventDataTwitterEvents> events = new ArrayList<>();
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
