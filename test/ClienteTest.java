import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase {@link Cliente}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

    // Fixture de objetos Cliente de prueba
    private static Cliente cliente;

    // Instancia Singleton del Inventario
    private static Inventario inventario;

    // Fixture de produtos de prueba
    private static Producto producto;
    private static Producto producto1;
    private static Producto productoNoInventario;
    private static Producto productoNoFavorito;

    @BeforeClass
    public static void setUp() {
        cliente = new ClienteEstandar("Nombre", 18, "Localidad");
        inventario = Inventario.recuperarInstancia();
        producto = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        producto1 = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        productoNoInventario = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        productoNoFavorito = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);

        inventario.agregarProducto(producto);
        inventario.agregarProducto(producto1);
        inventario.agregarProducto(productoNoFavorito);
    }

    /**
     * Testea que se produzcan las excepciones de argumentos ilegales al intentar instanciar clientes con nombres
     * y localidades vacías y edades no naturales
     */
    @Test(expected = IllegalArgumentException.class)
    public void comprobarArgumentos() {
        Cliente cliente;

        cliente = new ClienteEstandar("", 18, "Localidad");
        cliente = new ClienteEstandar("Nombre", 0, "Localidad");
        cliente = new ClienteEstandar("Nombre", -1, "Localidad");
        cliente = new ClienteEstandar("Nombre", 18, "");

    }

    /**
     * Testeo del método accesor {@link Cliente#getNombre()}
     */
    @Test
    public void getNombre() {
        assertEquals("Nombre", cliente.getNombre());
    }

    /**
     * Testeo del método accesor {@link Cliente#getIdentificador()}
     */
    @Test
    public void getIdentificador() {
        assertNotNull(cliente.getIdentificador());
    }

    /**
     * Testeo del método accesor {@link Cliente#getEdad()}
     */
    @Test
    public void getEdad() {
        assertEquals(18, cliente.getEdad());
    }

    /**
     * Testeo del método accesor {@link Cliente#getLocalidad()}
     */
    @Test
    public void getLocalidad() {
        assertEquals("Localidad", cliente.getLocalidad());
    }

    /**
     * Testeo del método accesor {@link Cliente#getTienda()}
     */
    @Test
    public void getTienda() {
        assertEquals(Inventario.recuperarInstancia(), cliente.getTienda());
    }

    /**
     * Testeo del método mutador {@link Cliente#getNombre()}
     */
    @Test
    public void setNombre() {
        String nuevoNombre = "Nuevo nombre";
        assertTrue(cliente.setNombre(nuevoNombre));
        assertEquals(nuevoNombre, cliente.getNombre());
    }

    /**
     * Testeo del método mutador {@link Cliente#getEdad()}
     */
    @Test
    public void setEdad() {
        int nuevaEdad = 11;
        assertTrue(cliente.setEdad(nuevaEdad));
        assertEquals(nuevaEdad, cliente.getEdad());
        assertFalse(cliente.setEdad(0));
        assertFalse(cliente.setEdad(-1));
    }

    /**
     * Testeo del método mutador {@link Cliente#getLocalidad()}
     */
    @Test
    public void setLocalidad() {
        String nuevaLocalidad = "Nueva localidad";
        assertTrue(cliente.setLocalidad(nuevaLocalidad));
        assertEquals(nuevaLocalidad, cliente.getLocalidad());
        assertFalse(cliente.setLocalidad(""));
    }

    /**
     * Testeo del método {@link Cliente#agregarFavorito(Producto, String)}. Comprueba que solo se puedan agregar
     * productos del inventario de la tienda (solo una vez) y que los aliases utilizados para recordar
     * los productos no se repitan
     */
    @Test
    public void agregarFavorito() {
        assertTrue(cliente.agregarFavorito(producto, "alias1"));
        assertFalse(cliente.agregarFavorito(producto, "alias2"));
        assertFalse(cliente.agregarFavorito(productoNoInventario, "aliasNuevo"));
        assertFalse(cliente.agregarFavorito(productoNoFavorito, "alias1"));
        assertFalse(cliente.agregarFavorito(productoNoFavorito, null));
        assertFalse(cliente.agregarFavorito((Producto) null, "alias3"));
    }

    /**
     * Testeo del método 'pedirProducto()'. Comprueba que solo se puedan hacer pedidos de produtos favoritos asociados
     * a aliases registrados. La validez de la cantidad está delegada
     */
    // TODO - mover implementación
    @Test
    public void bPedirProducto() {
//        assertTrue(cliente.pedirProducto("alias1", 3));
//        assertFalse(cliente.pedirProducto("aliasNoExiste", 3));
//        assertFalse(cliente.pedirProducto(null, 3));
    }

    /**
     * Testeo del método {@link Cliente#eliminarFavorito(String)}. Comprueba que se eliminen de la colección de
     * favoritos productos favoritos asociados a un alias (no nulo)
     */
    @Test
    public void dEliminarFavorito() {
        assertTrue(cliente.eliminarFavorito("alias1"));
        assertFalse(cliente.eliminarFavorito("cualquierAlias"));
        assertFalse(cliente.eliminarFavorito(null));
    }

    /**
     * Testeo del método 'pedirUnidadFavoritos()'. Comprueba que solo se pueda realizar el pedido si hay algún
     * producto agregado a favoritos (siempre habrá stock de productos porque se reabastecen constantemente)
     */
    // TODO - mover implementación
    @Test
    public void ePedirUnidadFavoritos() {
//        assertFalse(cliente.pedirUnidadFavoritos());
//
//        cliente.agregarFavorito(producto, "producto");
//
//        assertTrue(cliente.pedirUnidadFavoritos());
//
//        cliente.agregarFavorito(producto1, "producto1");
//        cliente.agregarFavorito(productoNoFavorito, "productoNoFavorito");
//
//        assertTrue(cliente.pedirUnidadFavoritos());
    }

    /**
     * Testeo del método 'comentarProducto()'. Comprueba que solo se puedan realizar comentarios sobre productos
     * favoritos asociados a claves registradas (no nulas). La validez de los parámetros está delegada
     */
    // TODO - mover implementación
    @Test
    public void cComentarProducto() {
//        assertTrue(cliente.comentarProducto("alias1", "Test", 5));
//        assertFalse(cliente.comentarProducto("noAlias", "Test", 5));
//        assertFalse(cliente.comentarProducto(null, "Test", 5));
    }

}