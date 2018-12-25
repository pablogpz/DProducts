import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase Comentario.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez
@author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComentarioTest {

    // Fixture de objetos Comentario de prueba
    private static Comentario comentario;

    // Cliente de ejemplo
    private static Cliente cliente;

    @Before
    public void setUp() {
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

    @Test
    public void setAutor() {
        ClienteEstandar nuevoAutor = new ClienteEstandar("Nuevo nombre", 18, "Localidad");
        comentario.setAutor(nuevoAutor);
        assertEquals(nuevoAutor, comentario.getAutor());
    }

    @Test
    public void setTexto() {
        String nuevoTexto = "Nuevo texto";
        comentario.setTexto(nuevoTexto);
        assertEquals(nuevoTexto, comentario.getTexto());
        assertFalse(comentario.setTexto(""));
    }

    @Test
    public void setPuntuacion() {
        int nuevaPuntuacion = 4;
        comentario.setPuntuacion(nuevaPuntuacion);
        assertEquals(nuevaPuntuacion, comentario.getPuntuacion());
        assertFalse(comentario.setPuntuacion(0));
        assertFalse(comentario.setPuntuacion(6));
    }

    /**
     * Testeo del método 'toString()', que devuelve toda la información
     * del comentario formateado. (COMPROBACIÓN VISUAL)
     */
    @Test
    public void comentarioCompleto() {
        System.out.println(comentario);
    }

    /**
     * Testeo del método 'equals()'
     */
    @Test
    public void testEquals() {
        assertTrue(comentario.equals(comentario));
        assertTrue(comentario.equals(new Comentario(cliente, "Test", 5)));
        assertFalse(comentario.equals(new Comentario(cliente, "Otro test", 5)));
    }

    /**
     * Testeo del método 'hashCode()'
     */
    @Test
    public void testHashCode() {
        assertTrue(comentario.hashCode() == comentario.hashCode());
        assertFalse(comentario.hashCode() == new Comentario(cliente, "Test", 5).hashCode());
        assertFalse(comentario.hashCode() == new Comentario(cliente, "Otro test", 5).hashCode());
    }
    
}