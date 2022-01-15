package com.example.api.webclient.crossref.dto.bydoi;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "given",
        "family"
})
@Generated("jsonschema2pojo")
public class CrossrefAuthorDto
{

    @JsonProperty("given")
    public String given;
    @JsonProperty("family")
    public String family;

}
