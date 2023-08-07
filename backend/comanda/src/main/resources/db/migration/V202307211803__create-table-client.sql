CREATE TABLE clients(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_created_id INT NOT NULL,
    user_updated_id INT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    phone VARCHAR(36) UNIQUE NOT NULL
);