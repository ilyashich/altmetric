package com.example.api.model.youtube;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class YoutubeItem
{
    private String videoId;
    private String publishedAt;
    private String channelTitle;
    private String title;
    private String description;
    private String thumbnailUrl;
}
