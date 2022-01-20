package com.example.api.dto.scopus;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "entry"
})
@Generated("jsonschema2pojo")
public class SearchResults {

    @JsonProperty("entry")
    public List<Entry> entry = null;

}