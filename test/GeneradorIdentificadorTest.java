import Identificadores.GeneradorIdentificador;
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
     * Testeo del método accesor 'consultarIdentificador()'
     */
    @Test
    public void consultarIdentificador() {
        assertEquals("X5P1N", generadorIdentificador.consultarIdentificador().toString());
        assertEquals("X5P1N", generadorIdentificador.consultarIdentificador().toString());
    }

    /**
     * Testeo del método 'generarIdentificador()'
     */
    @Test
    public void generadorIdentificador() {
        assertEquals("X5P1N", generadorIdentificador.generarIdentificador().toString());
        assertEquals("X5P1T", generadorIdentificador.generarIdentificador().toString());
    }

}
