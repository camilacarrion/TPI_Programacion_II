package dao;

import entities.CodigoBarras;
import java.sql.Connection;
import java.util.List;

public interface CodigoBarrasDao extends GenericDao<CodigoBarras> {

    CodigoBarras obtenerPorValor(String valor, Connection conn) throws Exception;
}
