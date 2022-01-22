package com.example.api.model;

import com.example.api.model.crossref.Crossref;
import com.example.api.model.eventdata.news.EventDataNews;
import com.example.api.model.eventdata.twitter.EventDataTwitter;
import com.example.api.model.facebook.Facebook;
import com.example.api.model.mendeley.Mendeley;
import com.example.api.model.reddit.Reddit;
import com.example.api.model.scopus.Scopus;
import com.example.api.model.stackexchange.StackExchange;
import com.example.api.model.twitter.Twitter;
import com.example.api.model.wikipedia.Wikipedia;
import com.example.api.model.youtube.Youtube;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
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
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant modifiedDate;
    private List<String> links;
    private Mendeley mendeley;
    private Crossref crossref;
    private Scopus scopus;
    private Wikipedia wikipedia;
    private Reddit reddit;
    private StackExchange stackExchange;
    private Twitter twitter;
    private Facebook facebook;
    private Youtube youtube;
    private EventDataNews news;
    private EventDataTwitter eventDataTwitter;
}

