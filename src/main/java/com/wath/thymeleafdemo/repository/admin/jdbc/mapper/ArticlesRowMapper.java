package com.wath.thymeleafdemo.repository.admin.jdbc.mapper;

import com.wath.thymeleafdemo.model.Article;
import com.wath.thymeleafdemo.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticlesRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet resultSet, int i) throws SQLException {
        Article article = new Article();

        article.setId(resultSet.getInt(1));
        article.setArticleID(resultSet.getString(2));
        article.setTitle(resultSet.getString(3));
        article.setDescription(resultSet.getString(4));
        article.setThumbnail(resultSet.getString(5));
        article.setAuthor(resultSet.getString(6));
        article.setCategory(new Category(resultSet.getInt(7),null,true));
        article.setDate(resultSet.getDate(8));
        article.setStatus(resultSet.getBoolean(9));
        return article;
    }
}
