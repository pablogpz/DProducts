import org.junit.BeforeClass;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase Producto.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class ProductoTest {

    // Fixture de objetos Producto de prueba
    private static Producto producto;

    @BeforeClass
    public static void setUp() {
        producto = new Producto("Nombre", 30, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA,
                new GregorianCalendar(2011, 3, 26), true);
    }

    /**
     * Testeo del método accesor del atribtuo 'nombre'
     */
    @Test
    public void getNombre() {
        assertEquals("Nombre", producto.getNombre());
    }

    /**
     * Testeo del método accesor del atribtuo 'identificador'
     */
    @Test
    public void getIdentificador() {
        assertNotNull(producto.getIdentificador());
    }

    /**
     * Testeo del método accesor del atribtuo 'cantidad'
     */
    @Test
    public void getCantidad() {
        assertEquals(30, producto.getCantidad());
    }

    /**
     * Testeo del método accesor del atribtuo 'stockMínimo'
     */
    @Test
    public void getStockMinimo() {
        assertEquals(25, producto.getStockMinimo());
    }

    /**
     * Testeo del método accesor del atribtuo 'prioridad'
     */
    @Test
    public void getPrioridad() {
        assertEquals(PRIORIDAD_PRODUCTO.MEDIA, producto.getPrioridad());
    }

    /**
     * Testeo del método accesor del atribtuo 'fabricante'
     */
    @Test
    public void getFrabricante() {
        assertEquals(FABRICANTES.ACER, producto.getFrabricante());
    }

    /**
     * Testeo del método accesor del atribtuo 'fechaLanzamiento'
     */
    @Test
    public void getFechaLanzamiento() {
        assertEquals(new GregorianCalendar(2011, 3, 26), producto.getFechaLanzamiento());
    }

    /**
     * Testeo del método accesor del atribtuo 'esReacondicionado'
     */
    @Test
    public void getEsReacondicionado() {
        assertTrue(producto.getEsReacondicionado());
    }

    @Test
    public void pedir() {
    }

    @Test
    public void comentar() {
    }

    @Test
    public void haySuficienteStock() {
    }

    @Test
    public void detalles() {
    }
}