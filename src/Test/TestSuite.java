package Test;

import Libreria.Acciones.AdminManagement;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdminManagement.class,
        BuscadorLibroTest.class,
        CitasManagerTest.class,
        ConexionBDTest.class,
        LoginManagerTest.class,
        RegisterManagerTest.class
})
public class TestSuite {

}

