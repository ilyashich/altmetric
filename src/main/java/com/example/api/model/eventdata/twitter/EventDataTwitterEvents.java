package com.example.api.model.eventdata.twitter;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EventDataTwitterEvents
{
    private String occurredAt;
    private String authorName;
    private String tweetId;
}
