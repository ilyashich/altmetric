package com.example.api.repository;

import com.example.api.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends MongoRepository<Article, String>
{
    Optional<Article> findArticleByDoi(String doi);
    Optional<Article> findArticleByTitleAndAuthorNameAndAuthorSurname(String title, String authorName, String authorSurname);
    @Query("{ '$or' : [" +
            "{ '$and' : [ { 'mendeley.authors.surname' : ?0 }, { 'mendeley.authors.name' : ?1 } ] }," +
            "{ '$and' : [ { 'crossref.authors.surname' : ?0 }, { 'crossref.authors.name' : ?1 } ] }," +
            "{ '$and' : [ { 'authorSurname'            : ?0 }, { 'authorName'            : ?1 } ] }" +
            "] }")
    List<Article> findArticlesByNameAndSurname(String surname, String name);
    List<Article> findAllByOrderByAuthorSurnameAsc();
}
