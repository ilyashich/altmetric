package com.example.api.webclient;

import com.example.api.dto.wikipedia.WikipediaPageDto;
import com.example.api.model.wikipedia.WikipediaArticle;
import com.example.api.model.wikipedia.Wikipedia;
import com.example.api.dto.wikipedia.WikipediaResultDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WikipediaClient
{
    public final String API_EN_URL = "https://en.wikipedia.org/w/rest.php/v1/search/page";
    public final String API_DE_URL = "https://de.wikipedia.org/w/rest.php/v1/search/page";
    public final String API_FR_URL = "https://fr.wikipedia.org/w/rest.php/v1/search/page";
    public final String API_PL_URL = "https://pl.wikipedia.org/w/rest.php/v1/search/page";
    public final String API_ES_URL = "https://es.wikipedia.org/w/rest.php/v1/search/page";
    public final String API_RU_URL = "https://ru.wikipedia.org/w/rest.php/v1/search/page";
    public RestTemplate restTemplate = new RestTemplate();

    public static final HashMap<String, String> languages;

    static
    {
        languages = new HashMap<>();
        languages.put("https://en.wikipedia.org/?curid=", "English");
        languages.put("https://fr.wikipedia.org/?curid=", "French");
        languages.put("https://de.wikipedia.org/?curid=", "German");
        languages.put("https://pl.wikipedia.org/?curid=", "Polish");
        languages.put("https://es.wikipedia.org/?curid=", "Spanish");
        languages.put("https://ru.wikipedia.org/?curid=", "Russian");
    }

    public Wikipedia getCitations(String query)
    {
        WikipediaResultDto wikipediaEn = callGetMethod(API_EN_URL + "?q=\"{id}\"&limit=100", WikipediaResultDto.class, query);
        WikipediaResultDto wikipediaDe = callGetMethod(API_DE_URL + "?q=\"{id}\"&limit=100", WikipediaResultDto.class, query);
        WikipediaResultDto wikipediaFr = callGetMethod(API_FR_URL + "?q=\"{id}\"&limit=100", WikipediaResultDto.class, query);
        WikipediaResultDto wikipediaPl = callGetMethod(API_PL_URL + "?q=\"{id}\"&limit=100", WikipediaResultDto.class, query);
        WikipediaResultDto wikipediaEs = callGetMethod(API_ES_URL + "?q=\"{id}\"&limit=100", WikipediaResultDto.class, query);
        WikipediaResultDto wikipediaRu = callGetMethod(API_RU_URL + "?q=\"{id}\"&limit=100", WikipediaResultDto.class, query);

        HashMap<String, List<WikipediaPageDto>> results = new HashMap<>();

        results.put("https://ru.wikipedia.org/?curid=", wikipediaRu.getPages());
        results.put("https://es.wikipedia.org/?curid=", wikipediaEs.getPages());
        results.put("https://fr.wikipedia.org/?curid=", wikipediaFr.getPages());
        results.put("https://pl.wikipedia.org/?curid=", wikipediaPl.getPages());
        results.put("https://de.wikipedia.org/?curid=", wikipediaDe.getPages());
        results.put("https://en.wikipedia.org/?curid=", wikipediaEn.getPages());


        List<WikipediaArticle> search = new ArrayList<>();

        int totalHits = 0;

        for(Map.Entry<String, List<WikipediaPageDto>> result : results.entrySet())
        {
            for(WikipediaPageDto value : result.getValue())
            {
                String thumbnail = value.getThumbnail() == null ? null : "https:" + value.getThumbnail().getUrl();
                search.add(WikipediaArticle.builder()
                        .language(languages.get(result.getKey()))
                        .link(result.getKey() + value.getId())
                        .title(value.getTitle())
                        .description(value.getDescription())
                        .thumbnail(thumbnail)
                        .build());
            }

            totalHits += result.getValue().size();
        }

        return Wikipedia.builder()
                .totalHits(totalHits)
                .citationInfo(search)
                .build();

    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(url, responseType, objects);
    }

}
