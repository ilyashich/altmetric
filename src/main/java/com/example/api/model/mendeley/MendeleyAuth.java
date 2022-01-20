package com.example.api.model.mendeley;


import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class MendeleyAuth
{
    private final String accessToken;
}
