package com.wath.thymeleafdemo.repository.admin.mybatis;

import com.wath.thymeleafdemo.model.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository {

    @Select("select * from roles")
    List<Role> roles();
}
