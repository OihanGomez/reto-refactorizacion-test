package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Libreria.Acciones.CitasManager;
import Libreria.Acciones.ConexionBD;
import Libreria.objetos.Cita;

public class CitasManagerTest {

    private static ConexionBD conexionBD;
    private CitasManager citasManager;

    @BeforeAll
    public static void setUpClass() {
        // Inicializar la conexión a la base de datos una sola vez antes de todas las pruebas
        conexionBD = new ConexionBD();
    }

    @BeforeEach
    public void setUp() {
        // Inicializar CitasManager con la conexión de prueba
        citasManager = new CitasManager();
    }

    @AfterAll
    public static void tearDownClass() throws SQLException {
        // Cerrar la conexión después de todas las pruebas
        conexionBD.desconectar();
    }

    @Test
    public void testReservarCita() throws Exception {
        LocalDate fecha = LocalDate.now().plusDays(1);
        Cita cita = new Cita("test@example.com", fecha);

        // Reservar una cita
        citasManager.reservarCita(cita);
        assertTrue(isCitaExistente(cita.getEmail(), cita.getFecha()));

        // Verificar que no se pueda reservar otra cita para el mismo usuario en la misma fecha
        try {
            citasManager.reservarCita(cita);
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("La fecha ya está ocupada para este usuario.", e.getMessage());
        }
    }

    @Test
    public void testIsFechaOcupada() throws Exception {
        LocalDate fecha = LocalDate.now().plusDays(1);
        Cita cita = new Cita("test@example.com", fecha);

        assertFalse(citasManager.isFechaOcupada(fecha));

        // Reservar una cita
        insertarCitaDePrueba(cita.getEmail(), cita.getFecha());
        assertTrue(citasManager.isFechaOcupada(fecha));
    }

    private void insertarCitaDePrueba(String email, LocalDate fecha) throws SQLException {
        try (Connection conexion = conexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(
                     "INSERT INTO CITAS (EMAIL, FECHA) VALUES (?, ?)")) {
            statement.setString(1, email);
            statement.setDate(2, java.sql.Date.valueOf(fecha));
            statement.executeUpdate();
        }
    }

    private boolean isCitaExistente(String email, LocalDate fecha) throws SQLException {
        try (Connection conexion = conexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(
                     "SELECT COUNT(*) FROM CITAS WHERE EMAIL = ? AND FECHA = ?")) {
            statement.setString(1, email);
            statement.setDate(2, java.sql.Date.valueOf(fecha));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
