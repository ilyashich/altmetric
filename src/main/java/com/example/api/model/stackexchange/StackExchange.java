package com.example.api.model.stackexchange;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StackExchange
{
    private int count;
    private List<StackExchangeItems> items;
}
