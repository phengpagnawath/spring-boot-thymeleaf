CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS authorities (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS role_authority (
    role_id INTEGER NOT NULL,
    authority_id INTEGER NOT NULL,
    PRIMARY KEY (role_id, authority_id),
    CONSTRAINT fk_role_authority_role
        FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT fk_role_authority_authority
        FOREIGN KEY (authority_id) REFERENCES authorities(id)
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users_roles_user
        FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_users_roles_role
        FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS articles (
    id SERIAL PRIMARY KEY,
    article_id VARCHAR(100) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    thumbnail VARCHAR(500),
    author VARCHAR(255),
    category_id INTEGER NOT NULL,
    create_date DATE NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_articles_category
        FOREIGN KEY (category_id) REFERENCES categories(id)
);
