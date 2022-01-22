package com.example.api.webclient;

import com.example.api.dto.wikipedia.WikipediaPageDto;
import com.example.api.model.wikipedia.WikipediaArticle;
import com.example.api.model.wikipedia.Wikipedia;
import com.example.api.dto.wikipedia.WikipediaResultDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
    public static final HashMap<String, String> languagesTranslate;

    public static final String summary = "?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&pageids={ids}";

    static
    {
        languages = new HashMap<>();
        languages.put("https://en.wikipedia.org/?curid=", "English");
        languages.put("https://fr.wikipedia.org/?curid=", "French");
        languages.put("https://de.wikipedia.org/?curid=", "German");
        languages.put("https://pl.wikipedia.org/?curid=", "Polish");
        languages.put("https://es.wikipedia.org/?curid=", "Spanish");
        languages.put("https://ru.wikipedia.org/?curid=", "Russian");

        languagesTranslate = new HashMap<>();
        languagesTranslate.put("https://en.wikipedia.org/?curid=",
                "https://en.wikipedia.org/w/api.php" + summary);
        languagesTranslate.put("https://fr.wikipedia.org/?curid=",
                "https://fr.wikipedia.org/w/api.php" + summary);
        languagesTranslate.put("https://de.wikipedia.org/?curid=",
                "https://de.wikipedia.org/w/api.php" + summary);
        languagesTranslate.put("https://pl.wikipedia.org/?curid=",
                "https://pl.wikipedia.org/w/api.php" + summary);
        languagesTranslate.put("https://es.wikipedia.org/?curid=",
                "https://es.wikipedia.org/w/api.php" + summary);
        languagesTranslate.put("https://ru.wikipedia.org/?curid=",
                "https://ru.wikipedia.org/w/api.php" + summary);
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


        HashMap<Integer, WikipediaArticle> search = new HashMap<>();

        int totalHits = 0;

        for(Map.Entry<String, List<WikipediaPageDto>> result : results.entrySet())
        {
            if(result.getValue().size() > 0)
            {
                StringBuilder builder = new StringBuilder();

                for (WikipediaPageDto value : result.getValue())
                {
                    search.put(value.getId(), WikipediaArticle.builder()
                            .language(languages.get(result.getKey()))
                            .link(result.getKey() + value.getId())
                            .title(value.getTitle())
                            .build()
                    );

                    builder.append(value.getId());
                    builder.append("|");
                }
                totalHits += result.getValue().size();


                builder.deleteCharAt(builder.length() - 1);

                String summary = callGetMethod(
                        languagesTranslate.get(result.getKey()),
                        String.class,
                        builder
                );

                JsonObject parsed = new Gson().fromJson(summary, JsonObject.class);
                HashMap<Integer, String> descr = new HashMap<>();

                for (WikipediaPageDto page : result.getValue())
                {
                    descr.put(page.getId(), parsed.get("query").getAsJsonObject()
                            .get("pages").getAsJsonObject()
                            .get(page.getId().toString()).getAsJsonObject()
                            .get("extract").getAsString()
                    );
                }

                List<Integer> ids = new ArrayList<>(descr.keySet());

                for (Integer id : ids)
                {
                    if(descr.get(id).length() > 255)
                    {
                        search.get(id).setDescription(descr.get(id).substring(0, 252) + "...");
                    }
                    else
                    {
                        search.get(id).setDescription(descr.get(id));
                    }
                }
            }
        }

        return Wikipedia.builder()
                .totalHits(totalHits)
                .citationInfo(new ArrayList<>(search.values()))
                .build();

    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects)
    {
        return restTemplate.getForObject(url, responseType, objects);
    }

}
