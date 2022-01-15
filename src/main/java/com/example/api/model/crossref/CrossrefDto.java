package com.example.api.model.crossref;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Data
public class CrossrefDto
{
    private int referencedByCount;
    private String title;
    private String publisher;
    private String issue;
    private String page;
    private String volume;
    private List<CrossrefAuthorsDto> authors;
    private String source;
    private List<String> issn;
    private String doi;
    private String published;

}
