package com.example.api.dto.crossref.bytitle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrossrefByTitleAuthorDto
{

    @JsonProperty("given")
    private String given;
    @JsonProperty("family")
    private String family;

}
