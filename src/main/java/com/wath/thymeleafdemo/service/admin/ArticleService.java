package com.wath.thymeleafdemo.service.admin;

import com.wath.thymeleafdemo.model.Article;

import java.util.List;

public interface ArticleService {
    Article save(Article article);

    List<Article> getAll();

    Article find(String articleID);

    void delete(String articleID);

    void update(Article newArticle);
}
