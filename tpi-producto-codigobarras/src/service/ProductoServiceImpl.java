package service;

import config.DataBaseConfig;
import dao.CodigoBarrasDao;
import dao.CodigoBarrasDaoImpl;
import dao.ProductoDao;
import dao.ProductoDaoImpl;
import entities.CodigoBarras;
import entities.Producto;

import java.sql.Connection;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {

    private final ProductoDao productoDao = new ProductoDaoImpl();
    private final CodigoBarrasDao codigoBarrasDao = new CodigoBarrasDaoImpl();

    @Override
    public void crearProducto(Producto producto) throws Exception {

        Connection conn = null;

        try {
            conn = DataBaseConfig.getConnection();
            conn.setAutoCommit(false);   // INICIO DE TRANSACCION

            // Validar datos
            if (producto.getNombre() == null || producto.getNombre().trim().isEmpty())
                throw new Exception("El nombre del producto es obligatorio.");

            if (producto.getPrecio() == null || producto.getPrecio() <= 0)
                throw new Exception("El precio debe ser mayor a 0.");

            if (producto.getStock() == null || producto.getStock() < 0)
                throw new Exception("El stock no puede ser negativo.");

            // Insertar primero el Código de Barras
            CodigoBarras cb = producto.getCodigoBarras();
            if (cb == null)
                throw new Exception("El producto debe tener un codigo de barras asociado.");
            // Insertar
            codigoBarrasDao.insertar(cb, conn);
            // Revisar unicidad
            if (codigoBarrasDao.obtenerPorValor(cb.getNumero(), conn) == null)
                throw new Exception("El codigo de barras no se pudo registrar correctamente.");

            // Insertar Producto asociado
            productoDao.insertar(producto, conn);

            // Confirmar transaccion completa
            conn.commit();
            System.out.println("Transaccion: Producto + Código de Barras guardados.");

        } catch (Exception e) {

            if (conn != null) {
                conn.rollback();
                System.out.println("Rollback realizado por error: " + e.getMessage());
            }
            throw e;

        } finally {
            if (conn != null) conn.setAutoCommit(true);
            if (conn != null) conn.close();
        }
    }

    @Override
    public void actualizarProducto(Producto producto) throws Exception {

        Connection conn = null;

        try {
            conn = DataBaseConfig.getConnection();
            conn.setAutoCommit(false);

            // Validaciones
            if (producto.getNombre() == null || producto.getNombre().trim().isEmpty())
                throw new Exception("El nombre es obligatorio.");

            if (producto.getPrecio() <= 0)
                throw new Exception("El precio debe ser mayor a 0.");

            if (producto.getStock() < 0)
                throw new Exception("El stock no puede ser negativo.");

            // Actualizar codigo de barras si corresponde
            if (producto.getCodigoBarras() != null) {
                codigoBarrasDao.actualizar(producto.getCodigoBarras(), conn);
            }

            // Actualizar producto
            productoDao.actualizar(producto, conn);

            conn.commit();

        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw e;

        } finally {
            if (conn != null) conn.setAutoCommit(true);
            if (conn != null) conn.close();
        }
    }

    @Override
    public void eliminarProducto(int id) throws Exception {

        Connection conn = null;

        try {
            conn = DataBaseConfig.getConnection();
            conn.setAutoCommit(false);

            productoDao.eliminar(id, conn);

            conn.commit();

        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw e;

        } finally {
            if (conn != null) conn.setAutoCommit(true);
            if (conn != null) conn.close();
        }
    }

    @Override
    public Producto obtenerPorId(int id) throws Exception {
        try (Connection conn = DataBaseConfig.getConnection()) {
            return productoDao.obtenerPorId(id, conn);
        }
    }

    @Override
    public List<Producto> obtenerTodos() throws Exception {
        try (Connection conn = DataBaseConfig.getConnection()) {
            return productoDao.obtenerTodos(conn);
        }
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) throws Exception {
        try (Connection conn = DataBaseConfig.getConnection()) {
            return productoDao.buscarPorNombre(nombre, conn);
        }
    }
}
