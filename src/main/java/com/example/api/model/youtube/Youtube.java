package com.example.api.model.youtube;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Youtube
{
    private String nextPageToken;
    private int totalResults;
    private List<YoutubeItem> items;
}
