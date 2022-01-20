package com.example.api.model.eventdata.news;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class EventDataNewsEvents
{
    private String ocurredAt;
    private String link;
    private String title;
}
