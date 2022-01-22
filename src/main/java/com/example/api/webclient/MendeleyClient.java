package com.example.api.webclient;

import com.example.api.model.mendeley.MendeleyAuth;
import com.example.api.model.mendeley.MendeleyAuthors;
import com.example.api.model.mendeley.Mendeley;
import com.example.api.dto.mendeley.MendeleyAuthorDto;
import com.example.api.dto.mendeley.MendeleyCatalogDto;
import com.example.api.dto.mendeley.MendeleyTokenDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.*;


@Component
public class MendeleyClient
{
    public final String MENDELEY_URL = "https://api.mendeley.com/";
    public final String AUTH_URL = "https://api.mendeley.com/oauth/token";
    public String API_TOKEN;
    public RestTemplate restTemplate = new RestTemplate();
    public HttpHeaders headers = new HttpHeaders();
    public HttpEntity<String> request;
    public Instant start;

    public MendeleyClient()
    {
        this.API_TOKEN = authorize().getAccessToken();
    }

    private MendeleyAuth authorize()
    {
        start = Instant.now();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        request = new HttpEntity<>("grant_type=client_credentials&scope=all&client_id=XXXXXXX&client_secret=XXXXXXX", headers);
        ResponseEntity<MendeleyTokenDto> mendeleyTokenDto = callPostMethod(AUTH_URL, request, MendeleyTokenDto.class);
        return MendeleyAuth.builder()
                .accessToken(Objects.requireNonNull(mendeleyTokenDto.getBody()).getAccess_token())
                .build();
    }


    public Mendeley getCatalog(String doi)
    {
        checkIfTokenExpired();
        MendeleyCatalogDto[] mendeleyCatalogDto = callGetMethod("/catalog?doi={doi}&view=all&access_token={access token}", MendeleyCatalogDto[].class, doi,  API_TOKEN);

        return getMendeleyDto(mendeleyCatalogDto[0]);
    }

    public Mendeley searchCatalogByTitleAndAuthor(String title, String author) throws JsonProcessingException
    {
        checkIfTokenExpired();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request;
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + API_TOKEN);
        request = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> mendeleyCatalogDto = restTemplate.exchange(
                MENDELEY_URL + "/search/catalog?title=\"{title}\"&author={author}&limit=1&view=all",
                HttpMethod.GET,
                request,
                String.class,
                title,
                author
        );

        MendeleyCatalogDto response;

        if(mendeleyCatalogDto.getBody() != null)
        {
            String toSingle = mendeleyCatalogDto.getBody().substring(1, mendeleyCatalogDto.getBody().length() - 1);

            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(toSingle, MendeleyCatalogDto.class);
        }
        else
        {
            response = null;
        }

        return getMendeleyDto(response);
    }

    private Mendeley getMendeleyDto(MendeleyCatalogDto catalog)
    {
        if(catalog == null)
        {
            return Mendeley.builder().build();
        }

        List<MendeleyAuthors> authors = new ArrayList<>();
        for(MendeleyAuthorDto author : catalog.getAuthors())
        {
            authors.add(MendeleyAuthors.builder()
                    .scopusAuthorId(author.getScopusAuthorId())
                    .name(author.getFirstName() + " " + author.getLastName())
                    .build());
        }

        //return listCatalog.get(0);
        return Mendeley.builder()
                .title(catalog.getTitle())
                .authors(authors)
                .readersCount(catalog.getReaderCount())
                .issue(catalog.getIssue())
                .issn(catalog.getIdentifiers().getIssn())
                .link(catalog.getLink())
                .publisher(catalog.getPublisher())
                .month(catalog.getMonth())
                .year(catalog.getYear())
                .pages(catalog.getPages())
                .source(catalog.getSource())
                .volume(catalog.getVolume())
                .pmid(catalog.getIdentifiers().getPmid())
                .doi(catalog.getIdentifiers().getDoi())
                .scopusId(catalog.getIdentifiers().getScopus())
                .readerCountByAcademicStatus(catalog.getReaderCountByAcademicStatus())
                .readerCountBySubjectArea(catalog.getReaderCountBySubjectArea())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object...objects)
    {
        return restTemplate.getForObject(MENDELEY_URL + url, responseType, objects);
    }

    private <T> ResponseEntity<T> callPostMethod(String url, HttpEntity<String> request, Class<T> responseType, Object...objects)
    {
        return restTemplate.postForEntity(url, request, responseType, objects);
    }

    public void checkIfTokenExpired()
    {
        Instant now = Instant.now();
        long timeElapsed = Duration.between(start, now).toSeconds();
        if(timeElapsed >= 3600)
        {
            this.API_TOKEN = authorize().getAccessToken();
        }
    }

}
