CREATE TABLE users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_created_id INT NOT NULL,
    user_updated_id INT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    username VARCHAR(36) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);