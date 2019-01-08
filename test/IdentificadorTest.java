import Identificadores.Identificador;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase {@link Identificador}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
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
     * Testeo del método {@link Identificador#toString()}
     */
    @Test
    public void valorDe() {
        assertEquals("XE", identificador.toString());
    }

    /**
     * Testeo del método {@link Identificador#equals(Object)}. Comprueba que dos identificadores sean iguales solo
     * si sus atributos son exáctamente iguales
     */
    @Test
    public void testEquals() {
        assertTrue(identificador.equals(identificador));
        assertTrue(identificador.equals(new Identificador(36)));
        assertFalse(identificador.equals(new Identificador(5)));
    }

    /**
     * Testeo del método {@link Identificador#hashCode()}. Comprueba que dos hashCodes coincidan solo si son
     * exáctamente el mismo objeto
     */
    @Test
    public void testHashCode() {
        assertTrue(identificador.hashCode() == identificador.hashCode());
        assertFalse(identificador.hashCode() == new Identificador(36).hashCode());
        assertFalse(identificador.hashCode() == new Identificador(5).hashCode());
    }

}
