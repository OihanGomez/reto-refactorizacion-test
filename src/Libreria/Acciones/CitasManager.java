package Libreria.Acciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La clase CitasManager se encarga de gestionar las citas en la base de datos.
 */
public class CitasManager {
    private ConexionBD conexionBD;

    /**
     * Constructor de la clase CitasManager.
     * Inicializa la conexión a la base de datos.
     */
    public CitasManager() {
        conexionBD = new ConexionBD();
    }

    /**
     * Reserva una cita para un usuario en una fecha específica.
     *
     * @param email El correo electrónico del usuario.
     * @param fecha La fecha de la cita en formato 'YYYY/MM/DD'.
     * @throws Exception Si la fecha ya está ocupada para el usuario.
     */
    public void reservarCita(String email, String fecha) throws Exception {
        Connection connection = conexionBD.getConexion();
        String checkQuery = "SELECT COUNT(*) FROM CITAS WHERE EMAIL = ? AND FECHA = TO_DATE(?, 'YYYY/MM/DD')";
        String insertQuery = "INSERT INTO CITAS (EMAIL, FECHA) VALUES (?, TO_DATE(?, 'YYYY/MM/DD'))";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setString(1, email);
            checkStmt.setString(2, fecha);

            try (ResultSet rs = checkStmt.executeQuery()) {
                rs.next();
                if (rs.getInt(1) > 0) {
                    throw new Exception("La fecha ya está ocupada para este usuario.");
                }
            }
        }

        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
            insertStmt.setString(1, email);
            insertStmt.setString(2, fecha);
            insertStmt.executeUpdate();
        }
    }

    /**
     * Verifica si una fecha específica ya está ocupada.
     *
     * @param fecha La fecha a verificar en formato 'YYYY/MM/DD'.
     * @return true si la fecha está ocupada, false en caso contrario.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public boolean isFechaOcupada(String fecha) throws SQLException {
        Connection connection = conexionBD.getConexion();
        String query = "SELECT COUNT(*) FROM CITAS WHERE FECHA = TO_DATE(?, 'YYYY/MM/DD')";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fecha);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }
}
