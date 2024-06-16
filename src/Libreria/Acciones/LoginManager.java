package Libreria.Acciones;

import Libreria.objetos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginManager {
    private Connection conexion;

    public LoginManager(ConexionBD conexionBD) {
        this.conexion = conexionBD.getConexion();
    }

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

    public boolean login(Usuario usuario, String inputPassword) {
        if (usuario.getContrasena().equals(inputPassword)){
            return true;
        }else {
            return false;
        }
    }
}
