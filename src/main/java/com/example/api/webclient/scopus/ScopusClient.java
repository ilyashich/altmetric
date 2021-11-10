package com.example.api.webclient.scopus;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class ScopusClient
{
    public final String SCOPUS_URL = "https://api.elsevier.com/content/search/scopus";
    public final String API_KEY = "XXXXXXX";
    public RestTemplate restTemplate = new RestTemplate();
    public HttpHeaders headers = new HttpHeaders();
    public HttpEntity<String> request;

    public String getCitationsByDoi(String doi)
    {
        headers.set("X-ELS-APIKey", API_KEY);
        request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(SCOPUS_URL + "?query=DOI({doi})&field=citedby-count", HttpMethod.GET, request, String.class, doi);
        return response.getBody();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(SCOPUS_URL + url, responseType, objects);
    }

    private <T> ResponseEntity<T> callPostMethod(String url, HttpEntity<String> request, Class<T> responseType, Object... objects)
    {
        return restTemplate.postForEntity(url, request, responseType, objects);
    }

}

