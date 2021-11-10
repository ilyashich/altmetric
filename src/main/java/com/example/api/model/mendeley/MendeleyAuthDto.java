package com.example.api.model.mendeley;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class MendeleyAuthDto
{
    private final String accessToken;
}
