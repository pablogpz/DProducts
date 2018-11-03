import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * CLASE DE TESTEO de la clase Identificador.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class IdentificadorTest {

    // Fixture de objetos Identificador de prueba
    private static Identificador identificador;

    /**
     * Constructor por defecto
     */
    public IdentificadorTest() {}

    @BeforeClass
    public static void setUp() {
        identificador = new Identificador(36);
    }

    /**
     * Testeo del constructor por copia
     */
    @Test
    public void Identificador() {
        assertNotSame(identificador, new Identificador(identificador));
    }

    /**
     * Testeo del método accesor del atributo 'valor'
     */
    @Test
    public void valorDe() {
        assertEquals("XE", identificador.valorDe());
    }

    /**
     * Testeo del método mutador 'incrementar()'
     */
    @Test
    public void incrementar() {
        identificador.incrementar(1);
        assertEquals("XX", identificador.valorDe());
    }
}
