package com.example.api.webclient;

import com.example.api.model.stackexchange.StackExchange;
import com.example.api.model.stackexchange.StackExchangeItems;
import com.example.api.dto.stackexchange.StackExchangeSearchDto;
import com.example.api.dto.stackexchange.StackExchangeSearchItemsDto;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StackExchangeClient
{
    private static final String API_URL = "https://api.stackexchange.com/2.3/search/excerpts";
    private static final String API_KEY = "XXXXXXX";

    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
            new HttpComponentsClientHttpRequestFactory
                    (
                            HttpClientBuilder.create()
                                    .setDefaultRequestConfig(
                                            RequestConfig.custom()
                                            .setCookieSpec(CookieSpecs.STANDARD)
                                            .build())
                                    .build()
                    );

    public RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

    public StackExchange searchStackExchangeByUrl(String url)
    {
        List<String> fields = Arrays.asList("stackoverflow", "biology", "math", "stats", "physics", "chemistry", "cs",
                "cstheory", "astronomy", "ai", "bioinformatics", "earthscience", "medicalsciences", "skeptics");

        StackExchange results = StackExchange.builder().items(new ArrayList<>()).build();
        for(String field : fields)
        {
            StackExchangeSearchDto response =
                    callGetMethod("?site={field}&order=desc&url={url}&sort=activity&filter=default&key={key}",
                            StackExchangeSearchDto.class, field, url, API_KEY);
            checkResponse(results, field, response);
        }
        results.setTotalCount(results.getItems().size());
        return results;

    }

    public StackExchange searchStackExchangeByTitle(String title)
    {
        List<String> fields = Arrays.asList("stackoverflow", "biology", "math", "stats", "physics", "chemistry", "cs",
                "cstheory", "astronomy", "ai", "bioinformatics", "earthscience", "medicalsciences", "skeptics");

        StackExchange results = StackExchange.builder().items(new ArrayList<>()).build();
        for(String field : fields)
        {
            StackExchangeSearchDto response =
                    callGetMethod("?site={field}&order=desc&q={title}&sort=activity&filter=default&key={key}",
                            StackExchangeSearchDto.class, field, title, API_KEY);
            checkResponse(results, field, response);
        }
        results.setTotalCount(results.getItems().size());
        return results;

    }

    private void checkResponse(StackExchange results, String field, StackExchangeSearchDto response)
    {
        if(response.getItems() != null)
        {
            for(StackExchangeSearchItemsDto item : response.getItems())
            {
                String link;
                if(!field.equals("stackoverflow"))
                {
                    link = "https://" + field + ".stackexchange.com/questions/" + item.getQuestion_id();
                }
                else
                {
                    link = "https://stackoverflow.com/questions/" + item.getQuestion_id();
                }
                StackExchangeItems result = StackExchangeItems.builder()
                        .link(link)
                        .creationDate(item.getCreation_date())
                        .excerpt(item.getExcerpt())
                        .title(item.getTitle())
                        .build();
                results.getItems().add(result);
            }
        }
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(API_URL + url, responseType, objects);
    }
}
