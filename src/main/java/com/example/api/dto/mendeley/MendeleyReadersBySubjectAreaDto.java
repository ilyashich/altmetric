package com.example.api.dto.mendeley;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MendeleyReadersBySubjectAreaDto
{

    @JsonProperty("Philosophy")
    private String philosophy;
    @JsonProperty("Decision Sciences")
    private String decisionSciences;
    @JsonProperty("Chemical Engineering")
    private String chemicalEngineering;
    @JsonProperty("Design")
    private String design;
    @JsonProperty("Materials Science")
    private String materialsScience;
    @JsonProperty("Pharmacology, Toxicology and Pharmaceutical Science")
    private String pharmacologyToxicologyAndPharmaceuticalScience;
    @JsonProperty("Energy")
    private String energy;
    @JsonProperty("Computer Science")
    private String computerScience;
    @JsonProperty("Chemistry")
    private String chemistry;
    @JsonProperty("Nursing and Health Professions")
    private String nursingAndHealthProfessions;
    @JsonProperty("Neuroscience")
    private String neuroscience;
    @JsonProperty("Social Sciences")
    private String socialSciences;
    @JsonProperty("Sports and Recreations")
    private String sportsAndRecreations;
    @JsonProperty("Earth and Planetary Sciences")
    private String earthAndPlanetarySciences;
    @JsonProperty("Physics and Astronomy")
    private String physicsAndAstronomy;
    @JsonProperty("Engineering")
    private String engineering;
    @JsonProperty("Agricultural and Biological Sciences")
    private String agriculturalAndBiologicalSciences;
    @JsonProperty("Medicine and Dentistry")
    private String medicineAndDentistry;
    @JsonProperty("Veterinary Science and Veterinary Medicine")
    private String veterinaryScienceAndVeterinaryMedicine;
    @JsonProperty("Arts and Humanities")
    private String artsAndHumanities;
    @JsonProperty("Environmental Science")
    private String environmentalScience;
    @JsonProperty("Psychology")
    private String psychology;
    @JsonProperty("Economics, Econometrics and Finance")
    private String economicsEconometricsAndFinance;
    @JsonProperty("Mathematics")
    private String mathematics;
    @JsonProperty("Linguistics")
    private String linguistics;
    @JsonProperty("Biochemistry, Genetics and Molecular Biology")
    private String biochemistryGeneticsAndMolecularBiology;
    @JsonProperty("Immunology and Microbiology")
    private String immunologyAndMicrobiology;
    @JsonProperty("Business, Management and Accounting")
    private String businessManagementAndAccounting;

}
