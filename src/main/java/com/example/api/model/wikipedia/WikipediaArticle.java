package com.example.api.model.wikipedia;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WikipediaArticle
{
    private String language;
    private String title;
    private String link;
    private String description;
    private String thumbnail;
}
