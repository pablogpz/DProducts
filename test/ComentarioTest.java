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
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComentarioTest {

    // Fixture de objetos Comentario de prueba
    private static Comentario comentario;

    // Cliente de ejemplo
    private static Cliente cliente;

    @BeforeClass
    public static void setUp() {
        cliente = new ClienteEstandar("Nombre", 18, "Localidad");
        comentario = new Comentario(cliente, "Test", 5);
    }

    /**
     * Testea la excepción generada por el constructor al pasar parámetros no válidos
     */
    @Test(expected = IllegalArgumentException.class)
    public void parametrosValidos() {
        comentario = new Comentario(cliente, "", 6);
    }

    /**
     * Testeo del método accesor del atributo 'autor'
     */
    @Test
    public void getAutor() {
        assertSame(cliente, comentario.getAutor());
    }

    /**
     * Testeo del método accesor del atributo 'texto'
     */
    @Test
    public void getTexto() {
        assertEquals("Test", comentario.getTexto());
    }

    /**
     * Testeo del método accesor del atributo 'puntuacion'
     */
    @Test
    public void getPuntuacion() {
        assertEquals(5, comentario.getPuntuacion());
    }

    /**
     * Testeo del método 'toString()', que devuelve toda la información
     * del comentario formateado
     */
    @Test
    public void comentarioCompleto() {
        assertEquals("Autor : Nombre\n" +
                "Calificación *****\n" +
                "\tReseña :\n" +
                "Test", comentario.toString());
    }

}