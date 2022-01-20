package com.example.api.dto.youtube;

import lombok.Getter;

import java.util.List;

@Getter
public class YoutubeSearchDto
{
    private String nextPageToken;
    private YoutubePageInfoDto pageInfo;
    private List<YoutubeItemsDto> items;
}
