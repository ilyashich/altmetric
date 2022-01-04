package com.example.api.model.eventdata.news;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Data
public class EventDataNewsDto
{
    private Integer totalResults;
    private List<EventDataNewsEventsDto> events;
}
