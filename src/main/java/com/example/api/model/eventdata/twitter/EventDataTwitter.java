package com.example.api.model.eventdata.twitter;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EventDataTwitter
{
    private int totalResults;
    private List<EventDataTwitterEvents> events;
}
