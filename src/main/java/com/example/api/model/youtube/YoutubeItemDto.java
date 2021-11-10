package com.example.api.model.youtube;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class YoutubeItemDto
{
    private String videoId;
    private String publishedAt;
    private String channelTitle;
    private String title;
    private String description;
    private String thumbnailUrl;
}
