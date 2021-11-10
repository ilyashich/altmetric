package com.example.api.model.wikipedia;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class WikipediaArticleDto
{
    private final String title;
    private final String pageId;
}
