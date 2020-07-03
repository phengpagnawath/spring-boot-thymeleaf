package com.wath.thymeleafdemo.repository.admin.imp;

import com.wath.thymeleafdemo.model.Category;
import com.wath.thymeleafdemo.repository.admin.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImp implements CategoryRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Category category) {
        String sql = "insert into categories (name) values ('"+category.getName()+"')";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Category> select() {
       return jdbcTemplate.query(
                "select * from categories",
                (rs, rowNum) -> new Category(
                        rs.getInt("id"), rs.getString("name"), rs.getBoolean("status")
                )
        );
    }

    @Override
    public int delete(Category category) {
        String sql = "delete from categories where id =" + category.getId();
        return jdbcTemplate.update(sql);
    }
}
