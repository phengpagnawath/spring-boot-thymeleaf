package com.wath.thymeleafdemo.service.admin;

import com.wath.thymeleafdemo.model.User;
import com.wath.thymeleafdemo.utils.Paging;

import java.util.List;

public interface UserService {
    List<User> getAllUsers(Paging paging,String keyword);
    User save(User user);
    User getUser(String userID);
    User update(User newUser);
    void delete(String userID);
    List<User> search(String search);
    void updatePassword(String password,String userID);
}
