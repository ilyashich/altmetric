package com.example.api.model.eventdata.news;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class EventDataNewsEventsDto
{
    private String ocurredAt;
    private String link;
    private String title;
}
