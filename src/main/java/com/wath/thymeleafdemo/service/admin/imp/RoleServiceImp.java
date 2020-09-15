package com.wath.thymeleafdemo.service.admin.imp;

import com.wath.thymeleafdemo.model.Role;
import com.wath.thymeleafdemo.repository.admin.mybatis.RoleRepository;
import com.wath.thymeleafdemo.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> listRole() {
        return roleRepository.roles();
    }

    @Override
    public Role addRole(Role role) {
        return null;
    }

    @Override
    public Role findRole(int id) {
        return roleRepository.role(id);
    }
}
