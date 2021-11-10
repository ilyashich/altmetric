package com.example.api.model.wikipedia;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Data
public class WikipediaDto
{
    private final String totalHits;
    private final List<WikipediaArticleDto> citationInfo;
}
