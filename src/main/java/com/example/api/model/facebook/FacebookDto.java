package com.example.api.model.facebook;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class FacebookDto
{
    private int reactionCount;
    private int commentCount;
    private int shareCount;
}
