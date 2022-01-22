package com.example.api.dto.wikipedia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WikipediaThumbnailDto
{

    @JsonProperty("url")
    private String url;

}