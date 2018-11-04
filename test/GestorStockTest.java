import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase GestorStock.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GestorStockTest {

    // Instancia Singleton de la clase GestorStock
    private static GestorStock gestorStock;

    // Fixture de productos de prueba
    private static Producto producto;
    private static Producto productoNoInventario;

    @BeforeClass
    public static void setUp() {
        gestorStock = GestorStock.recuperarInstancia();
        producto = new Producto("Nombre", 30, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA,
                new GregorianCalendar(2011, 3, 26), true);
        productoNoInventario = new Producto("Nombre", 30, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA,
                new GregorianCalendar(2011, 3, 26), true);
    }

    /**
     * Testeo del método 'agregarProducto()'. Comprueba que se pueda agregar un producto (no nulo)
     * , pero no más de una vez
     */
    @Test
    public void aAgregarProducto() {
        assertTrue(gestorStock.agregarProducto(producto));
        assertFalse(gestorStock.agregarProducto(producto));
        assertFalse(gestorStock.agregarProducto(null));
    }

    /**
     * Testeo del método 'eliminarProducto()'. Comprueba que solo se pueden eliminar productos (no nulos)
     * que están en el inventario
     */
    @Test
    public void fEliminarProducto() {
        assertTrue(gestorStock.eliminarProducto(producto));
        assertFalse(gestorStock.eliminarProducto(producto));
        assertFalse(gestorStock.eliminarProducto(productoNoInventario));
        assertFalse(gestorStock.eliminarProducto(null));
    }

    /**
     * Testeo del método 'venderProduto()'. Comprueba que solo se despachen pedidos de productos (no nulos)
     * en el inventario. No se prueba la validez del pedido, queda delegado a la clase Producto
     */
    @Test
    public void bVenderProducto() {
        assertTrue(gestorStock.venderProducto(producto, 1));
        assertFalse(gestorStock.venderProducto(productoNoInventario, 1));
        assertFalse(gestorStock.venderProducto(null, 1));
    }

    /**
     * Testeo del método 'comentarProducto()'. Comprueba que solo se puedan comentar (comentarios no nulos) productos
     * (no nulos) del inventario y no más de una vez
     */
    @Test
    public void cComentarProducto() {
        Comentario comentario = new Comentario(new Cliente(
                "Nombre", 18, "Localidad"), "Test", 5);

        assertTrue(gestorStock.comentarProducto(producto, comentario));
        assertFalse(gestorStock.comentarProducto(producto, comentario));
        assertFalse(gestorStock.comentarProducto(productoNoInventario, comentario));
        assertFalse(gestorStock.comentarProducto(null, comentario));
        assertFalse(gestorStock.comentarProducto(null, null));
    }

    /**
     * Testeo del método 'recuperarProducto()'. Comprueba que solo se puedan recuperar productos del inventario
     */
    @Test
    public void dRecuperarProducto() {
        assertEquals(producto, gestorStock.recuperarProducto(producto.getIdentificador()));
        assertNotEquals(productoNoInventario, gestorStock.recuperarProducto(productoNoInventario.getIdentificador()));
        assertNull(gestorStock.recuperarProducto(null));
    }

    /**
     * Testeo del método 'recuperarInstancia()'. Comrpueba que solo exista una insrancia Singleton durante
     * el ciclo de vida del programa
     */
    @Test
    public void recuperarInstancia() {
        assertSame(gestorStock, GestorStock.recuperarInstancia());
    }

    /**
     * Testea el método 'mostrarDetallesStock()'. Se hace una comprobación visual la salida por consola
     */
    @Test
    public void eMostrarDetallesStock() {
        gestorStock.mostrarDetallesStock();
    }

}