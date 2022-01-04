package com.example.api.webclient.mendeley.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "issn",
        "scopus",
        "doi",
        "pmid"
})
@Generated("jsonschema2pojo")
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