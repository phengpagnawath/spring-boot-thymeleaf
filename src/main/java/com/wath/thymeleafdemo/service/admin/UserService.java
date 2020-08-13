package com.wath.thymeleafdemo.service.admin;

import com.wath.thymeleafdemo.model.User;
import com.wath.thymeleafdemo.utils.Paging;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers(Paging paging,String keyword);
    List<User> getUsers();
    User save(User user);
    User getUser(String userID);
    User update(User newUser);
    void delete(String userID);
    List<User> search(String search);
    void updatePassword(String password,String userID);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
