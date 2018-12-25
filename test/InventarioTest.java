import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase Inventario.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventarioTest {

    // Instancia Singleton de la clase Inventario
    private static Inventario inventario;

    // Fixture de productos de prueba
    private static Producto producto;
    private static Producto productoNoInventario;

    @BeforeClass
    public static void setUp() {
        inventario = Inventario.recuperarInstancia();
        producto = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA);
        productoNoInventario = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA);
    }

    /**
     * Testeo del método 'agregarProducto()'. Comprueba que se pueda agregar un producto (no nulo)
     * , pero no más de una vez
     */
    @Test
    public void aAgregarProducto() {
        assertTrue(inventario.agregarProducto(producto));
        assertFalse(inventario.agregarProducto(producto));
        assertFalse(inventario.agregarProducto(null));
    }

    /**
     * Testeo del método 'eliminarProducto()'. Comprueba que solo se pueden eliminar productos (no nulos)
     * que están en el inventario
     */
    @Test
    public void eliminarProducto() {
        assertTrue(inventario.eliminarProducto(producto));
        assertFalse(inventario.eliminarProducto(producto));
        assertFalse(inventario.eliminarProducto(productoNoInventario));
        assertFalse(inventario.eliminarProducto(null));
    }

    /**
     * Testeo del método 'venderProduto()'. Comprueba que solo se despachen pedidos de productos (no nulos)
     * en el inventario. Comprueba que se reponga el stock del prduto si es necesario
     */
    @Test
    public void bVenderProducto() {
        assertTrue(inventario.venderProducto(producto, 5));
        assertEquals(25, producto.getCantidad());
        assertTrue(inventario.venderProducto(producto, 1));
        assertEquals(99, producto.getCantidad());
        assertFalse(inventario.venderProducto(productoNoInventario, 1));
        assertFalse(inventario.venderProducto(null, 1));
    }

    /**
     * Testeo del método 'recuperarProducto()'. Comprueba que solo se puedan recuperar productos del inventario
     */
    @Test
    public void dRecuperarProducto() {
        assertEquals(producto, inventario.recuperarProducto(producto.getIdentificador()));
        assertNotEquals(productoNoInventario, inventario.recuperarProducto(productoNoInventario.getIdentificador()));
        assertNull(inventario.recuperarProducto(null));
    }

    /**
     * Testeo del método 'recuperarInstancia()'. Comrpueba que solo exista una insrancia Singleton durante
     * el ciclo de vida del programa
     */
    @Test
    public void recuperarInstancia() {
        assertSame(inventario, Inventario.recuperarInstancia());
    }

    /**
     * Testea el método 'toString()'. (COMPROBACIÓN VISUAL)
     */
    @Test
    public void eMostrarDetallesStock() {
        System.out.println(inventario);
    }

}