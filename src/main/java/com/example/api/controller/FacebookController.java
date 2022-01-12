package com.example.api.controller;

import com.example.api.model.facebook.FacebookDto;
import com.example.api.service.FacebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FacebookController
{
    private final FacebookService facebookService;

    @GetMapping("/facebook/**")
    public FacebookDto searchFacebook(HttpServletRequest request)
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
        String url = requestURL.split("/facebook/")[1];
        return facebookService.searchFacebook(url);
    }
}
