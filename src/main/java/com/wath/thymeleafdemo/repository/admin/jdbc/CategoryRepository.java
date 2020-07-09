package com.wath.thymeleafdemo.repository.admin.jdbc;

import com.wath.thymeleafdemo.model.Category;

import java.util.List;

public interface CategoryRepository {
    int save(Category category);
    List<Category> select();
    int delete(int id);
    int edit(Category newCategory);
    Category find(int id);
}
