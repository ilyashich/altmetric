package com.example.api.dto.eventdata.news;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title"
})
@Generated("jsonschema2pojo")
public class EventDataSubjectNewsDto
{
    @JsonProperty("title")
    public String title;
}
