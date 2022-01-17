package com.example.api.repository;

import com.example.api.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArticleRepository extends MongoRepository<Article, String>
{
    Optional<Article> findArticleByDoi(String doi);
    Optional<Article> findArticleByTitle(String title);
}
