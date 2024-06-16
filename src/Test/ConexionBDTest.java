package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Libreria.Acciones.ConexionBD;

public class ConexionBDTest {

    private static ConexionBD conexionBD;

    @BeforeAll
    public static void setUpClass() {
        conexionBD = new ConexionBD();
    }

    @AfterAll
    public static void tearDownClass() throws SQLException {
        if (conexionBD != null) {
            conexionBD.desconectar();
        }
    }

    @Test
    public void testConexionNoNula() {
        // Verificar que la conexión no es nula
        Connection connection = conexionBD.getConexion();
        assertNotNull(connection, "La conexión debería ser no nula");
    }

    @Test
    public void testConexionAbierta() throws SQLException {
        // Verificar que la conexión está abierta
        Connection connection = conexionBD.getConexion();
        assertFalse(connection.isClosed(), "La conexión debería estar abierta");
    }

    @Test
    public void testDesconexion() throws SQLException {
        // Desconectar y verificar que la conexión está cerrada
        Connection connection = conexionBD.getConexion();
        conexionBD.desconectar();
        assertTrue(connection.isClosed(), "La conexión debería estar cerrada después de llamar a desconectar");
    }

    @Test
    public void testReconexion() throws SQLException {
        // Cerrar y reabrir la conexión para verificar que se puede reconectar
        conexionBD.desconectar();
        conexionBD = new ConexionBD();
        Connection connection = conexionBD.getConexion();
        assertFalse(connection.isClosed(), "La conexión debería estar abierta después de reconectar");
    }
}
