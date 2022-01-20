package com.example.api.dto.wikipedia;

import lombok.Getter;

@Getter
public class WikipediaSearchResultDto
{
    private String title;
    private String pageid;
    private String snippet;
}
