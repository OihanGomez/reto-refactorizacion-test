package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Libreria.Acciones.ConexionBD;
import Libreria.Acciones.RegisterManager;

public class RegisterManagerTest {

    private static ConexionBD conexionBD;
    private RegisterManager registerManager;

    @BeforeAll
    public static void setUpClass() {
        conexionBD = new ConexionBD();
    }

    @BeforeEach
    public void setUp() {
        registerManager = new RegisterManager(conexionBD);
    }

    @AfterAll
    public static void tearDownClass() throws SQLException {
        conexionBD.desconectar();
    }

    @Test
    public void testRegisterNuevoUsuario() {
        // Intentar registrar un nuevo usuario
        boolean registroExitoso = registerManager.register("nuevo@example.com", "clave", "Nuevo", "Usuario", "Calle Principal");

        assertTrue(registroExitoso, "Se esperaba un registro exitoso para el nuevo usuario");

        // Verificar que el usuario se haya insertado correctamente en la base de datos
        assertTrue(emailExistsInDatabase("nuevo@example.com"), "El correo electrónico del nuevo usuario debería existir en la base de datos");
    }

    @Test
    public void testRegisterUsuarioExistente() {
        // Insertar un usuario de prueba directamente en la base de datos
        insertarUsuarioDePrueba("existente@example.com", "Calle Principal", "Pérez", "Juan", "clave");

        // Intentar registrar un usuario con el mismo correo electrónico
        boolean registroExitoso = registerManager.register("existente@example.com", "otraclave", "Otro", "Usuario", "Otra Calle");

        assertFalse(registroExitoso, "Se esperaba un fallo en el registro porque el correo electrónico ya está registrado");
    }

    private void insertarUsuarioDePrueba(String email, String direccion, String apellidos, String nombre,
                                         String contrasena) {
        try (Connection conexion = conexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(
                     "INSERT INTO usuarios (id_usuario, direccion, apellidos, nombre, email, admin, contrasena) " +
                             "VALUES (new_usuario_seq.nextval, ?, ?, ?, ?, 'N', ?)")) {
            statement.setString(1, direccion);
            statement.setString(2, apellidos);
            statement.setString(3, nombre);
            statement.setString(4, email);
            statement.setString(5, contrasena);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar usuario de prueba en la base de datos", e);
        }
    }

    private boolean emailExistsInDatabase(String email) {
        try (Connection conexion = conexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement("SELECT * FROM usuarios WHERE email = ?")) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
