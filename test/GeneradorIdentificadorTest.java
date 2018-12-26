import Identificadores.GeneradorIdentificador;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase {@see GeneradorIdentificador}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneradorIdentificadorTest {

    // Instancia Singleton de la clase GeneradorIdentificador
    private static GeneradorIdentificador generadorIdentificador;

    @BeforeClass
    public static void setUp() {
        generadorIdentificador = GeneradorIdentificador.recuperarInstancia();
    }

    /**
     * Testeo del método accesor {@link GeneradorIdentificador#consultarIdentificador()}. Comprueba que se lea el mismo
     * identificador consecutivamente al consultar el siguiente identificador a asignar sin modificarlo.
     * Basado en la comparación de hashes
     */
    @Test
    public void consultarIdentificador() {
        int identificador = generadorIdentificador.consultarIdentificador().hashCode();
        int identificador1 = generadorIdentificador.consultarIdentificador().hashCode();
        assertEquals(0, identificador - identificador1);
    }

    /**
     * Testeo del método {@link GeneradorIdentificador#generarIdentificador()}. Comprueba que dos llamadas a
     * generación de identificador no devuelvan el mismo valor. Basado en la comparación de hashes
     */
    @Test
    public void generarIdentificador() {
        int identificador = generadorIdentificador.generarIdentificador().hashCode();
        int identificador1 = generadorIdentificador.generarIdentificador().hashCode();
        assertNotEquals(0, identificador - identificador1);
    }

    /**
     * Testeo del método {@link GeneradorIdentificador#recuperarInstancia()}. Comrpueba que solo exista una
     * instancia Singleton durante el ciclo de vida del programa
     */
    @Test
    public void recuperarInstancia() {
        assertSame(generadorIdentificador, GeneradorIdentificador.recuperarInstancia());
    }
}
