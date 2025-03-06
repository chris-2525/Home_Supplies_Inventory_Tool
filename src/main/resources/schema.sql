CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    productName varchar(50) NOT NULL,
    minStock INT NOT NULL,
    currentStock INT NOT NULL
);