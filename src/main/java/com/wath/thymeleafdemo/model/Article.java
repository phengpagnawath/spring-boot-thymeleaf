package com.wath.thymeleafdemo.model;

import java.io.Serializable;
import java.sql.Date;

public class Article implements Serializable {
    private int id;
    private String articleID;
    private String title;
    private String description;
    private String thumbnail;
    private String author;
    private Category category;
    private Date date;
    private boolean status;

    public Article() {
    }

    public Article(int id,String articleID, String title, String description, String thumbnail, String author, Category category, Date date, boolean status) {
        this.id = id;
        this.articleID = articleID;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.author = author;
        this.category = category;
        this.date = date;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleID='" + articleID + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", author='" + author + '\'' +
                ", category=" + category +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
