package com.example.api.webclient.mendeley.dto;
import lombok.Getter;

@Getter
public class MendeleyCatalogDto
{
    private String title;
    private MendeleyAuthorDto[] authors;
    private String reader_count;
    private MendeleyIdentifiersDto identifiers;
}
