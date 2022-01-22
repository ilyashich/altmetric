package com.example.api.dto.wikipedia;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WikipediaResultDto
{

    @JsonProperty("pages")
    private List<WikipediaPageDto> pages = null;

}
