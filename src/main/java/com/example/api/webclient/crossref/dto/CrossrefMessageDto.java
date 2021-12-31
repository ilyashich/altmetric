package com.example.api.webclient.crossref.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "is-referenced-by-count"
})
@Generated("jsonschema2pojo")
public class CrossrefMessageDto
{

    @JsonProperty("is-referenced-by-count")
    public Integer isReferencedByCount;

}