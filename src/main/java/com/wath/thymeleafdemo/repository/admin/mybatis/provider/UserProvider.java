package com.wath.thymeleafdemo.repository.admin.mybatis.provider;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    public String insertUser(){
        return new SQL(){
            {
                INSERT_INTO("users");
                VALUES("user_id","#{userID}");
                VALUES("first_name","#{firstName}");
                VALUES("last_name","#{lastName}");
                VALUES("email","#{email}");
                VALUES("password","#{password}");
            }
        }.toString();
    }

    public String updatePassword(String password,String userID){
        return new SQL(){
            {
                UPDATE("users");
                SET("password = #{password}");
                WHERE("user_id = #{userID}");
            }
        }.toString();
    }
}
