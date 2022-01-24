package com.example.api.dto.mendeley;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MendeleyIdentifiersDto
{

    @JsonProperty("issn")
    private String issn;
    @JsonProperty("scopus")
    private String scopus;
    @JsonProperty("doi")
    private String doi;
    @JsonProperty("pmid")
    private String pmid;

}