package com.wath.thymeleafdemo.repository.admin.mybatis;

import com.wath.thymeleafdemo.model.User;
import com.wath.thymeleafdemo.repository.admin.mybatis.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {


    @Update("update users set first_name = #{firstName} ," +
            "last_name = #{lastName}, email = #{email} , password = #{password} where" +
            "user_id = #{userID} and status = true")
    boolean update(User newUser);

    @Update("update users set status = false where user_id = #{userID}")
    boolean delete(String userID);

    @SelectProvider(value = UserProvider.class, method = "searchUser")
    @Results(
            id = "userResult",value = {
            @Result(property = "userID" , column = "user_id"),
            @Result(property = "firstName",column = "first_name"),
            @Result(property = "lastName",column = "last_name")
    })
    List<User> search(String search);

    @Select("select * from users where status = true")
    @ResultMap("userResult")
    List<User> getAllUsers();

    @Select("select * from users where user_id = #{userID} and status = true")
    @ResultMap("userResult")
    User getUser(String userID);

    @Insert("Insert into users (user_id,first_name,last_name,email,password) " +
            "values (#{userID},#{firstName},#{lastName},#{email},#{password})")
    boolean save(User user);

    @UpdateProvider(value = UserProvider.class,method = "updatePassword")
    boolean updatePassword(String password,String userID);
}
