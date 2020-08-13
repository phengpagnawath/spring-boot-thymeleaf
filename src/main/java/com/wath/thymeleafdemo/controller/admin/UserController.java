package com.wath.thymeleafdemo.controller.admin;

import com.wath.thymeleafdemo.model.User;
import com.wath.thymeleafdemo.service.admin.imp.CategoryServiceImp;
import com.wath.thymeleafdemo.service.admin.imp.RoleServiceImp;
import com.wath.thymeleafdemo.service.admin.imp.UserServiceImp;
import com.wath.thymeleafdemo.utils.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private final String URL = "/admin/user";
    private final String PATH = "admin/user";
    private final String PATH_VIEW="admin/user-view";
    private final String URL_VIEW="/admin/user/view";

    @Autowired
    public UserController(UserServiceImp userServiceImp,RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/view")
    public String userView(ModelMap map, @ModelAttribute User user, Paging paging,@RequestParam(required = false) String keyword){
//
        if (keyword ==null)
            keyword ="";
        map.addAttribute("user",user);
        List<User> users = userServiceImp.getAllUsers(paging,keyword);
        map.addAttribute("paging",paging);
        map.addAttribute("users",users);
        return PATH_VIEW;
    }

    @GetMapping("/add")
    public String addUserView(ModelMap map, User user){
        map.addAttribute("user",user);
        map.addAttribute("roles",roleServiceImp.listRole());
        return PATH;
    }

    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult result, ModelMap map
            ,RedirectAttributes redirect){
        //System.out.println(result.toString());
        if (result.hasErrors()) {
            return addUserView(map,user);
        }

        if(!user.getPassword().matches(user.getConfirmPassword())){
            result.rejectValue("confirmPassword","error.user","password not match");
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userServiceImp.save(user);
        redirect.addFlashAttribute("success",true);
        redirect.addFlashAttribute("message","User create successfully!");
        return "redirect:" + URL_VIEW;
    }


    @GetMapping("/{id}/delete")
    public String deleteUserAction(@PathVariable String id){
        userServiceImp.delete(id);
        return "redirect:"+URL_VIEW;
    }

    @GetMapping("update/{id}")
    public String updateUser(ModelMap map, @PathVariable String id){
        map.addAttribute("user",userServiceImp.getUser(id));
        map.addAttribute("roles",roleServiceImp.listRole());
        map.addAttribute("isEdit",true);
        return PATH;
    }

    @PostMapping("/update/{userID}")
    public String updateUserAction(User newUser){
        userServiceImp.update(newUser);
        return "redirect:" + URL_VIEW;
    }
}
