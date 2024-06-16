package Libreria.Acciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import Libreria.objetos.Usuario;

public class AdminManagement {
    private Connection conexion;

    public AdminManagement(ConexionBD conexion) {
        this.conexion = conexion.getConexion();
    }

    public void mostrarUsuarios(JComboBox<Usuario> comboBox) {
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM usuarios");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idUsuario = resultSet.getInt("idUsuario");
                String direccion = resultSet.getString("direccion");
                String apellidos = resultSet.getString("apellidos");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                boolean admin = resultSet.getBoolean("admin");
                String contrasena = resultSet.getString("contrasena");

                Usuario usuario = new Usuario(idUsuario, direccion, apellidos, nombre, email, admin, contrasena);
                comboBox.addItem(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarUsuario(Usuario usuario) {
        try {
            PreparedStatement statement = conexion.prepareStatement("UPDATE usuarios SET direccion = ?, apellidos = ?, nombre = ?, email = ?, admin = ?, contrasena = ? WHERE idUsuario = ?");
            statement.setString(1, usuario.getDireccion());
            statement.setString(2, usuario.getApellidos());
            statement.setString(3, usuario.getNombre());
            statement.setString(4, usuario.getEmail());
            statement.setBoolean(5, usuario.isAdmin());
            statement.setString(6, usuario.getContrasena());
            statement.setInt(7, usuario.getIdUsuario());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Información actualizada exitosamente para el usuario con ID: " + usuario.getIdUsuario());
            } else {
                System.out.println("No se encontró ningún usuario con el ID: " + usuario.getIdUsuario());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
