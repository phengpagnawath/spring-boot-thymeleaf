package com.wath.thymeleafdemo.configration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DBConfigration {

    @Bean
    public DataSource con(){
        DataSourceBuilder builder =DataSourceBuilder.create();
        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql://localhost:5432/article_mgt");
        builder.username("postgres");
        builder.password("1234");
        return builder.build();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("schema.sql"));

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator);
        return initializer;
    }
}
