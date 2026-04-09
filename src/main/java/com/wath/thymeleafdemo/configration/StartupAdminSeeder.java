package com.wath.thymeleafdemo.configration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class StartupAdminSeeder implements ApplicationRunner {

    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${app.seed.admin.email}")
    private String adminEmail;

    @Value("${app.seed.admin.password}")
    private String adminPassword;

    @Value("${app.seed.admin.first-name}")
    private String adminFirstName;

    @Value("${app.seed.admin.last-name}")
    private String adminLastName;

    @Value("${app.seed.admin.role}")
    private String adminRoleName;

    public StartupAdminSeeder(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        int roleId = ensureRole(adminRoleName);
        int[] authorityIds = new int[]{
                ensureAuthority("CATEGORY_"),
                ensureAuthority("ARTICLE_"),
                ensureAuthority("ROLE_"),
                ensureAuthority("USER_")
        };

        for (int authorityId : authorityIds) {
            ensureRoleAuthority(roleId, authorityId);
        }

        int userId = ensureAdminUser();
        ensureUserRole(userId, roleId);
    }

    private int ensureRole(String roleName) {
        Integer roleId = jdbcTemplate.query(
                "select id from roles where name = ?",
                rs -> rs.next() ? rs.getInt("id") : null,
                roleName
        );

        if (roleId != null) {
            return roleId;
        }

        return jdbcTemplate.query(
                "insert into roles(name) values (?) returning id",
                rs -> {
                    rs.next();
                    return rs.getInt("id");
                },
                roleName
        );
    }

    private int ensureAuthority(String authorityName) {
        Integer authorityId = jdbcTemplate.query(
                "select id from authorities where name = ?",
                rs -> rs.next() ? rs.getInt("id") : null,
                authorityName
        );

        if (authorityId != null) {
            return authorityId;
        }

        return jdbcTemplate.query(
                "insert into authorities(name) values (?) returning id",
                rs -> {
                    rs.next();
                    return rs.getInt("id");
                },
                authorityName
        );
    }

    private void ensureRoleAuthority(int roleId, int authorityId) {
        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from role_authority where role_id = ? and authority_id = ?",
                Integer.class,
                roleId,
                authorityId
        );

        if (count != null && count == 0) {
            jdbcTemplate.update(
                    "insert into role_authority(role_id, authority_id) values (?, ?)",
                    roleId,
                    authorityId
            );
        }
    }

    private int ensureAdminUser() {
        Integer userId = jdbcTemplate.query(
                "select id from users where email = ?",
                rs -> rs.next() ? rs.getInt("id") : null,
                adminEmail
        );

        if (userId != null) {
            jdbcTemplate.update(
                    "update users set status = true where id = ?",
                    userId
            );
            return userId;
        }

        return jdbcTemplate.query(
                "insert into users(user_id, first_name, last_name, email, password, status) " +
                        "values (?, ?, ?, ?, ?, true) returning id",
                rs -> {
                    rs.next();
                    return rs.getInt("id");
                },
                UUID.randomUUID().toString(),
                adminFirstName,
                adminLastName,
                adminEmail,
                passwordEncoder.encode(adminPassword)
        );
    }

    private void ensureUserRole(int userId, int roleId) {
        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from users_roles where user_id = ? and role_id = ?",
                Integer.class,
                userId,
                roleId
        );

        if (count != null && count == 0) {
            jdbcTemplate.update(
                    "insert into users_roles(user_id, role_id) values (?, ?)",
                    userId,
                    roleId
            );
        }
    }
}
