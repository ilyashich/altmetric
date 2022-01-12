package com.example.api.model.mendeley;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class MendeleyAuthorsDto
{
    private String name;
    private String scopusAuthorId;
}
