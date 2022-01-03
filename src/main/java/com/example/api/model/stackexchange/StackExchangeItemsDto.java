package com.example.api.model.stackexchange;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class StackExchangeItemsDto
{
    private String link;
    private String creationDate;
    private String excerpt;
    private String title;
}
