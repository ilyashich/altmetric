package com.example.api.controller;

import com.example.api.model.Article;
import com.example.api.model.crossref.Crossref;
import com.example.api.model.eventdata.news.EventDataNews;
import com.example.api.model.eventdata.news.EventDataNewsEvents;
import com.example.api.model.eventdata.twitter.EventDataTwitter;
import com.example.api.model.eventdata.twitter.EventDataTwitterEvents;
import com.example.api.model.facebook.Facebook;
import com.example.api.model.mendeley.Mendeley;
import com.example.api.model.reddit.RedditArticle;
import com.example.api.model.reddit.Reddit;
import com.example.api.model.scopus.Scopus;
import com.example.api.model.stackexchange.StackExchange;
import com.example.api.model.twitter.Twitter;
import com.example.api.model.wikipedia.Wikipedia;
import com.example.api.model.youtube.Youtube;
import com.example.api.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

    @GetMapping("/article")
    public Article getArticleByDoiOrTitle(@RequestParam(required = false) String doi,
                                          @RequestParam(required = false) String title,
                                          @RequestParam(required = false) String author)
    {

        if(doi != null)
        {
            return articleService.getArticleByDoi(doi).orElse(null);
        }

        if(title != null && author != null)
        {
            return articleService.getArticleByTitleAndAuthorSurname(title, author).orElse(null);
        }

        return null;

    }

    @GetMapping("/article/update/doi/{metric}")
    public Article updateArticleMetricByDoi(@PathVariable String metric, @RequestParam String doi) throws IOException
    {
        Optional<Article> article = articleService.getArticleByDoi(doi);
        if(article.isEmpty())
        {
            return null;
        }

        Article updated;

        switch (metric)
        {
            case "mendeley":
                updated = updateMendeleyByDoi(article.get(), doi);
                break;
            case "crossref":
                updated = updateCrossrefByDoi(article.get(), doi);
                break;
            case "scopus":
                updated = updateScopusByDoi(article.get(), doi);
                break;
            case "wikipedia":
                updated = updateWikipediaByDoi(article.get(), doi);
                break;
            case "reddit":
                updated = updateRedditByDoi(article.get(), doi);
                break;
            case "stackexchange":
                updated = updateStackExchangeByDoi(article.get(), doi);
                break;
            case "twitter":
                updated = updateTwitterByDoi(article.get(), doi);
                break;
            case "facebook":
                updated = updateFacebookByDoi(article.get(), doi);
                break;
            case "youtube":
                updated = updateYoutubeByDoi(article.get(), doi);
                break;
            case "news":
                updated = updateNewsByDoi(article.get(), doi);
                break;
            case "eventdatatwitter":
                updated = updateEventDataTwitterByDoi(article.get(), doi);
                break;
            default:
                updated = null;
                break;
        }

        return updated;
    }

    @GetMapping("/article/update/title/{metric}")
    public Article updateArticleMetricByTitleAndAuthor(@PathVariable String metric, @RequestParam String title, @RequestParam String author) throws IOException
    {
        Optional<Article> article = articleService.getArticleByTitleAndAuthorSurname(title, author);
        if(article.isEmpty())
        {
            return null;
        }

        Article updated;

        switch (metric)
        {
            case "mendeley":
                updated = updateMendeleyByTitle(article.get(), title, author);
                break;
            case "crossref":
                updated = updateCrossrefByTitle(article.get(), title, author);
                break;
            case "scopus":
                updated = updateScopusByTitle(article.get(), title, author);
                break;
            case "wikipedia":
                updated = updateWikipediaByTitle(article.get(), title);
                break;
            case "reddit":
                updated = updateRedditByTitle(article.get(), title);
                break;
            case "stackexchange":
                updated = updateStackExchangeByTitle(article.get(), title);
                break;
            case "twitter":
                updated = updateTwitterByTitle(article.get(), title);
                break;
            case "youtube":
                updated = updateYoutubeByTitle(article.get(), title);
                break;
            default:
                updated = null;
                break;
        }

        return updated;
    }

    @GetMapping("/article/add/bydoi")
    public Article addOrUpdateArticleByDoi(@RequestParam String doi) throws IOException
    {
        Mendeley mendeley = mendeleyService.searchCatalogByDoi(doi);
        Wikipedia wikipedia = wikipediaService.getCitations(doi);
        Crossref crossref = crossrefService.searchCrossrefByDoi(doi);
        Scopus scopus = scopusService.getCitationsByDoi(doi);

        String url = crawl("https://doi.org/" + doi);

        Reddit reddit = redditService.searchRedditByUrl(url);
        StackExchange stackExchange = stackExchangeService.searchStackExchangeByUrl(url);
        Twitter twitter = twitterService.searchTwitterByUrl(url);
        Facebook facebook = facebookService.searchFacebook(url);
        Youtube youtube = youtubeService.searchYoutubeByUrl(url);
        EventDataNews news = eventDataService.searchEventDataNews(doi);
        EventDataTwitter eventDataTwitter = eventDataService.searchEventDataTwitter(doi);

        reddit.getArticles().sort(Comparator.comparing(RedditArticle::getCreated).reversed());
        news.getEvents().sort(Comparator.comparing(EventDataNewsEvents::getOcurredAt).reversed());
        eventDataTwitter.getEvents().sort(Comparator.comparing(EventDataTwitterEvents::getOccurredAt).reversed());

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

    @GetMapping("/article/add/bytitle")
    public Article addOrUpdateArticleByTitle(@RequestParam String title, @RequestParam String author) throws IOException
    {
        Mendeley mendeley = mendeleyService.searchCatalogByTitleAndAuthor(title, author);
        Wikipedia wikipedia = wikipediaService.getCitations(title);
        Crossref crossref = crossrefService.searchCrossrefByTitleAndAuthor(title, author);
        Scopus scopus = scopusService.getCitationsByTitleAndAuthor(title, author);

        Reddit reddit = redditService.searchRedditByTitle(title);
        StackExchange stackExchange = stackExchangeService.searchStackExchangeByTitle(title);
        Twitter twitter = twitterService.searchTwitterByTitle(title);
        Facebook facebook = Facebook.builder().build();
        Youtube youtube = youtubeService.searchYoutubeByTitle(title);
        EventDataNews news = EventDataNews.builder().events(new ArrayList<>()).build();
        EventDataTwitter eventDataTwitter = EventDataTwitter.builder().events(new ArrayList<>()).build();

        reddit.getArticles().sort(Comparator.comparing(RedditArticle::getCreated).reversed());

        Optional<Article> article = articleService.getArticleByTitleAndAuthorSurname(title, author);
        if(article.isPresent())
        {
            return updateArticle(article.get(), mendeley, crossref,
                    scopus, wikipedia, reddit,
                    stackExchange, twitter, facebook,
                    youtube, news, eventDataTwitter);
        }

        return articleService.addArticle(Article.builder()
                .title(title)
                .authorSurname(author)
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

    private Article updateArticle(Article article, Mendeley mendeley, Crossref crossref,
                                 Scopus scopus, Wikipedia wikipedia, Reddit reddit,
                                 StackExchange stackExchange, Twitter twitter,
                                 Facebook facebook, Youtube youtube, EventDataNews news,
                                 EventDataTwitter eventDataTwitter)
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

        return sortTwitterAndSave(article, twitter);
    }

    private Article updateMendeleyByDoi(Article article, String doi)
    {
        Mendeley mendeley = mendeleyService.searchCatalogByDoi(doi);
        article.setMendeley(mendeley);
        return articleService.addArticle(article);
    }


    private Article updateWikipediaByDoi(Article article, String doi)
    {
        Wikipedia wikipedia = wikipediaService.getCitations(doi);
        article.setWikipedia(wikipedia);
        return articleService.addArticle(article);
    }

    private Article updateScopusByDoi(Article article, String doi)
    {
        Scopus scopus = scopusService.getCitationsByDoi(doi);
        article.setScopus(scopus);
        return articleService.addArticle(article);
    }

    private Article updateCrossrefByDoi(Article article, String doi)
    {
        Crossref crossref = crossrefService.searchCrossrefByDoi(doi);
        article.setCrossref(crossref);
        return articleService.addArticle(article);
    }

    private Article updateNewsByDoi(Article article, String doi) throws JsonProcessingException
    {
        EventDataNews news = eventDataService.searchEventDataNews(doi);
        news.getEvents().sort(Comparator.comparing(EventDataNewsEvents::getOcurredAt).reversed());
        article.setNews(news);
        return articleService.addArticle(article);
    }

    private Article updateEventDataTwitterByDoi(Article article, String doi) throws JsonProcessingException
    {
        EventDataTwitter eventDataTwitter = eventDataService.searchEventDataTwitter(doi);
        eventDataTwitter.getEvents().sort(Comparator.comparing(EventDataTwitterEvents::getOccurredAt).reversed());
        article.setEventDataTwitter(eventDataTwitter);
        return articleService.addArticle(article);
    }

    private Article updateRedditByDoi(Article article, String doi) throws IOException
    {
        String url = crawl("https://doi.org/" + doi);
        Reddit reddit = redditService.searchRedditByUrl(url);
        reddit.getArticles().sort(Comparator.comparing(RedditArticle::getCreated).reversed());
        article.setReddit(reddit);
        return articleService.addArticle(article);
    }

    private Article updateStackExchangeByDoi(Article article, String doi) throws IOException
    {
        String url = crawl("https://doi.org/" + doi);
        StackExchange stackExchange = stackExchangeService.searchStackExchangeByUrl(url);
        article.setStackExchange(stackExchange);
        return articleService.addArticle(article);
    }

    private Article updateFacebookByDoi(Article article, String doi) throws IOException
    {
        String url = crawl("https://doi.org/" + doi);
        Facebook facebook = facebookService.searchFacebook(url);
        article.setFacebook(facebook);
        return articleService.addArticle(article);
    }

    private Article updateYoutubeByDoi(Article article, String doi) throws IOException
    {
        String url = crawl("https://doi.org/" + doi);
        Youtube youtube = youtubeService.searchYoutubeByUrl(url);
        article.setYoutube(youtube);
        return articleService.addArticle(article);
    }

    private Article updateTwitterByDoi(Article article, String doi) throws IOException
    {
        String url = crawl("https://doi.org/" + doi);
        Twitter twitter = twitterService.searchTwitterByUrl(url);
        return sortTwitterAndSave(article, twitter);
    }

    private Article updateMendeleyByTitle(Article article, String title, String author) throws JsonProcessingException
    {
        Mendeley mendeley = mendeleyService.searchCatalogByTitleAndAuthor(title, author);
        article.setMendeley(mendeley);
        return articleService.addArticle(article);
    }


    private Article updateWikipediaByTitle(Article article, String title)
    {
        Wikipedia wikipedia = wikipediaService.getCitations(title);
        article.setWikipedia(wikipedia);
        return articleService.addArticle(article);
    }

    private Article updateScopusByTitle(Article article, String title, String author)
    {
        Scopus scopus = scopusService.getCitationsByTitleAndAuthor(title, author);
        article.setScopus(scopus);
        return articleService.addArticle(article);
    }

    private Article updateCrossrefByTitle(Article article, String title, String author)
    {
        Crossref crossref = crossrefService.searchCrossrefByTitleAndAuthor(title, author);
        article.setCrossref(crossref);
        return articleService.addArticle(article);
    }

    private Article updateRedditByTitle(Article article, String title) throws JsonProcessingException
    {
        Reddit reddit = redditService.searchRedditByTitle(title);
        reddit.getArticles().sort(Comparator.comparing(RedditArticle::getCreated).reversed());
        article.setReddit(reddit);
        return articleService.addArticle(article);
    }

    private Article updateStackExchangeByTitle(Article article, String title)
    {
        StackExchange stackExchange = stackExchangeService.searchStackExchangeByTitle(title);
        article.setStackExchange(stackExchange);
        return articleService.addArticle(article);
    }

    private Article updateYoutubeByTitle(Article article, String title)
    {
        Youtube youtube = youtubeService.searchYoutubeByTitle(title);
        article.setYoutube(youtube);
        return articleService.addArticle(article);
    }

    private Article updateTwitterByTitle(Article article, String title)
    {
        Twitter twitter = twitterService.searchTwitterByTitle(title);
        return sortTwitterAndSave(article, twitter);
    }

    private Article sortTwitterAndSave(Article article, Twitter twitter)
    {
        Twitter oldTwitter = article.getTwitter();
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
