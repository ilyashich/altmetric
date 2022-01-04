package com.example.api.model.twitter;

import com.example.api.model.twitter.TwitterResultDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Data
public class TwitterDto
{
    private int resultCount;
    private List<TwitterResultDto> results;
}
