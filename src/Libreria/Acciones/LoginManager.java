package Libreria.Acciones;

import Libreria.objetos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que gestiona las operaciones relacionadas con el inicio de sesión de usuarios.
 */
public class LoginManager {
    private Connection conexion;

    /**
     * Constructor de la clase LoginManager.
     *
     * @param conexionBD Objeto ConexionBD que proporciona la conexión a la base de datos.
     */
    public LoginManager(ConexionBD conexionBD) {
        this.conexion = conexionBD.getConexion();
    }

    /**
     * Busca un usuario en la base de datos por su dirección de correo electrónico.
     *
     * @param email Dirección de correo electrónico del usuario a buscar.
     * @return Objeto Usuario si se encuentra en la base de datos, null si no se encuentra.
     * @throws RuntimeException Si ocurre un error al buscar el usuario en la base de datos.
     */
    public Usuario buscarUsuario(String email) {
        String query = "SELECT * FROM usuarios WHERE email = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idUsuario = resultSet.getInt("idUsuario");
                    String direccion = resultSet.getString("direccion");
                    String apellidos = resultSet.getString("apellidos");
                    String nombre = resultSet.getString("nombre");
                    boolean admin = resultSet.getString("admin").equals("Y");
                    String contrasena = resultSet.getString("contrasena");

                    return new Usuario(idUsuario, direccion, apellidos, nombre, email, admin, contrasena);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario en la base de datos", e);
        }

        return null;
    }

    /**
     * Verifica si la contraseña proporcionada coincide con la contraseña del usuario.
     *
     * @param usuario Objeto Usuario cuya contraseña se va a verificar.
     * @param inputPassword Contraseña proporcionada por el usuario para el inicio de sesión.
     * @return true si las contraseñas coinciden, false si no coinciden.
     */
    public boolean login(Usuario usuario, String inputPassword) {
        return usuario.getContrasena().equals(inputPassword);
    }
}
