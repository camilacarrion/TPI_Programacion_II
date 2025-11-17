USE tpi_programacion;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS codigo_barras;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE codigo_barras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(255) NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE
);

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    codigo_barras_id INT UNIQUE,
    eliminado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (codigo_barras_id) REFERENCES codigo_barras(id)
);

INSERT INTO codigo_barras (numero) VALUES ('12345');

INSERT INTO productos (nombre, precio, stock, codigo_barras_id)
VALUES ('Producto test', 100, 5, 1);

SELECT * FROM productos;
SELECT * FROM codigo_barras;

