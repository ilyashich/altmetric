package com.example.api.repository;

import com.example.api.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String>
{
}
