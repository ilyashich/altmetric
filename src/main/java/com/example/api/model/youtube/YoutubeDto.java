package com.example.api.model.youtube;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Data
public class YoutubeDto
{
    private String nextPageToken;
    private String totalResults;
    private List<YoutubeItemDto> items;
}
