import Identificadores.Identificador;
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
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class IdentificadorTest {

    // Fixture de objetos Identificador de prueba
    private static Identificador identificador;

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
     * Testeo del método 'identificadorEquivalente()'. Comprueba que se generen instancias distintas, pero con
     * cadenas identificadores equivalentes
     */
    @Test
    public void identificadorEquivalente() {
        assertNotSame(identificador, Identificador.identificadorEquivalente("XE"));
        assertEquals(identificador.toString(), Identificador.identificadorEquivalente("XE").toString());
    }

    /**
     * Testeo del método accesor del atributo 'valor'
     */
    @Test
    public void valorDe() {
        assertEquals("XE", identificador.toString());
    }

}
