package com.example.api.model.twitter;

import com.example.api.model.twitter.TwitterResultDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Data
public class TwitterDto
{
    private final int resultCount;
    private final List<TwitterResultDto> results;
}
