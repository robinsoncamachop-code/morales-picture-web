CREATE DATABASE IF NOT EXISTS morales_picture_db;

USE morales_picture_db;

CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    correo VARCHAR(120) NOT NULL,
    servicio VARCHAR(100) NOT NULL
);

INSERT INTO cliente (nombre, telefono, correo, servicio) VALUES
('Juan Morales', '3001112233', 'juan@correo.com', 'Sesión familiar'),
('Paula Gómez', '3014445566', 'paula@correo.com', 'Evento social'),
('María Torres', '3027778899', 'maria@correo.com', 'Retrato profesional');
