package com.example.api.model.twitter;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TwitterResult
{
    private String createdAt;
    private String tweetId;
    private String authorId;
    private String text;
}
