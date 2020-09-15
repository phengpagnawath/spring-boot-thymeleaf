package com.wath.thymeleafdemo.controller.admin;

import com.wath.thymeleafdemo.model.Role;
import com.wath.thymeleafdemo.service.admin.imp.AuthorityServiceImp;
import com.wath.thymeleafdemo.service.admin.imp.RoleServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {

    private final RoleServiceImp roleServiceImp;
    private final AuthorityServiceImp authorityServiceImp;


    String PATH = "admin/role";


    public RoleController(RoleServiceImp roleServiceImp, AuthorityServiceImp authorityServiceImp) {
        this.roleServiceImp = roleServiceImp;
        this.authorityServiceImp = authorityServiceImp;
    }

    @GetMapping
    public String roleManagement(ModelMap map){
        Role role = new Role();
        map.addAttribute("roles",roleServiceImp.listRole());
        map.addAttribute("role",role);
        map.addAttribute("authorities",authorityServiceImp.listAuthority());
        List<Integer> auths = new ArrayList<>();
        map.addAttribute("auths",auths);
        return PATH;
    }

    @PostMapping
    public String addRole(Role role, ModelMap map){

        return PATH;
    }
}
