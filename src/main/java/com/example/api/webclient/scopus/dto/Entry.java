package com.example.api.webclient.scopus.dto;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "link",
        "citedby-count"
})
@Generated("jsonschema2pojo")
public class Entry {

    @JsonProperty("link")
    public List<Link> link = null;
    @JsonProperty("citedby-count")
    public String citedbyCount;

}