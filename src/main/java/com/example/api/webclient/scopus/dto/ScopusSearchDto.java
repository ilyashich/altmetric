package com.example.api.webclient.scopus.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "search-results"
})
@Generated("jsonschema2pojo")
public class ScopusSearchDto {

    @JsonProperty("search-results")
    public SearchResults searchResults;

}