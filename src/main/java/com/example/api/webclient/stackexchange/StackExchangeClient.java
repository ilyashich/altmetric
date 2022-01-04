package com.example.api.webclient.stackexchange;

import com.example.api.model.stackexchange.StackExchangeDto;
import com.example.api.model.stackexchange.StackExchangeItemsDto;
import com.example.api.webclient.stackexchange.dto.StackExchangeSearchDto;
import com.example.api.webclient.stackexchange.dto.StackExchangeSearchItemsDto;
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

    public RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));

    public StackExchangeDto searchStackExchange(String url)
    {
        List<String> fields = Arrays.asList("stackoverflow", "biology", "math", "physics", "chemistry", "cs", "astronomy", "ai", "bioinformatics", "medicalsciences", "skeptics");
        StackExchangeDto results = StackExchangeDto.builder().items(new ArrayList<>()).build();
        for(String field : fields)
        {
            StackExchangeSearchDto response =
                    callGetMethod("?site={field}&order=desc&url={url}&sort=activity&filter=default&key={key}",
                            StackExchangeSearchDto.class, field, url, API_KEY);
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
                    StackExchangeItemsDto result = StackExchangeItemsDto.builder()
                            .link(link)
                            .creationDate(item.getCreation_date())
                            .excerpt(item.getExcerpt())
                            .title(item.getTitle())
                            .build();
                    results.getItems().add(result);
                }
            }
        }

        return results;

    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(API_URL + url, responseType, objects);
    }
}
