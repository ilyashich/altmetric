package com.example.api.dto.eventdata.twitter;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDataSubjectTwitterDto {

    @JsonProperty("author")
    public EventDataAuthorTwitterDto author;
    @JsonProperty("original-tweet-url")
    public String originalTweetUrl;

}
