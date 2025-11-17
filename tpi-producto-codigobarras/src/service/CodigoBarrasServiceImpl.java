package service;

import config.DataBaseConfig;
import dao.CodigoBarrasDao;
import dao.CodigoBarrasDaoImpl;
import entities.CodigoBarras;

import java.sql.Connection;
import java.util.List;

public class CodigoBarrasServiceImpl implements CodigoBarrasService {

    private final CodigoBarrasDao codigoBarrasDao = new CodigoBarrasDaoImpl();

    @Override
    public CodigoBarras obtenerPorId(int id) throws Exception {
        try (Connection conn = DataBaseConfig.getConnection()) {
            return codigoBarrasDao.obtenerPorId(id, conn);
        }
    }

    @Override
    public CodigoBarras obtenerPorValor(String valor) throws Exception {
        try (Connection conn = DataBaseConfig.getConnection()) {
            return codigoBarrasDao.obtenerPorValor(valor, conn);
        }
    }

    @Override
    public List<CodigoBarras> obtenerTodos() throws Exception {
        try (Connection conn = DataBaseConfig.getConnection()) {
            return codigoBarrasDao.obtenerTodos(conn);
        }
    }

    @Override
    public void actualizarCodigoBarras(CodigoBarras codigo) throws Exception {
        try (Connection conn = DataBaseConfig.getConnection()) {
            codigoBarrasDao.actualizar(codigo, conn);
        }
    }

    @Override
    public void eliminarCodigoBarras(int id) throws Exception {
        try (Connection conn = DataBaseConfig.getConnection()) {
            codigoBarrasDao.eliminar(id, conn);
        }
    }


    @Override
    public void crearCodigoBarras(CodigoBarras codigo) throws Exception {
        throw new Exception("El codigo de barras solo puede crearse dentro de ProductoService (transacción).");
    }
}
