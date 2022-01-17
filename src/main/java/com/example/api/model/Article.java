package com.example.api.model;

import com.example.api.model.crossref.CrossrefDto;
import com.example.api.model.eventdata.news.EventDataNewsDto;
import com.example.api.model.eventdata.twitter.EventDataTwitterDto;
import com.example.api.model.facebook.FacebookDto;
import com.example.api.model.mendeley.MendeleyDto;
import com.example.api.model.reddit.RedditDto;
import com.example.api.model.scopus.ScopusDto;
import com.example.api.model.stackexchange.StackExchangeDto;
import com.example.api.model.twitter.TwitterDto;
import com.example.api.model.wikipedia.WikipediaDto;
import com.example.api.model.youtube.YoutubeDto;
import com.example.api.webclient.mendeley.dto.MendeleyCatalogDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document
@AllArgsConstructor
public class Article
{
    @Id
    private String id;
    @Indexed(unique = true, sparse = true)
    private String doi;
    @Indexed(unique = true, sparse = true)
    private String title;
    private List<String> links;
    private MendeleyDto mendeley;
    private CrossrefDto crossref;
    private ScopusDto scopus;
    private WikipediaDto wikipedia;
    private RedditDto reddit;
    private StackExchangeDto stackExchange;
    private TwitterDto twitter;
    private FacebookDto facebook;
    private YoutubeDto youtube;
    private EventDataNewsDto news;
    private EventDataTwitterDto eventDataTwitter;
}
