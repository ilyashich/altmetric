package com.example.api.controller;

import com.example.api.model.Article;
import com.example.api.model.mendeley.MendeleyDto;
import com.example.api.model.reddit.RedditDto;
import com.example.api.model.twitter.TwitterDto;
import com.example.api.model.wikipedia.WikipediaDto;
import com.example.api.model.youtube.YoutubeDto;
import com.example.api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
@RequestMapping("/")
public class ArticleController
{
    private final ArticleService articleService;
    private final MendeleyService mendeleyService;
    private final RedditService redditService;
    private final TwitterService twitterService;
    private final WikipediaService wikipediaService;
    private final YoutubeService youtubeService;

    @GetMapping("/articles")
    public List<Article> getArticles()
    {
        return articleService.getAllArticles();
    }

    @GetMapping("/articles/**")
    public Article getArticleById(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        String query = requestURL.split("/articles/")[1];

        Optional<Article> article = articleService.getArticleById(query);

        return article.orElse(null);

    }

    @PostMapping("/articles")
    public Article addArticle(@RequestBody String id) throws IOException
    {
        MendeleyDto mendeley = mendeleyService.getCatalog(id);
        WikipediaDto wikipedia = wikipediaService.getCitationsById(id);

        String url = crawl("https://doi.org/" + id);

        RedditDto reddit = redditService.searchReddit(url);
        TwitterDto twitter = twitterService.searchTwitter(url);
        YoutubeDto youtube = youtubeService.searchYoutube(url);

        return articleService.addArticle(Article.builder()
                .doi(id)
                .mendeley(mendeley)
                .wikipedia(wikipedia)
                .reddit(reddit)
                .twitter(twitter)
                .youtube(youtube)
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
