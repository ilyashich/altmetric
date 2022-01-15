package com.example.api.webclient.mendeley.dto;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Student  > Postgraduate",
        "Professor  > Associate Professor",
        "Researcher",
        "Student  > Master",
        "Student  > Ph. D. Student",
        "Professor",
        "Student  > Bachelor",
        "Student  > Doctoral Student",
        "Lecturer",
        "Other",
        "Librarian",
        "Lecturer  > Senior Lecturer"
})
@Generated("jsonschema2pojo")
public class MendeleyReadersByAcademicStatusDto
{

    @JsonProperty("Student  > Postgraduate")
    private String studentPostgraduate;
    @JsonProperty("Professor > Associate Professor")
    private String professorAssociateProfessor;
    @JsonProperty("Researcher")
    private String researcher;
    @JsonProperty("Student  > Master")
    private String studentMaster;
    @JsonProperty("Student  > Ph. D. Student")
    private String studentPhDStudent;
    @JsonProperty("Professor")
    private String professor;
    @JsonProperty("Student  > Bachelor")
    private String studentBachelor;
    @JsonProperty("Student  > Doctoral Student")
    private String studentDoctoralStudent;
    @JsonProperty("Lecturer")
    private String lecturer;
    @JsonProperty("Other")
    private String other;
    @JsonProperty("Librarian")
    private String librarian;
    @JsonProperty("Lecturer > Senior Lecturer")
    private String lecturerSeniorLecturer;

}