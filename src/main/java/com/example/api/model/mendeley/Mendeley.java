package com.example.api.model.mendeley;

import com.example.api.dto.mendeley.MendeleyReadersByAcademicStatusDto;
import com.example.api.dto.mendeley.MendeleyReadersBySubjectAreaDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Mendeley
{
    private String title;
    private List<MendeleyAuthors> authors;
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
    private int readersCount;
    private MendeleyReadersByAcademicStatusDto readerCountByAcademicStatus;
    private MendeleyReadersBySubjectAreaDto readerCountBySubjectArea;
}
