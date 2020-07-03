package com.wath.thymeleafdemo.service.admin;

import com.wath.thymeleafdemo.model.Category;

import java.util.List;

public interface CategoryService {
    Category save(Category category);
    List<Category> select();
    Category edit(Category newCategory);
    Category find(int id);
    void delete(int id);
}
