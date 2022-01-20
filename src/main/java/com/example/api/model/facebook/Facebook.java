package com.example.api.model.facebook;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Facebook
{
    private int reactionCount;
    private int commentCount;
    private int shareCount;
}
