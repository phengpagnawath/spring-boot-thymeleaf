package com.wath.thymeleafdemo.service.admin.imp;

import com.wath.thymeleafdemo.model.User;
import com.wath.thymeleafdemo.repository.admin.mybatis.UserRepository;
import com.wath.thymeleafdemo.service.admin.UserService;
import com.wath.thymeleafdemo.utils.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers(Paging paging,String keyword) {
        System.out.println(userRepository.countUser(keyword));
        paging.setTotalCount(userRepository.countUser(keyword));
        return userRepository.getAllUsers(paging,keyword);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user) ? user : null;
    }

    @Override
    public User getUser(String userID) {
        return userRepository.getUser(userID);
    }

    @Override
    public User update(User newUser) {
        return userRepository.update(newUser) ? newUser : null;
    }

    @Override
    public void delete(String userID) {
        userRepository.delete(userID);
    }

    @Override
    public List<User> search(String search) {
        return userRepository.search(search);
    }

    @Override
    public void updatePassword(String password, String userID) {
        userRepository.updatePassword(password,userID);
    }
}

