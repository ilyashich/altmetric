package com.example.api.dto.eventdata.news;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDataEventNewsDto
{

    @JsonProperty("occurred_at")
    public String occurredAt;
    @JsonProperty("subj_id")
    public String subjId;
    @JsonProperty("subj")
    public EventDataSubjectNewsDto subj;

}
