package com.example.api.dto.eventdata.twitter;

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
public class EventDataMessageTwitterDto {

    @JsonProperty("total-results")
    public int totalResults;
    @JsonProperty("events")
    public List<EventDataEventTwitterDto> events = null;

}
