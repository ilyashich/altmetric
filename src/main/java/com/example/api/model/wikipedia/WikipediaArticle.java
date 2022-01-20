package com.example.api.model.wikipedia;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WikipediaArticle
{
    private String title;
    private String pageId;
}
