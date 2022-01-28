package com.example.api.dto.crossref.bytitle;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrossrefByTitleItemDto
{

    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("issue")
    private String issue;
    @JsonProperty("DOI")
    private String doi;
    @JsonProperty("page")
    private String page;
    @JsonProperty("is-referenced-by-count")
    private Integer isReferencedByCount;
    @JsonProperty("title")
    private List<String> title = null;
    @JsonProperty("volume")
    private String volume;
    @JsonProperty("author")
    private List<CrossrefByTitleAuthorDto> author = null;
    @JsonProperty("container-title")
    private List<String> containerTitle = null;
    @JsonProperty("ISSN")
    private List<String> issn = null;
    @JsonProperty("created")
    private CrossrefByTitleCreatedDto created;

}
