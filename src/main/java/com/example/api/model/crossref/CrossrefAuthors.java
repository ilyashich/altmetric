package com.example.api.model.crossref;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CrossrefAuthors
{
    private String name;
    private String surname;
}
