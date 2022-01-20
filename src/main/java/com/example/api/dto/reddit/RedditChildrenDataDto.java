package com.example.api.dto.reddit;

import lombok.Getter;

@Getter
public class RedditChildrenDataDto
{
    private String subreddit;
    private String title;
    private String author;
    private String created;
    private String permalink;
}
