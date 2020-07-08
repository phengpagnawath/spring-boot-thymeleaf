package com.wath.thymeleafdemo.service.admin.imp;

import com.wath.thymeleafdemo.model.Article;
import com.wath.thymeleafdemo.repository.admin.imp.ArticleRepositoryImp;
import com.wath.thymeleafdemo.service.admin.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {

    private ArticleRepositoryImp articleRepositoryImp;

    @Autowired
    public ArticleServiceImp(ArticleRepositoryImp articleRepositoryImp) {
        this.articleRepositoryImp = articleRepositoryImp;
    }

    @Override
    public Article save(Article article) {
        return articleRepositoryImp.save(article) == 1 ? article : null;
    }

    @Override
    public List<Article> getAll() {
        return articleRepositoryImp.getAll();
    }

    @Override
    public Article find(String articleID) {
        return null;
    }

    @Override
    public void delete(String articleID) {
        articleRepositoryImp.delete(articleID);
    }

    @Override
    public void update(Article newArticle) {
        articleRepositoryImp.update(newArticle);
    }
}
