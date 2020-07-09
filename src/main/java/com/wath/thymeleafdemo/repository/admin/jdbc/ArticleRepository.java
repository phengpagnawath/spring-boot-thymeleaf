package com.wath.thymeleafdemo.repository.admin.jdbc;

import com.wath.thymeleafdemo.model.Article;

import java.util.List;


public interface ArticleRepository {

    int save(Article article);

    List<Article> getAll();

    Article find(String articleID);

    int delete(String articleID);

    int update(Article newArticle);

}
