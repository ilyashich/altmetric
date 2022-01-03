package com.example.api.webclient.stackexchange.dto;

import lombok.Getter;

@Getter
public class StackExchangeSearchItemsDto
{
    private String question_id;
    private String creation_date;
    private String excerpt;
    private String title;
}
