package Libreria.Acciones;

import Libreria.objetos.Cita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Clase que gestiona las operaciones relacionadas con las citas en la librería.
 */
public class CitasManager {
    private ConexionBD conexionBD;

    /**
     * Constructor de la clase CitasManager.
     * Inicializa la conexión a la base de datos mediante un objeto ConexionBD.
     */
    public CitasManager() {
        conexionBD = new ConexionBD();
    }

    /**
     * Reserva una cita para un usuario en la base de datos.
     *
     * @param cita Objeto Cita que contiene la información de la cita a reservar.
     * @throws Exception Si la fecha de la cita ya está ocupada para el usuario especificado.
     */
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

    /**
     * Verifica si una fecha específica está ocupada para citas en la base de datos.
     *
     * @param fecha Fecha para la cual se desea verificar la disponibilidad.
     * @return true si la fecha está ocupada por al menos una cita, false si está disponible.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
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
