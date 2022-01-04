package com.example.api.webclient.mendeley;

import com.example.api.model.mendeley.MendeleyAuthDto;
import com.example.api.model.mendeley.MendeleyDto;
import com.example.api.webclient.mendeley.dto.MendeleyCatalogDto;
import com.example.api.webclient.mendeley.dto.MendeleyTokenDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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


    public MendeleyCatalogDto getCatalog(String doi)
    {
        MendeleyCatalogDto[] mendeleyCatalogDto = callGetMethod("/catalog?doi={doi}&view=all&access_token={access token}", MendeleyCatalogDto[].class, doi,  API_TOKEN);

        List<MendeleyCatalogDto> listCatalog = Arrays.asList(mendeleyCatalogDto);
        return listCatalog.get(0);
//        return MendeleyDto.builder()
//                .title(listCatalog.get(0).getTitle())
//                .authors(listCatalog.get(0).getAuthors())
//                .readersCount(listCatalog.get(0).getReader_count())
//                .doi(listCatalog.get(0).getIdentifiers().getDoi())
//                .scopusId(listCatalog.get(0).getIdentifiers().getScopus())
//                .build();
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
