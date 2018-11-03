import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

/**
 * CLASE DE TESTEO de la clase GeneradorIdentificador.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneradorIdentificadorTest {
    // Instancia Singleton de la clase GeneradorIdentificador
    private static GeneradorIdentificador generadorIdentificador;

    /**
     * Constructor por defecto
     */
    public GeneradorIdentificadorTest() {}

    @BeforeClass
    public static void setUp() {
        generadorIdentificador = GeneradorIdentificador.recuperarInstancia();
    }

    /**
     * Testeo del método accesor 'consultarIdentificador()'
     */
    @Test
    public void consultarIdentificador() {
        assertEquals("X5P1A", generadorIdentificador.consultarIdentificador().valorDe());
        assertEquals("X5P1A", generadorIdentificador.consultarIdentificador().valorDe());
    }

    /**
     * Testeo del método 'generarIdentificador()'
     */
    @Test
    public void generadorIdentificador() {
        assertEquals("X5P1A", generadorIdentificador.generarIdentificador().valorDe());
        assertEquals("X5P1S", generadorIdentificador.generarIdentificador().valorDe());
    }

}
