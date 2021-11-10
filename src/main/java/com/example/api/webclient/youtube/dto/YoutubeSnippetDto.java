package com.example.api.webclient.youtube.dto;

import lombok.Getter;

@Getter
public class YoutubeSnippetDto
{
    private String publishedAt;
    private String channelTitle;
    private String title;
    private String description;
    private YoutubeThumbnailsDto thumbnails;
}
