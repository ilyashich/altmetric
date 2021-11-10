package com.example.api.webclient.wikipedia.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class WikipediaQueryDto
{
    private WikipediaSearchInfoDto searchinfo;
    private List<WikipediaSearchResultDto> search;
}
