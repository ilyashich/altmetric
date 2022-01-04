package com.example.api.webclient.eventdata.dto.twitter;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "url"
})
@Generated("jsonschema2pojo")
public class EventDataAuthorTwitterDto
{

    @JsonProperty("url")
    public String url;

}
