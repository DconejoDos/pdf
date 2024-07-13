package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class PruebaDAO {
    private static final Logger logger = LoggerFactory.getLogger(PruebaDAO.class);

    public void obtenerAtributos() {
        String query = "SELECT * FROM prueba";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                // Loguear los atributos
                logger.debug("ID: {}, Nombre: {}", id, nombre);
            }

        } catch (SQLException e) {
            logger.error("Error al obtener atributos", e);
        }
    }
    public Prueba obtenerPorId(int id) {
        String query = "SELECT * FROM prueba WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idResultado = rs.getInt("id");
                String nombre = rs.getString("nombre");

                logger.debug("Registro encontrado - ID: {}, Nombre: {}", idResultado, nombre);
                return new Prueba(idResultado, nombre);
            } else {
                logger.warn("No se encontró un registro con ID: {}", id);
            }

        } catch (SQLException e) {
            logger.error("Error al obtener el registro por ID", e);
        }
        return null;
    }
}
