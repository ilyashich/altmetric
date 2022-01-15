package com.example.api.controller;

import com.example.api.model.twitter.TwitterDto;
import com.example.api.model.youtube.YoutubeDto;
import com.example.api.service.TwitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TwitterController
{
    private final TwitterService twitterService;

    @GetMapping("/twitter/url/**")
    public TwitterDto searchTwitterByUrl(HttpServletRequest request)
    {
        String requestURL;
        if(request.getQueryString() == null)
        {
            requestURL = request.getRequestURL().toString();
        }
        else
        {
            requestURL = request.getRequestURL().append('?').append(request.getQueryString()).toString();
        }
        String url = requestURL.split("/twitter/")[1];
        return twitterService.searchTwitterByUrl(url);
    }

    @GetMapping("/twitter/title/**")
    public TwitterDto searchTwitterByTitle(HttpServletRequest request)
    {
        String requestURL;
        if(request.getQueryString() == null)
        {
            requestURL = request.getRequestURL().toString();
        }
        else
        {
            requestURL = request.getRequestURL().append('?').append(request.getQueryString()).toString();
        }
        String encoded = requestURL.split("/twitter/title/")[1];
        String title = URLDecoder.decode(encoded, StandardCharsets.UTF_8);
        return twitterService.searchTwitterByTitle(title);
    }
}

//https://academic.oup.com/cid/article/71/9/2311/5867798
