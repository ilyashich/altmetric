package com.example.api.dto.wikipedia;

import lombok.Getter;

import java.util.List;

@Getter
public class WikipediaQueryDto
{
    private WikipediaSearchInfoDto searchinfo;
    private List<WikipediaSearchResultDto> search;
}
