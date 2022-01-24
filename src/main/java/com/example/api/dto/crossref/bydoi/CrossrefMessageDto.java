package com.example.api.dto.crossref.bydoi;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrossrefMessageDto
{

    @JsonProperty("publisher")
    public String publisher;
    @JsonProperty("issue")
    public String issue;
    @JsonProperty("page")
    public String page;
    @JsonProperty("is-referenced-by-count")
    public Integer isReferencedByCount;
    @JsonProperty("title")
    public List<String> title = null;
    @JsonProperty("volume")
    public String volume;
    @JsonProperty("author")
    public List<CrossrefAuthorDto> author = null;
    @JsonProperty("container-title")
    public List<String> containerTitle = null;
    @JsonProperty("ISSN")
    public List<String> issn = null;
    @JsonProperty("published")
    public CrossrefPublishedDto published;

}