package com.wath.thymeleafdemo.service.admin.imp;

import com.wath.thymeleafdemo.model.Authority;
import com.wath.thymeleafdemo.repository.admin.mybatis.AuthorityRepository;
import com.wath.thymeleafdemo.service.admin.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImp implements AuthorityService {

    AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImp(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> listAuthority() {
        return authorityRepository.listAuthority();
    }

}
