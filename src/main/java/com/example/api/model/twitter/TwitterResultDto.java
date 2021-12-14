package com.example.api.model.twitter;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class TwitterResultDto
{
    private String createdAt;
    private String tweetId;
    private String authorId;
    private String text;
}
