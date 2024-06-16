package Libreria.Acciones;

import Libreria.objetos.Libro;

import java.sql.*;

/**
 * La clase BuscadorLibro se encarga de buscar libros en la base de datos.
 */
public class BuscadorLibro {
    private Connection conexion;

    /**
     * Constructor de la clase BuscadorLibro.
     *
     * @param conexionBD Objeto de tipo ConexionBD que proporciona la conexión a la base de datos.
     */
    public BuscadorLibro(ConexionBD conexionBD) {
        this.conexion = conexionBD.getConexion();
    }

    /**
     * Busca un libro en la base de datos por su título.
     *
     * @param tituloLibro El título del libro a buscar.
     * @return Un objeto de tipo Libro si se encuentra, o null si no se encuentra.
     */
    public Libro buscarLibro(String tituloLibro) {
        String sql = "SELECT * FROM libros WHERE titulo = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, tituloLibro);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return crearLibroDesdeResultSet(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Crea un objeto Libro a partir de un ResultSet.
     *
     * @param resultSet El ResultSet obtenido de la consulta a la base de datos.
     * @return Un objeto de tipo Libro.
     * @throws SQLException Si ocurre un error al acceder al ResultSet.
     */
    private Libro crearLibroDesdeResultSet(ResultSet resultSet) throws SQLException {
        Integer id_libro = resultSet.getInt("id_libro");
        String titulo = resultSet.getString("titulo");
        String descripcion = resultSet.getString("descripcion");
        Double precio = resultSet.getDouble("precio");
        Integer id_editorial = resultSet.getInt("id_editorial");
        String portadaRuta = resultSet.getString("portadaruta");

        return new Libro(id_libro, titulo, descripcion, precio, id_editorial, portadaRuta);
    }
}
