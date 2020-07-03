package com.wath.thymeleafdemo.configration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfigration {

    @Bean
    public DataSource con(){
        DataSourceBuilder builder =DataSourceBuilder.create();
        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql://localhost:5432/article_mgt");
        builder.username("postgres");
        builder.password("12345");
        return builder.build();
    }
}
