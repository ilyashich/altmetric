package com.example.api.model.reddit;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RedditArticle
{
    private String subreddit;
    private String author;
    private String title;
    private String created;
    private String permalink;
}

