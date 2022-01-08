package com.example.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Html5PathsController
{

    @RequestMapping( method = {RequestMethod.OPTIONS, RequestMethod.GET}, path = {"/details/**", "/add", "/widget", "/"} )
    public String forwardReactPaths() {
        return "forward:/index.html";
    }
}
