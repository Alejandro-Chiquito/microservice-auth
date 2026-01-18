CREATE DATABASE ms-auth;

CREATE TABLE user (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    first_names VARCHAR(50) NOT NULL,
    last_names VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    salary DECIMAL(8,2) NOT NULL CHECK (salary >= 0) CHECK (salary <= 1500000),
    password VARCHAR(100) NOT NULL,
    role_id BIGINT NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(100)
);

CREATE TABLE direction (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(100) NOT NULL,
    street_number VARCHAR(10) NOT NULL,
    apartment VARCHAR(10),
    neighborhood VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    postal_code INT NOT NULL,
    country VARCHAR(50) NOT NULL,
    additional_info VARCHAR(100),
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6),
    user_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);