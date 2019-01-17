import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase {@link Inventario}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventarioTest {

    // Instancia Singleton de la clase Inventario
    private static Inventario inventario;

    // Fixture de productos de prueba
    private static Producto producto;
    private static Producto producto1;
    private static Producto productoNoInventario;

    // Fixture de clientes de prueba
    private static Cliente cliente;
    private static Cliente clienteNoInventario;

    @BeforeClass
    public static void setUp() {
        inventario = Inventario.recuperarInstancia();
        producto = new ProductoOcio("Nombre", 31, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        producto1 = new ProductoOcio("Otro nombre", 31, 1, 25, FABRICANTES.AMD,
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
     * Testeo del método {@link Inventario#venderColeccionProductos(Collection, Cliente)}. Comprueba que se puedan pedir colecciones
     * de productos (basadas en la clase {@link Collection}) no nulas y con algún producto añadido que esté solo en el
     * inventario
     */
    @Test
    public void bVenderColeccionProductos() {
        List<Producto> coleccionProductos = new ArrayList<>();

        assertNull(inventario.venderColeccionProductos(null, null));
        assertNull(inventario.venderColeccionProductos(coleccionProductos, cliente));       // No contiene productos

        coleccionProductos.add(producto);

        assertNotNull(inventario.venderColeccionProductos(coleccionProductos, cliente));    // Hay un producto
        inventario.registrarGastoCliente(cliente, producto.getPrecio() * 1);

    }

    /**
     * Testeoo del método {@link Inventario#venderProducto(Producto, Cliente, int)}. Comprueba que solo se despachen
     * pedidos de productos (no nulos) en el inventario. Comprueba que se reponga el stock del prduto si es necesario
     */
    @Test
    public void bVenderProducto() {
        assertEquals(0, inventario.venderProducto(producto, cliente, 5));
        inventario.registrarGastoCliente(cliente, producto.getPrecio() * 5);
        assertEquals(25, producto.getCantidad());
        assertEquals(1, inventario.venderProducto(producto, cliente, 1));
        inventario.registrarGastoCliente(cliente, producto.getPrecio() * 1);
        assertEquals(99, producto.getCantidad());                                   // Se ha repuesto el producto
        assertEquals(-1, inventario.venderProducto(productoNoInventario, cliente, 1));
        assertEquals(-1, inventario.venderProducto(null, cliente, 1));
    }

    /**
     * Testeo del método {@link Inventario#recuperarClienteMasGastos()}. Comprueba que se devuelva el cliente que más
     * gastos ha realizado en pedidos (gasto total de 7.0 sin contar descuentos)
     */
    @Test
    public void dRecuperarClienteMasGastos() {
        assertEquals(cliente, inventario.recuperarClienteMasGastos().get(Inventario.CLAVE_CLIENTE_MAS_GASTOS_CLIENTE));
        assertEquals(7.0, (float) inventario.recuperarClienteMasGastos().get(Inventario.CLAVE_CLIENTE_MAS_GASTOS_GASTO), 0.1);
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
     * Testeo del método {@link Inventario#recuperarProductoMasComentado()}. Comprueba que se devuelva el producto con
     * más comentarios (1 comentario)
     */
    @Test
    public void dRecuperarProductoMasComentado() {
        inventario.agregarProducto(producto1);
        ((ProductoComentable) producto1).comentar(new Comentario(cliente, "Texto", 5));

        assertEquals(producto1, inventario.recuperarProductoMasComentado());
        assertEquals(1, ((ProductoComentable) inventario.recuperarProductoMasComentado()).recuperarNumComentarios());
    }

    /**
     * Testeo del método {@link Inventario#recuperarProductoMasVendido()}. Comprueba que se devuelva el producto más
     * vendido y la cantidad de unicades vendidas en total (7 unidades)
     */
    @Test
    public void dRecuperarProductoMasVendido() {
        assertEquals(producto, inventario.recuperarProductoMasVendido().get(Inventario.CLAVE_PRODUCTO_MAS_VENDIDO_PRODUCTO));
        assertEquals(7, inventario.recuperarProductoMasVendido().get(Inventario.CLAVE_PRODUCTO_MAS_VENDIDO_VENTAS));
    }

    /**
     * Testeo del método {@link Inventario#recuperarProductosVendidos()}. Comprueba que se registren todos los productos
     * que han sido pedidos durante una simulación (Se pide 1 producto)
     */
    @Test
    public void dRecuperarProductosVendidos() {
        List<Producto> productos = new ArrayList<>();
        Iterator<Producto> itProductos = inventario.recuperarProductosVendidos();
        while (itProductos.hasNext()) {
            productos.add(itProductos.next());
        }

        assertTrue(productos.contains(producto));
        assertEquals(1, productos.size());
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
     * Testeo del método {@link Inventario#eliminarCliente(Identificadores.Identificador)}. Comprueba que solo se puedan
     * eliminr clientes (no nulos) que estén en el inventario
     */
    @Test
    public void eliminarCliente() {
        assertTrue(inventario.eliminarCliente(cliente.getIdentificador()));
        assertFalse(inventario.eliminarCliente(cliente.getIdentificador()));
        assertFalse(inventario.eliminarCliente(clienteNoInventario.getIdentificador()));
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