package Test;

import Libreria.Acciones.BuscadorLibro;
import Libreria.Acciones.ConexionBD;
import Libreria.objetos.Libro;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class BuscadorLibroTest {

    private static ConexionBD conexionBD;
    private BuscadorLibro buscadorLibro;

    @BeforeAll
    public static void setUpClass() {
        // Inicializar la conexión a la base de datos una sola vez antes de todas las pruebas
        conexionBD = new ConexionBD();
    }

    @BeforeEach
    public void setUp() {
        // Inicializar el buscadorLibro con la conexión de prueba
        buscadorLibro = new BuscadorLibro(conexionBD);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Limpiar después de cada prueba si es necesario
    }

    @AfterAll
    public static void tearDownClass() throws SQLException {
        // Cerrar la conexión después de todas las pruebas
        conexionBD.desconectar();
    }

    @Test
    public void testBuscarLibroExistente() throws SQLException {
        // Preparar datos de prueba si es necesario
        insertarLibroDePrueba("Java Programming");

        // Prueba buscar un libro que existe en la base de datos
        String tituloBusqueda = "Java Programming";
        Libro libroEncontrado = buscadorLibro.buscarLibro(tituloBusqueda);

        assertNotNull(libroEncontrado, "Se esperaba encontrar un libro con el título: " + tituloBusqueda);
        assertEquals(tituloBusqueda, libroEncontrado.getTitulo(), "El título del libro encontrado no coincide");
    }

    @Test
    public void testBuscarLibroNoExistente() {
        // Prueba buscar un libro que no existe en la base de datos
        String tituloBusqueda = "Libro Inexistente";
        Libro libroEncontrado = buscadorLibro.buscarLibro(tituloBusqueda);

        assertNull(libroEncontrado, "No se esperaba encontrar un libro con el título: " + tituloBusqueda);
    }

    // Método auxiliar para insertar un libro de prueba en la base de datos
    private void insertarLibroDePrueba(String titulo) throws SQLException {
        Connection connection = conexionBD.getConexion();
        try (Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO libros (titulo, descripcion, precio, id_editorial, portadaruta) " +
                    "VALUES ('" + titulo + "', 'Descripción de prueba', 29.99, 1, '/ruta/portada.jpg')";
            statement.executeUpdate(sql);
        }
    }
}
