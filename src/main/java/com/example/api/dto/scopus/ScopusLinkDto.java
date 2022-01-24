package com.example.api.dto.scopus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScopusLinkDto
{

    @JsonProperty("@_fa")
    public String fa;
    @JsonProperty("@ref")
    public String ref;
    @JsonProperty("@href")
    public String href;

}