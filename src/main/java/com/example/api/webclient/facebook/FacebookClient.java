package com.example.api.webclient.facebook;

import com.example.api.model.facebook.FacebookDto;
import com.example.api.webclient.facebook.dto.FacebookSearchDto;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FacebookClient
{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String FACEBOOK_API_URL = "https://graph.facebook.com/v12.0/";
    private static final String FACEBOOK_API_TOKEN = "XXXXXXX";

    public FacebookDto searchFacebook(String url)
    {
        FacebookSearchDto twitterSearchDto = callGetMethod("?id={url}&fields=engagement&access_token={token}", FacebookSearchDto.class, url, FACEBOOK_API_TOKEN);

        return FacebookDto.builder()
                .shareCount(twitterSearchDto.getEngagement().getShare_count())
                .commentCount(twitterSearchDto.getEngagement().getComment_count())
                .reactionCount(twitterSearchDto.getEngagement().getReaction_count())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object...objects)
    {
        return restTemplate.getForObject(FACEBOOK_API_URL + url, responseType, objects);
    }
}
