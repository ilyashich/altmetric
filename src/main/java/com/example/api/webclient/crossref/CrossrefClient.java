package com.example.api.webclient.crossref;

import com.example.api.model.crossref.CrossrefDto;
import com.example.api.webclient.crossref.dto.CrossrefSearchDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CrossrefClient
{
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String CROSSREF_API_URL = "https://api.crossref.org/works/";

    public CrossrefDto searchCrossref(String doi)
    {
        CrossrefSearchDto response = callGetMethod("q={link}", CrossrefSearchDto.class, doi);

        return CrossrefDto.builder()
                .referencedByCount(response.message.isReferencedByCount)
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(CROSSREF_API_URL + url, responseType, objects);
    }
}
