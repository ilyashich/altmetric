package com.example.api.webclient.mendeley;

import com.example.api.model.mendeley.MendeleyAuthDto;
import com.example.api.model.mendeley.MendeleyAuthorsDto;
import com.example.api.model.mendeley.MendeleyDto;
import com.example.api.webclient.crossref.dto.CrossrefAuthorDto;
import com.example.api.webclient.mendeley.dto.MendeleyAuthorDto;
import com.example.api.webclient.mendeley.dto.MendeleyCatalogDto;
import com.example.api.webclient.mendeley.dto.MendeleyTokenDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Component
public class MendeleyClient
{
    public final String MENDELEY_URL = "https://api.mendeley.com/";
    public final String AUTH_URL = "https://api.mendeley.com/oauth/token";
    public final String API_TOKEN;
    public RestTemplate restTemplate = new RestTemplate();
    public HttpHeaders headers = new HttpHeaders();
    public HttpEntity<String> request;

    public MendeleyClient()
    {
        this.API_TOKEN = authorize().getAccessToken();
    }

    private MendeleyAuthDto authorize()
    {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        request = new HttpEntity<>("grant_type=client_credentials&scope=all&client_id=XXXXXXX&client_secret=XXXXXXX", headers);
        ResponseEntity<MendeleyTokenDto> mendeleyTokenDto = callPostMethod(AUTH_URL, request, MendeleyTokenDto.class);
        return MendeleyAuthDto.builder()
                .accessToken(Objects.requireNonNull(mendeleyTokenDto.getBody()).getAccess_token())
                .build();
    }


    public MendeleyDto getCatalog(String doi)
    {
        MendeleyCatalogDto[] mendeleyCatalogDto = callGetMethod("/catalog?doi={doi}&view=all&access_token={access token}", MendeleyCatalogDto[].class, doi,  API_TOKEN);

        List<MendeleyCatalogDto> listCatalog = Arrays.asList(mendeleyCatalogDto);
        if(listCatalog.size() == 0)
        {
            return MendeleyDto.builder().build();
        }

        List<MendeleyAuthorsDto> authors = new ArrayList<>();
        for(MendeleyAuthorDto author : listCatalog.get(0).getAuthors())
        {
            authors.add(MendeleyAuthorsDto.builder()
                    .scopusAuthorId(author.getScopusAuthorId())
                    .name(author.getFirstName() + " " + author.getLastName())
                    .build());
        }

        //return listCatalog.get(0);
        return MendeleyDto.builder()
                .title(listCatalog.get(0).getTitle())
                .authors(authors)
                .readersCount(listCatalog.get(0).getReaderCount())
                .issue(listCatalog.get(0).getIssue())
                .issn(listCatalog.get(0).getIdentifiers().getIssn())
                .link(listCatalog.get(0).getLink())
                .publisher(listCatalog.get(0).getPublisher())
                .month(listCatalog.get(0).getMonth())
                .year(listCatalog.get(0).getYear())
                .pages(listCatalog.get(0).getPages())
                .source(listCatalog.get(0).getSource())
                .volume(listCatalog.get(0).getVolume())
                .pmid(listCatalog.get(0).getIdentifiers().getPmid())
                .doi(listCatalog.get(0).getIdentifiers().getDoi())
                .scopusId(listCatalog.get(0).getIdentifiers().getScopus())
                .readerCountByAcademicStatus(listCatalog.get(0).getReaderCountByAcademicStatus())
                .readerCountBySubjectArea(listCatalog.get(0).getReaderCountBySubjectArea())
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

}
