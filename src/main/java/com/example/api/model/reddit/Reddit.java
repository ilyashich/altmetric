package com.example.api.model.reddit;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Reddit
{
    private int totalResults;
    private List<RedditArticle> articles;
}