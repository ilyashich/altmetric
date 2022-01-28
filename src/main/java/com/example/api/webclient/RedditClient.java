package com.example.api.webclient;

import com.example.api.model.reddit.RedditArticle;
import com.example.api.model.reddit.Reddit;
import com.example.api.dto.reddit.RedditChildrenDto;
import com.example.api.dto.reddit.RedditMainDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class RedditClient
{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String REDDIT_API_URL = "https://www.reddit.com/search.json?";

    public Reddit searchRedditByUrl(String url) throws JsonProcessingException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-agent", "com.example.altmetric.webclient.twitter:v1.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String response = callExchangeMethod("q={link}", HttpMethod.GET, entity, String.class, url);

        return getRedditDto(response);
    }

    public Reddit searchRedditByTitle(String title) throws JsonProcessingException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-agent", "com.example.altmetric.webclient.twitter:v1.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String response = callExchangeMethod("q=\"{title}\"", HttpMethod.GET, entity, String.class, title);

        return getRedditDto(response);
    }

    private Reddit getRedditDto(String response)
    {
        Gson g = new Gson();
        RedditMainDto redditMainDto = null;
        if(!response.equals("\"{}\""))
        {
            if(response.charAt(0) == '[')
            {
                redditMainDto = g.fromJson(response, RedditMainDto[].class)[0];
            }
            else
            {
                redditMainDto = g.fromJson(response, RedditMainDto.class);
            }
        }


        List<RedditArticle> articles = new ArrayList<>();
        if(redditMainDto != null)
        {
            for (RedditChildrenDto redditChildrenDto : redditMainDto.getData().getChildren())
            {
                articles.add(RedditArticle.builder().subreddit(redditChildrenDto.getData().getSubreddit())
                        .author(redditChildrenDto.getData().getAuthor())
                        .title(redditChildrenDto.getData().getTitle())
                        .created(redditChildrenDto.getData().getCreated())
                        .permalink("https://www.reddit.com" + redditChildrenDto.getData().getPermalink())
                        .build());
            }
        }

        return Reddit.builder()
                .totalResults(articles.size())
                .articles(articles)
                .build();
    }

    private <T> T callExchangeMethod(String url, HttpMethod method, HttpEntity<String> entity, Class<T> responseType, Object... objects)
    {
        return restTemplate.exchange(REDDIT_API_URL + url, method, entity, responseType, objects).getBody();
    }
}