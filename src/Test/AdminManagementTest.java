package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Libreria.Acciones.AdminManagement;
import Libreria.Acciones.ConexionBD;
import Libreria.objetos.Usuario;

public class AdminManagementTest {

    private static ConexionBD conexionBD;
    private AdminManagement adminManagement;

    @BeforeAll
    public static void setUpClass() {
        // Inicializar la conexión a la base de datos una sola vez antes de todas las pruebas
        conexionBD = new ConexionBD();
    }

    @BeforeEach
    public void setUp() {
        // Inicializar AdminManagement con la conexión de prueba
        adminManagement = new AdminManagement(conexionBD);
    }

    @AfterAll
    public static void tearDownClass() throws SQLException {
        // Cerrar la conexión después de todas las pruebas
        conexionBD.desconectar();
    }

    @Test
    public void testMostrarUsuarios() throws SQLException {
        // Insertar datos de prueba en la tabla de usuarios
        insertarUsuarioDePrueba(1, "Calle Principal", "Pérez", "Juan", "juan@example.com", false, "clave");

        // Configurar un JComboBox simulado
        JComboBox<Usuario> pruebaComboBox = new JComboBox<>();

        // Llamar al método bajo prueba
        adminManagement.mostrarUsuarios(pruebaComboBox);

        // Verificar que se añadió un elemento al comboBox
        assertEquals(1, pruebaComboBox.getItemCount(), "Se esperaba un usuario en el JComboBox");
    }

    @Test
    public void testActualizarUsuario() throws SQLException {
        // Insertar un usuario de prueba en la base de datos
        insertarUsuarioDePrueba(1, "Calle Principal", "Pérez", "Juan", "juan@example.com", false, "clave");

        // Crear un objeto Usuario para actualizar
        Usuario usuarioActualizado = new Usuario(1, "Nueva Dirección", "Nuevos Apellidos", "Nuevo Nombre",
                "nuevo@email.com", true, "nuevaclave");

        // Llamar al método bajo prueba
        adminManagement.actualizarUsuario(usuarioActualizado);

        // Verificar que el usuario se actualizó correctamente en la base de datos
        Usuario usuarioObtenido = obtenerUsuario(1);
        assertNotNull(usuarioObtenido, "No se encontró ningún usuario con el ID especificado");
        assertEquals(usuarioActualizado.getDireccion(), usuarioObtenido.getDireccion(),
                "La dirección del usuario no coincide después de la actualización");
        assertEquals(usuarioActualizado.getApellidos(), usuarioObtenido.getApellidos(),
                "Los apellidos del usuario no coinciden después de la actualización");
        assertEquals(usuarioActualizado.getNombre(), usuarioObtenido.getNombre(),
                "El nombre del usuario no coincide después de la actualización");
        assertEquals(usuarioActualizado.getEmail(), usuarioObtenido.getEmail(),
                "El email del usuario no coincide después de la actualización");
        assertEquals(usuarioActualizado.isAdmin(), usuarioObtenido.isAdmin(),
                "El estado de administrador del usuario no coincide después de la actualización");
        assertEquals(usuarioActualizado.getContrasena(), usuarioObtenido.getContrasena(),
                "La contraseña del usuario no coincide después de la actualización");
    }

    private void insertarUsuarioDePrueba(int idUsuario, String direccion, String apellidos, String nombre,
                                         String email, boolean admin, String contrasena) throws SQLException {
        try (Connection conexion = conexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement(
                     "INSERT INTO usuarios (idUsuario, direccion, apellidos, nombre, email, admin, contrasena) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, idUsuario);
            statement.setString(2, direccion);
            statement.setString(3, apellidos);
            statement.setString(4, nombre);
            statement.setString(5, email);
            statement.setBoolean(6, admin);
            statement.setString(7, contrasena);
            statement.executeUpdate();
        }
    }

    private Usuario obtenerUsuario(int idUsuario) throws SQLException {
        try (Connection conexion = conexionBD.getConexion();
             PreparedStatement statement = conexion.prepareStatement("SELECT * FROM usuarios WHERE idUsuario = ?")) {
            statement.setInt(1, idUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("idUsuario");
                    String direccion = resultSet.getString("direccion");
                    String apellidos = resultSet.getString("apellidos");
                    String nombre = resultSet.getString("nombre");
                    String email = resultSet.getString("email");
                    boolean admin = resultSet.getBoolean("admin");
                    String contrasena = resultSet.getString("contrasena");
                    return new Usuario(id, direccion, apellidos, nombre, email, admin, contrasena);
                }
            }
        }
        return null;
    }
}
