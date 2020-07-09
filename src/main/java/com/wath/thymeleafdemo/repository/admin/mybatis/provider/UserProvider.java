package com.wath.thymeleafdemo.repository.admin.mybatis.provider;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.data.relational.core.sql.Where;

public class UserProvider {

    public String searchUser(String search){
        return new SQL(){{
            SELECT("*");
            FROM("users");
            WHERE("first_name like #{search}");
            OR();
            WHERE("last_name like #{search}");
            AND();
            WHERE("status = true");
        }}.toString();
    }
}
