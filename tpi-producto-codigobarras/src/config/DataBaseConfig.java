package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConfig {

    private static final String URL = "jdbc:mysql://localhost:3306/tpi_programacion";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
