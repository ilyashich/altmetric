package com.example.api.dto.eventdata.twitter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDataMessageTwitterDto {

    @JsonProperty("total-results")
    public int totalResults;
    @JsonProperty("events")
    public List<EventDataEventTwitterDto> events = null;

}
