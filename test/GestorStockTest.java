import org.junit.BeforeClass;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertSame;

/**
 * CLASE DE TESTEO de la clase GestorStock.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class GestorStockTest {

    // Instancia Singleton de la clase GestorStock
    private static GestorStock gestorStock;

    // Fixture de productos de pruebs
    private static Producto producto;

    @BeforeClass
    public void setUp() {
        gestorStock = GestorStock.recuperarInstancia();
        producto = new Producto("Nombre", 30, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA,
                new GregorianCalendar(2011, 3, 26), true);
    }

    @Test
    public void agregarProducto() {
    }

    @Test
    public void eliminarProducto() {
    }

    @Test
    public void venderProducto() {
    }

    @Test
    public void comentarProducto() {
    }

    @Test
    public void recuperarProducto() {
    }

    /**
     * Testeo del método 'recuperarInstancia()'. Comrpueba que solo exista una insrancia Singleton durante
     * el ciclo de vida del programa
     */
    @Test
    public void recuperarInstancia() {
        assertSame(gestorStock, GestorStock.recuperarInstancia());
    }

    @Test
    public void mostrarDetallesStock() {
    }
}