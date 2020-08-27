package com.wath.thymeleafdemo.repository.admin.mybatis;

import com.wath.thymeleafdemo.model.Authority;
import com.wath.thymeleafdemo.model.Role;
import com.wath.thymeleafdemo.model.User;
import com.wath.thymeleafdemo.repository.admin.mybatis.provider.UserProvider;
import com.wath.thymeleafdemo.utils.Paging;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {


    @Update("update users set first_name = #{firstName} ," +
            "last_name = #{lastName}, email = #{email} where " +
            "user_id = #{userID} and status = true")
    boolean update(User newUser);

    @Update("update users set status = false where user_id = #{userID}")
    boolean delete(String userID);

    @SelectProvider(value = UserProvider.class, method = "searchUser")
    @Results(
            id = "userResult",value = {
                                        @Result(property = "id" , column = "id"),
                                        @Result(property = "userID" , column = "user_id"),
                                        @Result(property = "firstName",column = "first_name"),
                                        @Result(property = "lastName",column = "last_name"),
                                        @Result(property = "roles",column = "id",
            many = @Many(select = "selectRolesById"))
    })
    List<User> search(String search);

    @Select("select * from users where ( first_name ilike '%${keyword}%' " +
            "or last_name ilike '%${keyword}%' " +
            "or email ilike '%${keyword}%' ) " +
            "and status = true " +
            "order by id desc " +
            "OFFSET #{paging.offset} " +
            "LIMIT #{paging.limit} ")
    @ResultMap("userResult")
    List<User> getAllUsers(Paging paging,String keyword);

    @Select("select * from users Where "+
            "status = true ")
    @ResultMap("userResult")
    List<User> getUsers();



    @Select("select count(*) from users where ( first_name ilike '%${keyword}%' " +
            "or last_name ilike '%${keyword}%' " +
            "or email ilike '%${keyword}%' ) " +
            "and status = true")
    int countUser(String keyword);

    @Select("select * from users where user_id = #{userID} and status = true")
    @ResultMap("userResult")
    User getUser(String userID);


    @Insert("Insert into users (user_id,first_name,last_name,email,password) " +
            "values (#{userID},#{firstName},#{lastName},#{email},#{password})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    boolean save(User user);

    @UpdateProvider(value = UserProvider.class,method = "updatePassword")
    boolean updatePassword(String password,String userID);

    @Select("Select * from users where email = #{email} and status = true")
    @ResultMap("userResult")
    User getUserByEmail(String email);

    @Select("select r.id,r.name from roles r inner join users_roles ur on r.id = ur.role_id " +
            "where ur.user_id = #{id}")
    @Results(value = {
            @Result(property = "authorities",column = "id",many = @Many(select = "selectAuthorityByRole"))
    })
    List<Role> selectRolesById(int id);

    @Select("Select * from authorities a INNER JOIN role_authority ra ON ra.authority_id = a.id " +
            "WHERE ra.role_id = #{roleID} ")
    List<Authority> selectAuthorityByRole(int id);

    @Insert("insert into users_roles(user_id, role_id) VALUES (#{id},#{roles[0].id})")
    @ResultMap("userResult")
    boolean createUserRole(User user);

    @Update("update users_roles set role_id = #{roles[0].id} where user_id = #{id}")
    boolean updateUserRole(User user);
}
