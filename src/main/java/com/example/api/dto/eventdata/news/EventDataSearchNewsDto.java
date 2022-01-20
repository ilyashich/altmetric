package com.example.api.dto.eventdata.news;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message"
})
@Generated("jsonschema2pojo")
public class EventDataSearchNewsDto
{

    @JsonProperty("message")
    public EventDataMessageNewsDto message;

}