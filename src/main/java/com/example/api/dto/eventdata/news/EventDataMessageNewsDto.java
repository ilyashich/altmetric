package com.example.api.dto.eventdata.news;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "total-results",
        "events"
})
@Generated("jsonschema2pojo")
public class EventDataMessageNewsDto
{
    @JsonProperty("total-results")
    public int totalResults;
    @JsonProperty("events")
    public List<EventDataEventNewsDto> events = null;

}
