package dao;

import java.sql.Connection;
import java.util.List;

public interface GenericDao<T> {

    void insertar(T entidad, Connection conn) throws Exception;

    void actualizar(T entidad, Connection conn) throws Exception;

    void eliminar(int id, Connection conn) throws Exception;

    T obtenerPorId(int id, Connection conn) throws Exception;

    List<T> obtenerTodos(Connection conn) throws Exception;
}
