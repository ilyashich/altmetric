package com.example.api.webclient.reddit;

import com.example.api.model.reddit.RedditArticleDto;
import com.example.api.model.reddit.RedditDto;
import com.example.api.webclient.reddit.dto.RedditChildrenDto;
import com.example.api.webclient.reddit.dto.RedditMainDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedditClient
{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String REDDIT_API_URL = "https://www.reddit.com/search.json?";

    public RedditDto searchReddit(String searchQuery) throws JsonProcessingException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-agent", "com.example.altmetric.webclient.twitter:v1.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String response = callExchangeMethod("q={link}", entity, String.class, searchQuery);

        Gson g = new Gson();
        RedditMainDto redditMainDto = null;
        if(!response.equals("\"{}\""))
        {
            redditMainDto = g.fromJson(response, RedditMainDto.class);
        }


        List<RedditArticleDto> articles = new ArrayList<>();
        if(redditMainDto != null)
        {
            for (RedditChildrenDto redditChildrenDto : redditMainDto.getData().getChildren())
            {
                articles.add(RedditArticleDto.builder().subreddit(redditChildrenDto.getData().getSubreddit())
                        .author(redditChildrenDto.getData().getAuthor())
                        .title(redditChildrenDto.getData().getTitle())
                        .build());
            }
        }

        return RedditDto.builder()
                .articles(articles)
                .build();
    }

    private <T> T callExchangeMethod(String url, HttpEntity<String> entity, Class<T> responseType, Object... objects)
    {
        return restTemplate.exchange(REDDIT_API_URL + url, HttpMethod.GET, entity, responseType, objects).getBody();
    }
}