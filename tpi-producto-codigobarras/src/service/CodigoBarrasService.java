package service;

import entities.CodigoBarras;
import java.util.List;

public interface CodigoBarrasService {

    void crearCodigoBarras(CodigoBarras codigoBarras) throws Exception;

    void actualizarCodigoBarras(CodigoBarras codigoBarras) throws Exception;

    void eliminarCodigoBarras(int id) throws Exception; // eliminacion logica

    CodigoBarras obtenerPorId(int id) throws Exception;

    CodigoBarras obtenerPorValor(String valor) throws Exception;

    List<CodigoBarras> obtenerTodos() throws Exception;
}
