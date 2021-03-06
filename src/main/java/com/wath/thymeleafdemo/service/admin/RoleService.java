package com.wath.thymeleafdemo.service.admin;

import com.wath.thymeleafdemo.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> listRole();
    Role addRole(Role role);
    Role findRole(int id);
}
