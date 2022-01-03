package com.example.api.webclient.stackexchange.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StackExchangeSearchDto
{
    private List<StackExchangeSearchItemsDto> items;
}
