package com.example.api.model.twitter;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Twitter
{
    private int resultCount;
    private List<TwitterResult> results;
}
