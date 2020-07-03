package com.wath.thymeleafdemo.service.admin.imp;

import com.wath.thymeleafdemo.model.Category;
import com.wath.thymeleafdemo.repository.admin.imp.CategoryRepositoryImp;
import com.wath.thymeleafdemo.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private CategoryRepositoryImp categoryRepositoryImp;

    @Autowired
    public CategoryServiceImp(CategoryRepositoryImp categoryRepositoryImp) {
        this.categoryRepositoryImp = categoryRepositoryImp;
    }

    @Override
    public Category save(Category category) {
        if(categoryRepositoryImp.save(category)==1){
            return category;
        }
        return null;
    }

    @Override
    public List<Category> select() {
        List<Category> categories=categoryRepositoryImp.select();
        if(categories!=null){
            return categories;
        }
        return null;
    }
}
