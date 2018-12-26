import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase {@see Inventario}.
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

    // Fixture de clientes de prueba
    private static Cliente cliente;
    private static Cliente clienteNoInventario;

    @BeforeClass
    public static void setUp() {
        inventario = Inventario.recuperarInstancia();
        producto = new ProductoOcio("Nombre", 31, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        productoNoInventario = new ProductoOcio("Nombre", 31, 1, 25,
                FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA);
        cliente = new ClienteEstandar("Nombre", 18, "Localidad");
        clienteNoInventario = new ClienteEstandar("Nombre", 18, "Localidad");
    }

    /**
     * Testeo del método {@link Inventario#agregarCliente(Cliente)}. Comprueba que se pueda registrar un cliente (no nulo),
     * pero no más de una vez
     */
    @Test
    public void agregarCliente() {
        assertTrue(inventario.agregarCliente(cliente));
        assertFalse(inventario.agregarCliente(cliente));
        assertFalse(inventario.agregarCliente(null));
    }

    /**
     * Testeo del método {@link Inventario#agregarProducto(Producto)}. Comprueba que se pueda agregar un producto (no nulo),
     * pero no más de una vez
     */
    @Test
    public void agregarProducto() {
        assertTrue(inventario.agregarProducto(producto));
        assertFalse(inventario.agregarProducto(producto));
        assertFalse(inventario.agregarProducto(null));
    }

    /**
     * Testeo del método {@link Inventario#venderColeccionProductos(Collection)}. Comprueba que se puedan pedir colecciones
     * de productos (basadas en la clase {@link Collection}) no nulas y con algún producto añadido que esté solo en el
     * inventario
     */
    @Test
    public void bVenderColeccionProductos() {
        List<Producto> coleccionProductos = new ArrayList<>();

        assertFalse(inventario.venderColeccionProductos(null));
        assertFalse(inventario.venderColeccionProductos(coleccionProductos));   // No contiene productos

        coleccionProductos.add(producto);

        assertTrue(inventario.venderColeccionProductos(coleccionProductos));    // Hay un producto
    }

    /**
     * Testeoo del método {@link Inventario#venderProducto(Producto, int)}. Comprueba que solo se despachen
     * pedidos de productos (no nulos) en el inventario. Comprueba que se reponga el stock del prduto si es necesario
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
     * Testeo del método {@link Inventario#recuperarProducto(Identificadores.Identificador)}. Comprueba que solo
     * se puedan recuperar productos del inventario
     */
    @Test
    public void dRecuperarProducto() {
        assertEquals(producto, inventario.recuperarProducto(producto.getIdentificador()));
        assertNotEquals(productoNoInventario, inventario.recuperarProducto(productoNoInventario.getIdentificador()));
        assertNull(inventario.recuperarProducto(null));
    }

    /**
     * Testeo del método {@link Inventario#toString()}. Comprueba que se formatee bien la cadena que
     * representa el inventario. (COMPROBACIÓN VISUAL)
     */
    @Test
    public void eMostrarDetallesStock() {
        System.out.println(inventario);
    }

    /**
     * Testeo del método {@link Inventario#eliminarCliente(Cliente)}. Comprueba que solo se puedan eliminr clientes
     * (no nulos= que estén en el inventario
     */
    @Test
    public void eliminarCliente() {
        assertTrue(inventario.eliminarCliente(cliente));
        assertFalse(inventario.eliminarCliente(cliente));
        assertFalse(inventario.eliminarCliente(clienteNoInventario));
        assertFalse(inventario.eliminarCliente(null));
    }

    /**
     * Testeo del método {@link Inventario#eliminarProducto(Producto)}. Comprueba que solo se pueden eliminar productos
     * (no nulos) que están en el inventario
     */
    @Test
    public void eliminarProducto() {
        assertTrue(inventario.eliminarProducto(producto));
        assertFalse(inventario.eliminarProducto(producto));
        assertFalse(inventario.eliminarProducto(productoNoInventario));
        assertFalse(inventario.eliminarProducto(null));
    }

    /**
     * Testeo del método {@link Inventario#recuperarInstancia()}. Comrpueba que solo exista una instancia Singleton
     * durante el ciclo de vida del programa
     */
    @Test
    public void recuperarInstancia() {
        assertSame(inventario, Inventario.recuperarInstancia());
    }

}