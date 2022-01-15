package com.example.api.webclient.crossref.dto.bydoi;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date-parts"
})
@Generated("jsonschema2pojo")
public class CrossrefPublishedDto
{

    @JsonProperty("date-parts")
    public List<List<Integer>> dateParts = null;

}
