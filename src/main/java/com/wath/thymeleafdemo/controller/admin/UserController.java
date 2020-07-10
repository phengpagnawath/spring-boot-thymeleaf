package com.wath.thymeleafdemo.controller.admin;

import com.wath.thymeleafdemo.model.User;
import com.wath.thymeleafdemo.service.admin.imp.CategoryServiceImp;
import com.wath.thymeleafdemo.service.admin.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private UserServiceImp userServiceImp;
    private final String URL = "/admin/user";
    private final String PATH = "admin/user";
    private final String PATH_VIEW="admin/user-view";

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/view")
    public String userView(ModelMap map,@ModelAttribute User user){
        map.addAttribute("user",user);
        List<User> users = userServiceImp.getAllUsers();
        map.addAttribute("users",users);
        return PATH_VIEW;
    }

    @GetMapping("/add")
    public String addUserView(ModelMap map,@ModelAttribute User user){
        map.addAttribute("user",user);
        return PATH;
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute User user, BindingResult result
            ,RedirectAttributes redirect, ModelMap map){
        System.out.println(result.toString());
        if (result.hasErrors()) {
            return addUserView(map,user);
        }

        if(!user.getPassword().matches(user.getConfirmPassword())){
            result.rejectValue("confirmPassword","error.user","password not match ..!");
            return addUserView(map,user);
        }
        //If you want Message error show in model
        /*
        if(!user.getPassword().matches(user.getConfirmPassword())){

            redirect.addFlashAttribute("pwdError",true);
            redirect.addFlashAttribute("user",user);
            redirect.addFlashAttribute("message","password not match...!");
            return "redirect:" + URL + "/add";
        }
        */
        user.setUserID(UUID.randomUUID().toString());
        userServiceImp.save(user);
        redirect.addFlashAttribute("success",true);
        redirect.addFlashAttribute("message","User create successfully!");
        return "redirect:" + URL + "/add";
    }
}
