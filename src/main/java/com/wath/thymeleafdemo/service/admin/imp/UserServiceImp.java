package com.wath.thymeleafdemo.service.admin.imp;

import com.wath.thymeleafdemo.model.User;
import com.wath.thymeleafdemo.repository.admin.mybatis.UserRepository;
import com.wath.thymeleafdemo.service.admin.UserService;
import com.wath.thymeleafdemo.utils.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        paging.setTotalCount(userRepository.countUser(keyword));
        return userRepository.getAllUsers(paging,keyword);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User save(User user) {
        if(userRepository.save(user))
            if(userRepository.createUserRole(user))
                return user;
        return null;
    }

    @Override
    public User getUser(String userID) {
        return userRepository.getUser(userID);
    }

    @Override
    public User update(User newUser) {
        if(userRepository.update(newUser))
            if(userRepository.updateUserRole(newUser))
                return newUser;
        return null;
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(userRepository.getUserByEmail(s));
        return userRepository.getUserByEmail(s);
    }
}

