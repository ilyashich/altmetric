package com.example.api.model.mendeley;

import com.example.api.webclient.mendeley.dto.MendeleyAuthorDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class MendeleyDto
{
    private final String title;
    private final MendeleyAuthorDto[] authors;
    private final String readersCount;
    private final String doi;
    private final String scopusId;
}
