package com.example.api.webclient.twitter;

import com.example.api.model.twitter.TwitterDto;
import com.example.api.model.twitter.TwitterResultDto;
import com.example.api.webclient.twitter.dto.TwitterDataDto;
import com.example.api.webclient.twitter.dto.TwitterSearchDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class TwitterClient
{
        private final RestTemplate restTemplate = new RestTemplate();
        private static final String TWITTER_API_URL = "https://api.twitter.com/2/tweets/";
        private static final String TWITTER_API_TOKEN = "XXXXXXX";



        public TwitterDto searchTwitter(String url)
        {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> request;
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + TWITTER_API_TOKEN);
            request = new HttpEntity<>("parameters", headers);
            TwitterSearchDto twitterSearchDto = callExchangeMethod("search/recent?query=url:\"{url}\"&tweet.fields=created_at,author_id&max_results=100", request, TwitterSearchDto.class, url);

            List<TwitterResultDto> results = new ArrayList<>();
            if(twitterSearchDto.getData() != null)
            {
                for (TwitterDataDto data : twitterSearchDto.getData())
                {
                    results.add(TwitterResultDto.builder()
                            .createdAt(data.getCreated_at())
                            .tweetId(data.getId())
                            .authorId(data.getAuthor_id())
                            .text(data.getText())
                            .build());
                }
            }

            return TwitterDto.builder()
                    .results(results)
                    .resultCount(twitterSearchDto.getMeta().getResult_count())
                    .build();
        }

        private <T> T callExchangeMethod(String url, HttpEntity<String> request, Class<T> responseType, Object... objects)
        {
            return restTemplate.exchange(TWITTER_API_URL + url, HttpMethod.GET, request, responseType, objects).getBody();
        }

}
