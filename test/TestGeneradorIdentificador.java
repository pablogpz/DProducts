import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CLASE DE TESTEO de la clase GeneradorIdentificador.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class TestGeneradorIdentificador {
    // Instancia Singleton de la clase GeneradorIdentificador
    static GeneradorIdentificador generadorIdentificador;

    /**
     * Constructor privado. No hace falta instanciar la clase
     */
    private TestGeneradorIdentificador() {}

    @BeforeAll
    static void setUp() {
        generadorIdentificador = GeneradorIdentificador.recuperarInstancia();
    }

    /**
     * Testeo del método accesor 'consultarIdentificador()'
     */
    @Test
    void consultarIdentificador() {
        assertEquals("X5P1J", generadorIdentificador.consultarIdentificador().valorDe());
        assertEquals("X5P1J", generadorIdentificador.consultarIdentificador().valorDe());
    }

    /**
     * Testeo del método 'generarIdentificador()'
     */
    @Test
    void generadorIdentificador() {
        assertEquals("X5P1A", generadorIdentificador.generarIdentificador().valorDe());
        assertEquals("X5P1S", generadorIdentificador.generarIdentificador().valorDe());
    }

}
