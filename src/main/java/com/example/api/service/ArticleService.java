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
        return repository.save(newArticle);
    }

    public List<Article> getAllArticles()
    {
        return repository.findAllByOrderByAuthorSurnameAscAuthorNameAsc();
    }

    public Optional<Article> getArticleById(String id)
    {
        return repository.findById(id);
    }

    public Optional<Article> getArticleByDoi(String doi)
    {
        return repository.findArticleByDoi(doi);
    }

    public Optional<Article> getArticleByTitleAndAuthorName(String title, String authorName, String authorSurname)
    {
        return repository.findArticleByTitleAndAuthorNameAndAuthorSurname(title, authorName, authorSurname);
    }

    public List<Article> findByNameAndSurname(String surname, String name)
    {
        return repository.findArticlesByNameAndSurname(surname, name);
    }
}
