package com.example.api.service;

import com.example.api.model.Article;
import com.example.api.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService
{
    private final ArticleRepository repository;

    public Article addArticle(Article newArticle)
    {
        return this.repository.save(newArticle);
    }

    public List<Article> getAllArticles()
    {
        return this.repository.findAll();
    }

    public Optional<Article> getArticleById(String id)
    {
        return this.repository.findById(id);
    }

    public Optional<Article> getArticleByDoi(String doi)
    {
        return this.repository.findArticleByDoi(doi);
    }

    public Optional<Article> getArticleByTitleAndAuthorSurname(String title, String authorSurname)
    {
        return this.repository.findArticleByTitleAndAuthorSurname(title, authorSurname);
    }
}
