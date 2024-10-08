package com.example.api.dto.mendeley;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MendeleyCatalogDto
{

    @JsonProperty("title")
    private String title;
    @JsonProperty("authors")
    private List<MendeleyAuthorDto> authors = null;
    @JsonProperty("year")
    private String year;
    @JsonProperty("source")
    private String source;
    @JsonProperty("identifiers")
    private MendeleyIdentifiersDto identifiers;
    @JsonProperty("pages")
    private String pages;
    @JsonProperty("volume")
    private String volume;
    @JsonProperty("issue")
    private String issue;
    @JsonProperty("month")
    private String month;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("link")
    private String link;
    @JsonProperty("reader_count")
    private int readerCount;
    @JsonProperty("reader_count_by_academic_status")
    private MendeleyReadersByAcademicStatusDto readerCountByAcademicStatus;
    @JsonProperty("reader_count_by_subject_area")
    private MendeleyReadersBySubjectAreaDto readerCountBySubjectArea;

}
