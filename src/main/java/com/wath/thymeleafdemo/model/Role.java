package com.wath.thymeleafdemo.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable, GrantedAuthority {
    private int id;
    private String name;
    private List<Authority> authorities;

    public Role() {
    }

    public Role(int id, String name, List<Authority> authorities) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
