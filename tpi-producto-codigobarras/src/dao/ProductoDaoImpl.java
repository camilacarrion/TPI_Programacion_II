package dao;

import entities.CodigoBarras;
import entities.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoImpl implements ProductoDao {

    @Override
    public void insertar(Producto producto, Connection conn) throws Exception {

        String sql = """
            INSERT INTO productos (nombre, precio, stock, codigo_barras_id, eliminado)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getStock());

            if (producto.getCodigoBarras() != null) {
                stmt.setInt(4, producto.getCodigoBarras().getId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.setBoolean(5, producto.isEliminado());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                producto.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public void actualizar(Producto producto, Connection conn) throws Exception {

        String sql = """
            UPDATE productos
            SET nombre = ?, precio = ?, stock = ?, codigo_barras_id = ?, eliminado = ?
            WHERE id = ?
            """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getStock());

            if (producto.getCodigoBarras() != null) {
                stmt.setInt(4, producto.getCodigoBarras().getId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.setBoolean(5, producto.isEliminado());
            stmt.setInt(6, producto.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id, Connection conn) throws Exception {
        String sql = "UPDATE productos SET eliminado = TRUE WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Producto obtenerPorId(int id, Connection conn) throws Exception {
        String sql = """
            SELECT p.id, p.nombre, p.precio, p.stock, p.eliminado,
                   cb.id AS cb_id, cb.numero
            FROM productos p
            LEFT JOIN codigo_barras cb ON p.codigo_barras_id = cb.id
            WHERE p.id = ? AND p.eliminado = FALSE
            """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                CodigoBarras cb = null;
                int cbId = rs.getInt("cb_id");
                if (!rs.wasNull()) {
                    cb = new CodigoBarras(cbId, rs.getString("numero"), false);
                }

                return new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        cb,
                        rs.getBoolean("eliminado")
                );
            }
            return null;
        }
    }

    @Override
    public List<Producto> obtenerTodos(Connection conn) throws Exception {

        String sql = """
            SELECT p.id, p.nombre, p.precio, p.stock, p.eliminado,
                   cb.id AS cb_id, cb.numero
            FROM productos p
            LEFT JOIN codigo_barras cb ON p.codigo_barras_id = cb.id
            WHERE p.eliminado = FALSE
            ORDER BY p.id
            """;

        List<Producto> lista = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                CodigoBarras cb = null;
                int cbId = rs.getInt("cb_id");
                if (!rs.wasNull()) {
                    cb = new CodigoBarras(cbId, rs.getString("numero"), false);
                }

                lista.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        cb,
                        rs.getBoolean("eliminado")
                ));
            }
        }

        return lista;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre, Connection conn) throws Exception {

        String sql = """
            SELECT p.id, p.nombre, p.precio, p.stock, p.eliminado,
                   cb.id AS cb_id, cb.numero
            FROM productos p
            LEFT JOIN codigo_barras cb ON p.codigo_barras_id = cb.id
            WHERE p.eliminado = FALSE AND p.nombre LIKE ?
            """;

        List<Producto> lista = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                CodigoBarras cb = null;
                int cbId = rs.getInt("cb_id");
                if (!rs.wasNull()) {
                    cb = new CodigoBarras(cbId, rs.getString("numero"), false);
                }

                lista.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        cb,
                        rs.getBoolean("eliminado")
                ));
            }
        }

        return lista;
    }
}
