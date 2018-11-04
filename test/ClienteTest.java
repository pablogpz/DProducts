import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase Cliente.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

    // Fixture de objetos Cliente de prueba
    private static Cliente cliente;

    // Instancia Singleton del GestorStock
    private static GestorStock gestorStock;

    // Fixture de produtos de prueba
    private static Producto producto;
    private static Producto producto1;
    private static Producto productoNoInventario;
    private static Producto productoNoFavorito;

    @BeforeClass
    public static void setUp() {
        cliente = new Cliente("Nombre", 18, "Localidad");
        gestorStock = GestorStock.recuperarInstancia();
        producto = new Producto("Nombre", 30, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA,
                new GregorianCalendar(2011, 3, 26), true);
        producto1 = new Producto("Nombre", 30, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA,
                new GregorianCalendar(2011, 3, 26), true);
        productoNoInventario = new Producto("Nombre", 30, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA,
                new GregorianCalendar(2011, 3, 26), true);
        productoNoFavorito = new Producto("Nombre", 30, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA,
                new GregorianCalendar(2011, 3, 26), true);

        gestorStock.agregarProducto(producto);
        gestorStock.agregarProducto(producto1);
        gestorStock.agregarProducto(productoNoFavorito);
    }

    /**
     * Testeo del método accesor del atributo 'nombre'
     */
    @Test
    public void getNombre() {
        assertEquals("Nombre", cliente.getNombre());
    }

    /**
     * Testeo del método accesor del atributo 'identificador'
     */
    @Test
    public void getIdentificador() {
        assertNotNull(cliente.getIdentificador());
    }

    /**
     * Testeo del método accesor del atributo 'edad'
     */
    @Test
    public void getEdad() {
        assertEquals(18, cliente.getEdad());
    }

    /**
     * Testeo del método accesor del atributo 'localidad'
     */
    @Test
    public void getLocalidad() {
        assertEquals("Localidad", cliente.getLocalidad());
    }

    /**
     * Testeo del método 'agregarFavorito()'. Comprueba que solo se puedan agregar productos del inventario de la tienda
     * (solo una vez) y que los aliases utilizados para recordar los productos no se repitan
     */
    @Test
    public void aAgregarFavorito() {
        assertTrue(cliente.agregarFavorito(producto,"alias1"));
        assertFalse(cliente.agregarFavorito(producto,"alias2"));
        assertFalse(cliente.agregarFavorito(productoNoInventario,"aliasNuevo"));
        assertFalse(cliente.agregarFavorito(productoNoFavorito,"alias1"));
        assertFalse(cliente.agregarFavorito(productoNoFavorito,null));
        assertFalse(cliente.agregarFavorito((Producto) null,"alias3"));
    }

    /**
     * Testeo del método 'eliminarFavorito()'. Comprueba que se eliminen de la colección de favoritos productos
     * favoritos asociados a un alias (no nulo)
     */
    @Test
    public void dEliminarFavorito() {
        assertTrue(cliente.eliminarFavorito("alias1"));
        assertFalse(cliente.eliminarFavorito("cualquierAlias"));
        assertFalse(cliente.eliminarFavorito(null));
    }

    /**
     * Testeo del método 'pedirProducto()'. Comprueba que solo se puedan hacer pedidos de produtos favoritos asociados
     * a aliases registrados. La validez de la cantidad está delegada
     */
    @Test
    public void bPedirProducto() {
        assertTrue(cliente.pedirProducto("alias1", 3));
        assertFalse(cliente.pedirProducto("aliasNoExiste", 3));
        assertFalse(cliente.pedirProducto(null, 3));
    }

    /**
     * Testeo del método 'pedirUnidadFavoritos()'. Comprueba que solo se pueda realizar el pedido si hay algún
     * producto agregado a favoritos (siempre habrá stock de productos porque se reabastecen constantemente)
     */
    @Test
    public void ePedirUnidadFavoritos() {
        assertFalse(cliente.pedirUnidadFavoritos());

        cliente.agregarFavorito(producto, "producto");

        assertTrue(cliente.pedirUnidadFavoritos());

        cliente.agregarFavorito(producto1, "producto1");
        cliente.agregarFavorito(productoNoFavorito, "productoNoFavorito");

        assertTrue(cliente.pedirUnidadFavoritos());
    }

    /**
     * Testeo del método 'comentarProducto()'. Comprueba que solo se puedan realizar comentarios sobre productos
     * favoritos asociados a claves registradas (no nulas). La validez de los parámetros está delegada
     */
    @Test
    public void cComentarProducto() {
        assertTrue(cliente.comentarProducto("alias1", "Test", 5));
        assertFalse(cliente.comentarProducto("noAlias", "Test", 5));
        assertFalse(cliente.comentarProducto(null, "Test", 5));
    }
}