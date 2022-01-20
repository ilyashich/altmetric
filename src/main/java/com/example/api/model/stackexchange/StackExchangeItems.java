package com.example.api.model.stackexchange;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StackExchangeItems
{
    private String link;
    private String creationDate;
    private String excerpt;
    private String title;
}
