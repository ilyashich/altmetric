package com.example.api.model.crossref;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class CrossrefDto
{
    private final int referencedByCount;
}
