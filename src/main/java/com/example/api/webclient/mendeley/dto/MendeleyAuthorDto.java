package com.example.api.webclient.mendeley.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "first_name",
        "last_name",
        "scopus_author_id"
})
@Generated("jsonschema2pojo")
public class MendeleyAuthorDto
{

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("scopus_author_id")
    private String scopusAuthorId;
}

