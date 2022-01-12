package com.example.api.controller;

import com.example.api.model.stackexchange.StackExchangeDto;
import com.example.api.model.twitter.TwitterDto;
import com.example.api.service.StackExchangeService;
import com.example.api.service.TwitterService;
import com.example.api.webclient.stackexchange.dto.StackExchangeSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StackExchangeController
{
    private final StackExchangeService stackExchangeService;

    @GetMapping("/stackexchange/**")
    public StackExchangeDto searchTwitter(HttpServletRequest request)
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
        String url = requestURL.split("/stackexchange/")[1];
        return stackExchangeService.searchStackExchange(url);
    }
}
