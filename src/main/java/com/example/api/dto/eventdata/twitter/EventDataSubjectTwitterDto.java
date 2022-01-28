package com.example.api.dto.eventdata.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDataSubjectTwitterDto {

    @JsonProperty("author")
    public EventDataAuthorTwitterDto author;
    @JsonProperty("original-tweet-url")
    public String originalTweetUrl;

}
