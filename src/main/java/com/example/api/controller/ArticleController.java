package com.example.api.controller;

import com.example.api.model.Article;
import com.example.api.model.crossref.CrossrefDto;
import com.example.api.model.eventdata.news.EventDataNewsDto;
import com.example.api.model.eventdata.twitter.EventDataTwitterDto;
import com.example.api.model.facebook.FacebookDto;
import com.example.api.model.mendeley.MendeleyDto;
import com.example.api.model.reddit.RedditDto;
import com.example.api.model.scopus.ScopusDto;
import com.example.api.model.stackexchange.StackExchangeDto;
import com.example.api.model.twitter.TwitterDto;
import com.example.api.model.twitter.TwitterResultDto;
import com.example.api.model.wikipedia.WikipediaDto;
import com.example.api.model.youtube.YoutubeDto;
import com.example.api.service.*;
import com.example.api.webclient.mendeley.dto.MendeleyCatalogDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleController
{
    private final ArticleService articleService;
    private final MendeleyService mendeleyService;
    private final CrossrefService crossrefService;
    private final ScopusService scopusService;
    private final RedditService redditService;
    private final StackExchangeService stackExchangeService;
    private final TwitterService twitterService;
    private final WikipediaService wikipediaService;
    private final FacebookService facebookService;
    private final YoutubeService youtubeService;
    private final EventDataService eventDataService;

    @GetMapping("/articles")
    public List<Article> getArticles()
    {
        return articleService.getAllArticles();
    }

    @GetMapping("/articles/doi/{doiPrefix}/{doiSuffix}")
    public Article getArticleByDoi(@PathVariable String doiPrefix, @PathVariable String doiSuffix)
    {
        String doi = doiPrefix + "/" + doiSuffix;

        Optional<Article> article = articleService.getArticleByDoi(doi);

        return article.orElse(null);

    }

    @GetMapping("/articles/id/{id}")
    public Article getArticleById(@PathVariable String id)
    {
        Optional<Article> article = articleService.getArticleById(id);

        return article.orElse(null);

    }

    @PostMapping("/articles")
    public Article addArticle(@RequestBody String id) throws IOException
    {
        String doi = new ObjectMapper().readTree(id).path("id").asText();
        MendeleyCatalogDto mendeley = mendeleyService.getCatalog(doi);
        WikipediaDto wikipedia = wikipediaService.getCitationsById(doi);
        CrossrefDto crossref = crossrefService.searchCrossref(doi);
        ScopusDto scopus = scopusService.getCitationsByDoi(doi);

        String url = crawl("https://doi.org/" + doi);

        RedditDto reddit = redditService.searchReddit(url);
        StackExchangeDto stackExchange = stackExchangeService.searchStackExchange(url);
        TwitterDto twitter = twitterService.searchTwitter(url);
        FacebookDto facebook = facebookService.searchFacebook(url);
        YoutubeDto youtube = youtubeService.searchYoutube(url);
        EventDataNewsDto news = eventDataService.searchEventDataNews(doi);
        EventDataTwitterDto eventDataTwitter = eventDataService.searchEventDataTwitter(doi);

        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isPresent())
        {
            article.get().setMendeley(mendeley);
            article.get().setCrossref(crossref);
            article.get().setScopus(scopus);
            article.get().setWikipedia(wikipedia);
            article.get().setReddit(reddit);
            article.get().setStackExchange(stackExchange);
            article.get().setFacebook(facebook);
            article.get().setYoutube(youtube);
            article.get().setNews(news);
            article.get().setEventDataTwitter(eventDataTwitter);

            TwitterDto oldTwitter = article.get().getTwitter();
            for(TwitterResultDto result : twitter.getResults())
            {
                if(!oldTwitter.getResults().contains(result))
                {
                    oldTwitter.getResults().add(result);
                }
            }
            article.get().getTwitter().getResults().sort(Comparator.comparing(TwitterResultDto::getCreatedAt).reversed());

            article.get().getTwitter().setResultCount(article.get().getTwitter().getResults().size());

            article.get().setTwitter(oldTwitter);

            return articleService.addArticle(article.get());
        }

        return articleService.addArticle(Article.builder()
                .doi(doi)
                .mendeley(mendeley)
                .crossref(crossref)
                .scopus(scopus)
                .wikipedia(wikipedia)
                .reddit(reddit)
                .stackExchange(stackExchange)
                .twitter(twitter)
                .facebook(facebook)
                .youtube(youtube)
                .news(news)
                .eventDataTwitter(eventDataTwitter)
                .build());
    }

    private String crawl(String url) throws IOException
    {

        Response response = Jsoup.connect(url).execute();

        //System.out.println(response.statusCode() + " : " + url);

        if (response.hasHeader("location"))
        {
            String redirectUrl = response.header("location");
            crawl(redirectUrl);
        }

        Document doc = Jsoup.parse(response.body());

        Elements metaTags = doc.getElementsByTag("meta");

        String result = null;

        for (Element metaTag : metaTags)
        {
            String tag = metaTag.attr("HTTP-EQUIV");
            String content = metaTag.attr("content");

            if("REFRESH".equals(tag))
            {
                result = content;
            }
        }

        if(result == null)
        {
            return response.url().toString();
        }
        else
        {
            String tempResult = result.split("Redirect=")[1].split("%3Fvia")[0];
            return URLDecoder.decode(tempResult, StandardCharsets.UTF_8);
        }

    }
}
