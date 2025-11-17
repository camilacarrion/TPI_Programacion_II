# Sistema de Gestión de Productos – TPI Programación II  

## Integrantes:
- Camila Carrión (Comisión 7)
- Pablo Osvaldo Cozzi (Comisión 3)

## 1. Descripción del dominio elegido

El dominio seleccionado para este trabajo es un Sistema de Gestión de Productos, donde cada producto posee un código de barras único asociado.  

El objetivo es modelar y desarrollar un sistema que permita administrar productos mediante las operaciones básicas CRUD, 
garantizando la integridad de los datos y aplicando una relación 1 a 1 entre: Producto y Código de Barras.
La relación es unidireccional desde Producto hacia CódigoBarras, lo que significa que cada Producto conoce su código de barras, pero el código no tiene referencia inversa.  

El sistema permite:
- Crear productos con su código de barras  
- Listar productos activos  
- Buscar productos por nombre  
- Eliminar productos (baja lógica)  
- Validar datos antes de guardar en la BD  
- Persistir la información en MySQL usando JDBC  

## 2. Requisitos y configuración de la base de datos

### Requisitos técnicos
- Java: versión 17 o superior  
- MySQL: versión 8 o superior  
- IDE recomendado: IntelliJ IDEA  
- Driver JDBC: incluido en el proyecto  
- Sistema operativo: Windows / Linux / MacOS  

## 2.1 Crear la base de datos
Ejecutar el siguiente script SQL en MySQL Workbench:

```sql
USE tpi_programacion;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS codigo_barras;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE codigo_barras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(255) NOT NULL UNIQUE,
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

## 3. Cómo compilar y ejecutar el proyecto

 3.1 Configurar la conexión a MySQL

Abrir el archivo:
src/config/DataBaseConfig.java

Y ajustar:

java
Copiar código
private static final String URL = "jdbc:mysql://localhost:3306/tpi_programacion";
private static final String USER = "root";     // Cambiar si usas otro usuario
private static final String PASSWORD = "";     // Agregar contraseña si aplica

 3.2 Compilar y ejecutar

En IntelliJ IDEA:
Abrir el proyecto
Ir a src/main/Main.java
Hacer clic en Run

 3.3 Credenciales de prueba
El sistema no usa login. Solo consulta directamente a la base local definida en DataBaseConfig.

 3.4 Flujo de uso del menú
Al ejecutar, aparece:

SISTEMA DE GESTIÓN DE PRODUCTOS
1. Crear producto
2. Listar productos
3. Buscar producto por nombre
4. Eliminar producto
5. Salir

 1. Crear producto
El sistema solicita:
Nombre
Precio
Stock
Código de barras
Si todo es válido:
Se crea el código de barras
Se crea el producto
Ambos quedan vinculados con relación 1→1

 2. Listar productos
Muestra todos los productos NO eliminados.

 3. Buscar por nombre
Filtra usando LIKE '%texto%'.

 4. Eliminar producto
Se realiza una baja lógica:

ini
Copiar código
eliminado = TRUE
El producto deja de mostrarse, pero permanece en BD.

 5. Salir
Finaliza la ejecución.
