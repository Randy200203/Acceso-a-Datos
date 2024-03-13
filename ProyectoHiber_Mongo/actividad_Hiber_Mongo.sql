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


