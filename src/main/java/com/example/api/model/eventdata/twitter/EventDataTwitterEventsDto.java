package com.example.api.model.eventdata.twitter;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class EventDataTwitterEventsDto
{
    private String authorName;
    private String tweetId;
}
