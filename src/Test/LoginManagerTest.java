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
import Libreria.Acciones.LoginManager;
import Libreria.objetos.Usuario;

public class LoginManagerTest {

    private static ConexionBD conexionBD;
    private LoginManager loginManager;

    @BeforeAll
    public static void setUpClass() {
        conexionBD = new ConexionBD();
    }

    @BeforeEach
    public void setUp() {
        loginManager = new LoginManager(conexionBD);
    }

    @AfterAll
    public static void tearDownClass() throws SQLException {
        conexionBD.desconectar();
    }

    @Test
    public void testBuscarUsuarioExistente() {
        // Insertar un usuario de prueba en la base de datos
        Usuario usuarioPrueba = insertarUsuarioDePrueba("test@example.com", "Calle Principal", "Pérez", "Juan", false, "clave");

        // Buscar el usuario por su email
        Usuario usuarioEncontrado = loginManager.buscarUsuario("test@example.com");

        // Verificar que el usuario encontrado coincide con el usuario de prueba
        assertNotNull(usuarioEncontrado, "Se esperaba encontrar un usuario con el email especificado");
        assertEquals(usuarioPrueba.getIdUsuario(), usuarioEncontrado.getIdUsuario(), "Los IDs de usuario no coinciden");
        assertEquals(usuarioPrueba.getDireccion(), usuarioEncontrado.getDireccion(), "Las direcciones de usuario no coinciden");
        assertEquals(usuarioPrueba.getApellidos(), usuarioEncontrado.getApellidos(), "Los apellidos de usuario no coinciden");
        assertEquals(usuarioPrueba.getNombre(), usuarioEncontrado.getNombre(), "Los nombres de usuario no coinciden");
        assertEquals(usuarioPrueba.getEmail(), usuarioEncontrado.getEmail(), "Los emails de usuario no coinciden");
        assertEquals(usuarioPrueba.isAdmin(), usuarioEncontrado.isAdmin(), "Los roles de administrador de usuario no coinciden");
        assertEquals(usuarioPrueba.getContrasena(), usuarioEncontrado.getContrasena(), "Las contraseñas de usuario no coinciden");
    }

    @Test
    public void testBuscarUsuarioNoExistente() {
        // Buscar un usuario que no existe en la base de datos
        Usuario usuarioEncontrado = loginManager.buscarUsuario("usuarioquenoexiste@example.com");
        assertNull(usuarioEncontrado, "No se esperaba encontrar un usuario con el email especificado");
    }

    @Test
    public void testLoginCorrecto() {
        // Insertar un usuario de prueba en la base de datos
        Usuario usuarioPrueba = insertarUsuarioDePrueba("test@example.com", "Calle Principal", "Pérez", "Juan", false, "clave");

        // Realizar login con la contraseña correcta
        boolean resultadoLogin = loginManager.login(usuarioPrueba, "clave");
        assertTrue(resultadoLogin, "Se esperaba un login exitoso con la contraseña correcta");
    }

    @Test
    public void testLoginIncorrecto() {
        // Insertar un usuario de prueba en la base de datos
        Usuario usuarioPrueba = insertarUsuarioDePrueba("test@example.com", "Calle Principal", "Pérez", "Juan", false, "clave");

        // Intentar realizar login con una contraseña incorrecta
        boolean resultadoLogin = loginManager.login(usuarioPrueba, "contraseñaincorrecta");
        assertFalse(resultadoLogin, "Se esperaba un login fallido con la contraseña incorrecta");
    }

    private Usuario insertarUsuarioDePrueba(String email, String direccion, String apellidos, String nombre,
                                            boolean admin, String contrasena) {
        try (Connection conexion = conexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(
                     "INSERT INTO usuarios (email, direccion, apellidos, nombre, admin, contrasena) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, email);
            statement.setString(2, direccion);
            statement.setString(3, apellidos);
            statement.setString(4, nombre);
            statement.setString(5, admin ? "Y" : "N");
            statement.setString(6, contrasena);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idUsuario = generatedKeys.getInt(1);
                    return new Usuario(idUsuario, direccion, apellidos, nombre, email, admin, contrasena);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar usuario de prueba en la base de datos", e);
        }
        return null;
    }
}
