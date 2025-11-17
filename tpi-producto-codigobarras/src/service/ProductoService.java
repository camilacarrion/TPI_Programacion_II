package service;

import entities.Producto;
import java.util.List;

public interface ProductoService {

    void crearProducto(Producto producto) throws Exception;

    void actualizarProducto(Producto producto) throws Exception;

    void eliminarProducto(int id) throws Exception; // eliminacion logica

    Producto obtenerPorId(int id) throws Exception;

    List<Producto> obtenerTodos() throws Exception;

    List<Producto> buscarPorNombre(String nombre) throws Exception;
}
