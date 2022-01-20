package com.example.api.dto.eventdata.twitter;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "occurred_at",
        "subj"
})
@Generated("jsonschema2pojo")
public class EventDataEventTwitterDto {

    @JsonProperty("occurred_at")
    public String occurredAt;
    @JsonProperty("subj")
    public EventDataSubjectTwitterDto subj;

}

