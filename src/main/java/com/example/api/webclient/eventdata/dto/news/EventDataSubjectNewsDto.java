package com.example.api.webclient.eventdata.dto.news;

import com.example.api.webclient.eventdata.dto.twitter.EventDataAuthorTwitterDto;
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
