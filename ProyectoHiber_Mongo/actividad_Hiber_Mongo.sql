create database actividad_Hiber_Mongo;
use actividad_Hiber_Mongo;
CREATE TABLE Zapatos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    precio DECIMAL(10, 2),
    marca VARCHAR(100),
    color VARCHAR(50),
    material VARCHAR(100)
);

CREATE TABLE Coches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
	precio DECIMAL(10, 2),
    modelo VARCHAR(100),
	kilometraje INT,
    color VARCHAR(50)
);
CREATE TABLE Smartphones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    modelo VARCHAR(100),
    sistema_operativo VARCHAR(50),
    precio DECIMAL(10, 2),
    memoria_ram INT
);

CREATE TABLE Ropas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    marca VARCHAR(100),
    talla VARCHAR(20),
    color VARCHAR(50),
    precio DECIMAL(10, 2)
);

INSERT INTO Zapatos (nombre, precio, marca, color, material) 
VALUES ('Zapato deportivo', 89.99, 'Nike', 'Negro', 'Piel sintética'),
       ('Zapatilla casual', 59.99, 'Adidas', 'Blanco', 'Tela'),
       ('Bota de montaña', 129.99, 'Merrell', 'Gris', 'Cuero'),
       ('Sandalia veraniega', 39.99, 'Crocs', 'Azul', 'Plástico'),
       ('Zapato elegante', 99.99, 'Clarks', 'Marrón', 'Cuero');

INSERT INTO Coches (nombre, precio, modelo, kilometraje, color)
VALUES ('Toyota Corolla', 25000.00, 'Sedan', 15000, 'Gris'),
       ('Ford Mustang', 45000.00, 'Deportivo', 5000, 'Rojo'),
       ('Volkswagen Golf', 30000.00, 'Compacto', 20000, 'Blanco'),
       ('BMW X5', 60000.00, 'SUV', 10000, 'Negro'),
       ('Tesla Model S', 80000.00, 'Eléctrico', 5000, 'Azul');

INSERT INTO Smartphones (nombre, modelo, sistema_operativo, precio, memoria_ram)
VALUES ('Samsung Galaxy S21', 'S21', 'Android', 999.99, 8),
       ('iPhone 12', '12', 'iOS', 1099.99, 6),
       ('OnePlus 9 Pro', '9 Pro', 'Android', 899.99, 12),
       ('Google Pixel 5', 'Pixel 5', 'Android', 699.99, 8),
       ('Xiaomi Mi 11', 'Mi 11', 'Android', 799.99, 8);

INSERT INTO Ropas (nombre, marca, talla, color, precio)
VALUES ('Camiseta básica', 'H&M', 'M', 'Blanco', 14.99),
       ('Jeans ajustados', 'Levi\'s', '32/32', 'Azul', 49.99),
       ('Chaqueta de invierno', 'North Face', 'L', 'Negro', 149.99),
       ('Vestido de fiesta', 'Zara', 'S', 'Rojo', 79.99),
       ('Sudadera con capucha', 'Nike', 'XL', 'Gris', 39.99);
