package com.example.api.dto.stackexchange;

import lombok.Getter;

@Getter
public class StackExchangeSearchItemsDto
{
    private String question_id;
    private String creation_date;
    private String excerpt;
    private String title;
}
