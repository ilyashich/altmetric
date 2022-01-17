package com.example.api.model.eventdata.twitter;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Data
public class EventDataTwitterDto
{
    private Integer totalResults;
    private List<EventDataTwitterEventsDto> events;
}
