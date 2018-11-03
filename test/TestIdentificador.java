import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * CLASE DE TESTEO de la clase Identificador.
 *
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class TestIdentificador {

    // Fixture de objetos Identificador de prueba
    private static Identificador identificador;

    /**
     * Constructor privado. No hace falta instanciar la clase
     */
    private TestIdentificador() {}

    @BeforeAll
    static void setUp() {
        identificador = new Identificador(36);
    }

    /**
     * Testeo del constructor por copia
     */
    @Test
    void Identificador() {
        assertNotSame(identificador, new Identificador(identificador));
    }

    /**
     * Testeo del método accesor del atributo 'valor'
     */
    @Test
    void valorDe() {
    }

    /**
     * Testeo del método mutador 'incrementar()'
     */
    @Test
    void incrementar() {
    }
}
