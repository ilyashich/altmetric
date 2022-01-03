package com.example.api.model.stackexchange;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Data
public class StackExchangeDto
{
    private final List<StackExchangeItemsDto> items;
}
