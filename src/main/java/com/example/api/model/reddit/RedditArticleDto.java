package com.example.api.model.reddit;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class RedditArticleDto
{
    private String subreddit;
    private String author;
    private String title;
    private String created;
}

