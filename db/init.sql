CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255)
);
CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT,
                        product VARCHAR(255),
                        price DOUBLE
);
ALTER  TABLE  orders ADD COLUMN total DOUBLE AFTER price;
ALTER  TABLE  users ADD COLUMN password VARCHAR(255) AFTER email;
