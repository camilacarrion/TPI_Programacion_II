package dao;

import entities.Producto;
import java.sql.Connection;
import java.util.List;

public interface ProductoDao extends GenericDao<Producto> {

    List<Producto> buscarPorNombre(String nombre, Connection conn) throws Exception;
}
