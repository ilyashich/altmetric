package com.example.api.webclient.crossref.dto.bytitle;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message"
})
@Generated("jsonschema2pojo")
public class CrossrefByTitleSearchDto
{

    @JsonProperty("message")
    public CrossrefByTitleMessageDto message;

}