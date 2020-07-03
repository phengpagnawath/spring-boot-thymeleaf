package com.wath.thymeleafdemo.repository.admin;

import com.wath.thymeleafdemo.model.Category;

import java.util.List;

public interface CategoryRepository {
    int save(Category category);
    List<Category> select();
    int delete(Category category);
}
