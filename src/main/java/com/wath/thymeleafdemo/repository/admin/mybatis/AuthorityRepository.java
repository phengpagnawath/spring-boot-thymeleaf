package com.wath.thymeleafdemo.repository.admin.mybatis;

import com.wath.thymeleafdemo.model.Authority;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository {

    @Select("Select * from authorities")
    List<Authority> listAuthority();

}
