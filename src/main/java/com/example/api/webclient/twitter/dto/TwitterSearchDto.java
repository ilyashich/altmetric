package com.example.api.webclient.twitter.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TwitterSearchDto
{
    private List<TwitterDataDto> data;
    private TwitterMetaDto meta;
}
