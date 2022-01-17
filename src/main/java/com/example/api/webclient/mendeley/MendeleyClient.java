package com.example.api.webclient.mendeley;

import com.example.api.model.mendeley.MendeleyAuthDto;
import com.example.api.model.mendeley.MendeleyAuthorsDto;
import com.example.api.model.mendeley.MendeleyDto;
import com.example.api.webclient.mendeley.dto.MendeleyAuthorDto;
import com.example.api.webclient.mendeley.dto.MendeleyCatalogDto;
import com.example.api.webclient.mendeley.dto.MendeleyTokenDto;
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

    private MendeleyAuthDto authorize()
    {
        start = Instant.now();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        request = new HttpEntity<>("grant_type=client_credentials&scope=all&client_id=XXXXXXX&client_secret=XXXXXXX", headers);
        ResponseEntity<MendeleyTokenDto> mendeleyTokenDto = callPostMethod(AUTH_URL, request, MendeleyTokenDto.class);
        return MendeleyAuthDto.builder()
                .accessToken(Objects.requireNonNull(mendeleyTokenDto.getBody()).getAccess_token())
                .build();
    }


    public MendeleyDto getCatalog(String doi)
    {
        checkIfTokenExpired();
        MendeleyCatalogDto[] mendeleyCatalogDto = callGetMethod("/catalog?doi={doi}&view=all&access_token={access token}", MendeleyCatalogDto[].class, doi,  API_TOKEN);

        return getMendeleyDto(mendeleyCatalogDto[0]);
    }

    public MendeleyDto searchCatalogByTitle(String title) throws JsonProcessingException
    {
        checkIfTokenExpired();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request;
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + API_TOKEN);
        request = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> mendeleyCatalogDto = restTemplate.exchange(
                MENDELEY_URL + "/search/catalog?title=\"{title}\"&limit=1&view=all",
                HttpMethod.GET,
                request,
                String.class,
                title
        );

        String toSingle = mendeleyCatalogDto.getBody().substring(1, mendeleyCatalogDto.getBody().length() - 1);

        ObjectMapper mapper = new ObjectMapper();
        MendeleyCatalogDto response = mapper.readValue(toSingle, MendeleyCatalogDto.class);

        return getMendeleyDto(response);
    }

    private MendeleyDto getMendeleyDto(MendeleyCatalogDto catalog)
    {
        if(catalog == null)
        {
            return MendeleyDto.builder().build();
        }

        List<MendeleyAuthorsDto> authors = new ArrayList<>();
        for(MendeleyAuthorDto author : catalog.getAuthors())
        {
            authors.add(MendeleyAuthorsDto.builder()
                    .scopusAuthorId(author.getScopusAuthorId())
                    .name(author.getFirstName() + " " + author.getLastName())
                    .build());
        }

        //return listCatalog.get(0);
        return MendeleyDto.builder()
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

    public String getReadersByDoi(String doi)
    {
        MendeleyCatalogDto[] mendeleyCatalogDto = callGetMethod("/catalog?doi={doi}&view=stats&access_token={access token}", MendeleyCatalogDto[].class, doi,  API_TOKEN);

        List<MendeleyCatalogDto> listCatalog = Arrays.asList(mendeleyCatalogDto);
        return listCatalog.get(0).getReaderCount();
    }

    public String getReadersByScopusId(String scopus)
    {
        MendeleyCatalogDto[] mendeleyCatalogDto = callGetMethod("/catalog?scopus={scopus}&view=stats&access_token={access token}", MendeleyCatalogDto[].class, scopus,  API_TOKEN);

        List<MendeleyCatalogDto> listCatalog = Arrays.asList(mendeleyCatalogDto);
        return listCatalog.get(0).getReaderCount();
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
