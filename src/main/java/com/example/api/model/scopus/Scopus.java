package com.example.api.model.scopus;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Scopus
{
    private int citationsCount;
    private String link;
}
