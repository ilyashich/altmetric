package com.example.api.dto.stackexchange;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StackExchangeSearchDto
{
    private List<StackExchangeSearchItemsDto> items;
}
