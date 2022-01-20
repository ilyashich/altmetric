package com.example.api.dto.scopus;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "@_fa",
        "@ref",
        "@href"
})
@Generated("jsonschema2pojo")
public class Link {

    @JsonProperty("@_fa")
    public String fa;
    @JsonProperty("@ref")
    public String ref;
    @JsonProperty("@href")
    public String href;

}