package com.example.api.webclient.eventdata.dto.news;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "occurred_at",
        "subj_id",
        "subj"
})
@Generated("jsonschema2pojo")
public class EventDataEventNewsDto
{

    @JsonProperty("occurred_at")
    public String occurredAt;
    @JsonProperty("subj_id")
    public String subjId;
    @JsonProperty("subj")
    public EventDataSubjectNewsDto subj;

}
