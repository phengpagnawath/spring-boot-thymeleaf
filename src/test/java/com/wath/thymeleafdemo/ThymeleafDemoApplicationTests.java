package com.wath.thymeleafdemo;

import com.wath.thymeleafdemo.model.User;
import com.wath.thymeleafdemo.repository.admin.mybatis.UserRepository;
import com.wath.thymeleafdemo.service.admin.imp.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@SpringBootTest
class ThymeleafDemoApplicationTests {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
//        User user = new User();
//
//        user.setUserID(UUID.randomUUID().toString());
//        user.setFirstName("Pheng");
//        user.setLastName("Pagnavoin");
//        user.setEmail("PhengPagnavoin@gmail.com");
//        user.setPassword("12345");
//
//        userServiceImp.save(user);
//
//        System.out.println("List all user:"+userServiceImp.getAllUsers());
//        System.out.println("User by ID :"+userServiceImp.getUser("fdd768fd-77c5-4ec1-9133-13f7d23856d0"));
//        System.out.println("search pheng:" +userServiceImp.search("%Phen%"));
//        System.out.println(userServiceImp.search("%Pagnawa%"));
//
//

//        userServiceImp.updatePassword("456","fdd768fd-77c5-4ec1-9133-13f7d23856d0");
    }

    @Test
    void test(){
        System.out.println(bCryptPasswordEncoder.encode("wath@123"));
        System.out.println(userRepository.selectRolesById(1));
    }

}
