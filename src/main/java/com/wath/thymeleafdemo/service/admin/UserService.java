package com.wath.thymeleafdemo.service.admin;

import com.wath.thymeleafdemo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User save(User user);
    User getUser(String userID);
    User update(User newUser);
    void delete(String userID);
    List<User> search(String search);
}
