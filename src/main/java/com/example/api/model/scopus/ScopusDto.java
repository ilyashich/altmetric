package com.example.api.model.scopus;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class ScopusDto
{
    private String citationsCount;
    private String link;
}
