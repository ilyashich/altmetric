package com.example.api.model.wikipedia;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Wikipedia
{
    private int totalHits;
    private List<WikipediaArticle> citationInfo;
}
