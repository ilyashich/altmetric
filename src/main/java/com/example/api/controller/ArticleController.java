package com.example.api.controller;

import com.example.api.model.Article;
import com.example.api.model.crossref.CrossrefDto;
import com.example.api.model.eventdata.news.EventDataNewsDto;
import com.example.api.model.eventdata.news.EventDataNewsEventsDto;
import com.example.api.model.eventdata.twitter.EventDataTwitterDto;
import com.example.api.model.eventdata.twitter.EventDataTwitterEventsDto;
import com.example.api.model.facebook.FacebookDto;
import com.example.api.model.reddit.RedditArticleDto;
import com.example.api.model.reddit.RedditDto;
import com.example.api.model.scopus.ScopusDto;
import com.example.api.model.stackexchange.StackExchangeDto;
import com.example.api.model.twitter.TwitterDto;
import com.example.api.model.twitter.TwitterResultDto;
import com.example.api.model.wikipedia.WikipediaDto;
import com.example.api.model.youtube.YoutubeDto;
import com.example.api.model.youtube.YoutubeItemDto;
import com.example.api.service.*;
import com.example.api.webclient.mendeley.dto.MendeleyCatalogDto;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @PostMapping("/articles/update/mendeley")
    public Article updateMendeley(@RequestBody String doi)
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }

        MendeleyCatalogDto mendeley = mendeleyService.getCatalog(doi);
        article.get().setMendeley(mendeley);
        return articleService.addArticle(article.get());
    }

    @PostMapping("/articles/update/wikipedia")
    public Article updateWikipedia(@RequestBody String doi)
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }

        WikipediaDto wikipedia = wikipediaService.getCitationsById(doi);
        article.get().setWikipedia(wikipedia);
        return articleService.addArticle(article.get());
    }

    @PostMapping("/articles/update/scopus")
    public Article updateScopus(@RequestBody String doi)
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }

        ScopusDto scopus = scopusService.getCitationsByDoi(doi);
        article.get().setScopus(scopus);
        return articleService.addArticle(article.get());
    }

    @PostMapping("/articles/update/crossref")
    public Article updateCrossref(@RequestBody String doi)
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }

        CrossrefDto crossref = crossrefService.searchCrossref(doi);
        article.get().setCrossref(crossref);
        return articleService.addArticle(article.get());
    }

    @PostMapping("/articles/update/news")
    public Article updateNews(@RequestBody String doi)
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }

        EventDataNewsDto news = eventDataService.searchEventDataNews(doi);
        news.getEvents().sort(Comparator.comparing(EventDataNewsEventsDto::getOcurredAt).reversed());
        article.get().setNews(news);
        return articleService.addArticle(article.get());
    }

    @PostMapping("/articles/update/eventDataTwitter")
    public Article updateEventDataTwitter(@RequestBody String doi)
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }

        EventDataTwitterDto eventDataTwitter = eventDataService.searchEventDataTwitter(doi);
        eventDataTwitter.getEvents().sort(Comparator.comparing(EventDataTwitterEventsDto::getOccurredAt).reversed());
        article.get().setEventDataTwitter(eventDataTwitter);
        return articleService.addArticle(article.get());
    }

    @PostMapping("/articles/update/reddit")
    public Article updateReddit(@RequestBody String doi) throws IOException
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }
        String url = crawl("https://doi.org/" + doi);
        RedditDto reddit = redditService.searchReddit(url);
        reddit.getArticles().sort(Comparator.comparing(RedditArticleDto::getCreated).reversed());
        article.get().setReddit(reddit);
        return articleService.addArticle(article.get());
    }

    @PostMapping("/articles/update/stackexchange")
    public Article updateStackExchange(@RequestBody String doi) throws IOException
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }
        String url = crawl("https://doi.org/" + doi);
        StackExchangeDto stackExchange = stackExchangeService.searchStackExchange(url);
        article.get().setStackExchange(stackExchange);
        return articleService.addArticle(article.get());
    }

    @PostMapping("/articles/update/facebook")
    public Article updateStackFacebook(@RequestBody String doi) throws IOException
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }
        String url = crawl("https://doi.org/" + doi);
        FacebookDto facebook = facebookService.searchFacebook(url);
        article.get().setFacebook(facebook);
        return articleService.addArticle(article.get());
    }

    @PostMapping("/articles/update/youtube")
    public Article updateYoutube(@RequestBody String doi) throws IOException
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }
        String url = crawl("https://doi.org/" + doi);
        YoutubeDto youtube = youtubeService.searchYoutube(url);
        youtube.getItems().sort(Comparator.comparing(YoutubeItemDto::getPublishedAt).reversed());
        article.get().setYoutube(youtube);
        return articleService.addArticle(article.get());
    }

    @PostMapping("/articles/update/twitter")
    public Article updateTwitter(@RequestBody String doi) throws IOException
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }
        String url = crawl("https://doi.org/" + doi);
        TwitterDto twitter = twitterService.searchTwitter(url);
        TwitterDto oldTwitter = article.get().getTwitter();
        for(int i = twitter.getResults().size() - 1; i >= 0; i--)
        {
            if(!oldTwitter.getResults().contains(twitter.getResults().get(i)))
            {
                oldTwitter.getResults().add(0, twitter.getResults().get(i));
            }
        }

        article.get().getTwitter().setResultCount(article.get().getTwitter().getResults().size());

        article.get().setTwitter(oldTwitter);
        return articleService.addArticle(article.get());
    }

    @PostMapping("/articles")
    public Article addOrUpdateArticle(@RequestBody String doi) throws IOException
    {
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

        reddit.getArticles().sort(Comparator.comparing(RedditArticleDto::getCreated).reversed());
        youtube.getItems().sort(Comparator.comparing(YoutubeItemDto::getPublishedAt).reversed());
        news.getEvents().sort(Comparator.comparing(EventDataNewsEventsDto::getOcurredAt).reversed());
        eventDataTwitter.getEvents().sort(Comparator.comparing(EventDataTwitterEventsDto::getOccurredAt).reversed());

        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isPresent())
        {
            return updateArticle(article.get(), mendeley, crossref,
                            scopus, wikipedia, reddit,
                            stackExchange, twitter, facebook,
                            youtube, news, eventDataTwitter);
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

    public Article updateArticle(Article article, MendeleyCatalogDto mendeley, CrossrefDto crossref,
                                 ScopusDto scopus, WikipediaDto wikipedia, RedditDto reddit,
                                 StackExchangeDto stackExchange, TwitterDto twitter,
                                 FacebookDto facebook, YoutubeDto youtube, EventDataNewsDto news,
                                 EventDataTwitterDto eventDataTwitter)
    {
        article.setMendeley(mendeley);
        article.setCrossref(crossref);
        article.setScopus(scopus);
        article.setWikipedia(wikipedia);
        article.setReddit(reddit);
        article.setStackExchange(stackExchange);
        article.setFacebook(facebook);
        article.setYoutube(youtube);
        article.setNews(news);
        article.setEventDataTwitter(eventDataTwitter);

        TwitterDto oldTwitter = article.getTwitter();
        for(int i = twitter.getResults().size() - 1; i >= 0; i--)
        {
            if(!oldTwitter.getResults().contains(twitter.getResults().get(i)))
            {
                oldTwitter.getResults().add(0, twitter.getResults().get(i));
            }
        }

        article.getTwitter().setResultCount(article.getTwitter().getResults().size());

        article.setTwitter(oldTwitter);

        return articleService.addArticle(article);
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
