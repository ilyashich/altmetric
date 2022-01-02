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
@RequestMapping("/")
public class FacebookController
{
    private final FacebookService facebookService;

    @GetMapping("/facebook/**")
    public FacebookDto searchFacebook(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.split("/facebook/")[1];
        return facebookService.searchFacebook(url);
    }
}
