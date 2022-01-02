package com.example.api.model;

import com.example.api.model.crossref.CrossrefDto;
import com.example.api.model.facebook.FacebookDto;
import com.example.api.model.mendeley.MendeleyDto;
import com.example.api.model.reddit.RedditDto;
import com.example.api.model.scopus.ScopusDto;
import com.example.api.model.twitter.TwitterDto;
import com.example.api.model.wikipedia.WikipediaDto;
import com.example.api.model.youtube.YoutubeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Builder
@Data
@Document
@AllArgsConstructor
public class Article
{
    @Id
    private String doi;
    private List<String> links;
    private MendeleyDto mendeley;
    private CrossrefDto crossref;
    private ScopusDto scopus;
    private WikipediaDto wikipedia;
    private RedditDto reddit;
    private TwitterDto twitter;
    private FacebookDto facebook;
    private YoutubeDto youtube;
}
