package com.example.api.dto.scopus;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScopusEntryDto
{

    @JsonProperty("link")
    public List<ScopusLinkDto> link = null;
    @JsonProperty("citedby-count")
    public String citedbyCount;

}