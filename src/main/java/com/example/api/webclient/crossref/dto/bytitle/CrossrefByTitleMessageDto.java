package com.example.api.webclient.crossref.dto.bytitle;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "items"
})
@Generated("jsonschema2pojo")
public class CrossrefByTitleMessageDto
{
    @JsonProperty("items")
    public List<CrossrefByTitleItemDto> items = null;

}