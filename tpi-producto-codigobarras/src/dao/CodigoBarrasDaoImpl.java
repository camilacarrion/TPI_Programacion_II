package dao;

import entities.CodigoBarras;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CodigoBarrasDaoImpl implements CodigoBarrasDao {

    @Override
    public void insertar(CodigoBarras codigo, Connection conn) throws Exception {
        String sql = "INSERT INTO codigo_barras (numero, eliminado) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, codigo.getNumero());
            stmt.setBoolean(2, codigo.isEliminado());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                codigo.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public void actualizar(CodigoBarras codigo, Connection conn) throws Exception {
        String sql = "UPDATE codigo_barras SET numero = ?, eliminado = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo.getNumero());
            stmt.setBoolean(2, codigo.isEliminado());
            stmt.setInt(3, codigo.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id, Connection conn) throws Exception {
        String sql = "UPDATE codigo_barras SET eliminado = TRUE WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public CodigoBarras obtenerPorId(int id, Connection conn) throws Exception {
        String sql = "SELECT id, numero, eliminado FROM codigo_barras WHERE id = ? AND eliminado = FALSE";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CodigoBarras(
                        rs.getInt("id"),
                        rs.getString("numero"),
                        rs.getBoolean("eliminado")
                );
            }
            return null;
        }
    }

    @Override
    public List<CodigoBarras> obtenerTodos(Connection conn) throws Exception {
        String sql = "SELECT id, numero, eliminado FROM codigo_barras WHERE eliminado = FALSE";

        List<CodigoBarras> lista = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new CodigoBarras(
                        rs.getInt("id"),
                        rs.getString("numero"),
                        rs.getBoolean("eliminado")
                ));
            }
        }

        return lista;
    }

    @Override
    public CodigoBarras obtenerPorValor(String valor, Connection conn) throws Exception {
        String sql = "SELECT id, numero, eliminado FROM codigo_barras WHERE numero = ? AND eliminado = FALSE";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, valor);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CodigoBarras(
                        rs.getInt("id"),
                        rs.getString("numero"),
                        rs.getBoolean("eliminado")
                );
            }
            return null;
        }
    }
}
