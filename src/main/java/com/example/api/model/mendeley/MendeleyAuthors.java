package com.example.api.model.mendeley;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MendeleyAuthors
{
    private String name;
    private String surname;
    private String scopusAuthorId;
}
