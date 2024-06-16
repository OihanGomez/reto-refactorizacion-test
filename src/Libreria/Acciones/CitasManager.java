package Libreria.Acciones;

import Libreria.objetos.Cita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CitasManager {
    private ConexionBD conexionBD;

    public CitasManager() {
        conexionBD = new ConexionBD();
    }

    public void reservarCita(Cita cita) throws Exception {
        Connection connection = conexionBD.getConexion();
        String checkQuery = "SELECT COUNT(*) FROM CITAS WHERE EMAIL = ? AND FECHA = ?";
        String insertQuery = "INSERT INTO CITAS (EMAIL, FECHA) VALUES (?, ?)";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setString(1, cita.getEmail());
            checkStmt.setDate(2, java.sql.Date.valueOf(cita.getFecha()));

            try (ResultSet rs = checkStmt.executeQuery()) {
                rs.next();
                if (rs.getInt(1) > 0) {
                    throw new Exception("La fecha ya está ocupada para este usuario.");
                }
            }
        }

        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
            insertStmt.setString(1, cita.getEmail());
            insertStmt.setDate(2, java.sql.Date.valueOf(cita.getFecha()));
            insertStmt.executeUpdate();
        }
    }

    public boolean isFechaOcupada(LocalDate fecha) throws SQLException {
        Connection connection = conexionBD.getConexion();
        String query = "SELECT COUNT(*) FROM CITAS WHERE FECHA = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, java.sql.Date.valueOf(fecha));

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }
}
