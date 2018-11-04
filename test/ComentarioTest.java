import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * CLASE DE TESTEO de la clase Comentario.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class ComentarioTest {

    // Fixture de objetos Comentario de prueba
    private static Comentario comentario;

    // Cliente de ejemplo
    private static Cliente cliente;

    @BeforeClass
    public static void setUp() {
        cliente = new Cliente("Nombre", 18, "Localidad");
        comentario = new Comentario(cliente, "Test", 5);
    }

    /**
     * Testea la excepción generada por el constructor al pasar parámetros no válidos
     */
    @Test(expected = IllegalArgumentException.class)
    public void parametrosValidos() {
        comentario = new Comentario(cliente, "", 6);
    }

    @Test
    public void getAutor() {
        assertSame(cliente, comentario.getAutor());
    }

    @Test
    public void getTexto() {
        assertEquals("Test", comentario.getTexto());
    }

    @Test
    public void getPuntuacion() {
        assertEquals(5, comentario.getPuntuacion());
    }

    @Test
    public void comentarioCompleto() {
        assertEquals("Autor : Nombre\n" +
                "Calificación *****\n" +
                "\tReseña :\n" +
                "Test", comentario.comentarioCompleto());
    }
}