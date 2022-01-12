package com.example.api.model.mendeley;

import com.example.api.webclient.mendeley.dto.MendeleyReadersByAcademicStatusDto;
import com.example.api.webclient.mendeley.dto.MendeleyReadersBySubjectAreaDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Data
public class MendeleyDto
{
    private String title;
    private List<MendeleyAuthorsDto> authors;
    private String year;
    private String month;
    private String source;
    private String issn;
    private String doi;
    private String scopusId;
    private String pmid;
    private String pages;
    private String volume;
    private String issue;
    private String publisher;
    private String link;
    private String readersCount;
    private MendeleyReadersByAcademicStatusDto readerCountByAcademicStatus;
    private MendeleyReadersBySubjectAreaDto readerCountBySubjectArea;
}
