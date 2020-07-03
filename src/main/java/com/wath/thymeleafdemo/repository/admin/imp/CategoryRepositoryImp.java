package com.wath.thymeleafdemo.repository.admin.imp;

import com.wath.thymeleafdemo.model.Category;
import com.wath.thymeleafdemo.repository.admin.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
                "select * from categories order by id DESC",
                (rs, rowNum) -> new Category(
                        rs.getInt("id"), rs.getString("name"), rs.getBoolean("status")
                )
        );
    }

    @Override
    public int delete(int id) {
        String sql = "delete from categories where id =" + id;
        return jdbcTemplate.update(sql);
    }

    @Override
    public int edit(Category newCategory) {
        String sql = "update categories set name ='"+newCategory.getName() + "' where id ="+ newCategory.getId();
        return jdbcTemplate.update(sql);
    }

    @Override
    public Category find(int id) {
        String sql = "Select * from categories where id ="+id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Category>() {
            @Override
            public Category extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    return new Category(rs.getInt("id")
                            , rs.getString("name")
                            , rs.getBoolean("status"));
                }else {
                    return null;
                }
            }
        });
    }
}
