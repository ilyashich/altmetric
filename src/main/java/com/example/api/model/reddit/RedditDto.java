package com.example.api.model.reddit;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Data
public class RedditDto
{
    private List<RedditArticleDto> articles;
}