package com.example.api.model.eventdata.news;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EventDataNews
{
    private int totalResults;
    private List<EventDataNewsEvents> events;
}
