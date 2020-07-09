package com.wath.thymeleafdemo.repository.admin.jdbc.imp;


import com.wath.thymeleafdemo.model.Article;
import com.wath.thymeleafdemo.repository.admin.jdbc.ArticleRepository;
import com.wath.thymeleafdemo.repository.admin.jdbc.mapper.ArticlesRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepositoryImp implements ArticleRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Article article) {
        String sql = "insert into articles (article_id,title,description,thumbnail,author,category_id,create_date) " +
                "values(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1,article.getArticleID());
            preparedStatement.setString(2,article.getTitle());
            preparedStatement.setString(3,article.getDescription());
            preparedStatement.setString(4,article.getThumbnail());
            preparedStatement.setString(5,article.getAuthor());
            preparedStatement.setInt(6,article.getCategory().getId());
           preparedStatement.setDate(7,article.getDate());
        });
    }

    @Override
    public List<Article> getAll() {

        String sql = "select * from articles where status = TRUE order by id asc";
        return jdbcTemplate.query(sql,new ArticlesRowMapper());
    }

    @Override
    public Article find(String articleID) {
        return null;
    }

    @Override
    public int delete(String articleID) {
        String sql = "update articles set status = false where article_id = ?";
        return jdbcTemplate.update(sql,articleID);
    }

    @Override
    public int update(Article newArticle) {
        String sql = "update articles set title = ?," +
                "description = ?," +
                "thumbnail = ?," +
                "category_id = ?" +
                "where article_id = ? and status = true";
        return jdbcTemplate.update(sql,newArticle.getTitle(),newArticle.getDescription(),newArticle.getThumbnail(),
                newArticle.getCategory().getId(),newArticle.getArticleID());
    }
}
