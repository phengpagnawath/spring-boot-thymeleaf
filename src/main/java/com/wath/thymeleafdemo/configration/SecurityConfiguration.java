package com.wath.thymeleafdemo.configration;

import com.wath.thymeleafdemo.model.User;
import com.wath.thymeleafdemo.service.admin.imp.UserServiceImp;
import com.wath.thymeleafdemo.utils.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private CustomAuthenticationSuccessHandler successHandler;
    private UserServiceImp userServiceImp;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Autowired
    public void setSuccessHandler(CustomAuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*List<User> userList = userServiceImp.getUsers();
        for (User user:userList) {
            auth.inMemoryAuthentication().withUser(user.getEmail())
                    .password("{noop}"+user.getPassword())
                    .roles("USER");
        }
        auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN");*/
        //auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN");
        auth.userDetailsService(userServiceImp).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").successHandler(successHandler).permitAll();
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
        http.authorizeRequests()
                .antMatchers("/admin/categories/**").hasAnyAuthority("CATEGORY_")
                .antMatchers("/admin/articles/**").hasAnyAuthority("ARTICLE_")
                .antMatchers("/admin/role/**").hasAnyAuthority("ROLE_")
                .antMatchers("/admin/user/**").hasAnyAuthority("USER_")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/css/**","/img/**","/js/**","/webfonts/**","/images/**");
    }
}
