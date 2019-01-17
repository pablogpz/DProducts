import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashSet;
import java.util.Set;

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
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

    // Fixture de objetos Cliente de prueba
    private static Cliente cliente;
    private static Cliente clienteV;

    // Instancia Singleton del Inventario
    private static Inventario inventario;

    // Fixture de produtos de prueba
    private static Producto producto;
    private static Producto producto1;
    private static Producto productoNoInventario;
    private static Producto productoNoFavorito;

    private static Producto producto0;
    private static Producto producto00;

    @BeforeClass
    public static void setUp() {
        cliente = new ClienteEstandar("Nombre", 18, "Localidad");
        clienteV = new ClienteVIP("Nombre", 20, "Localidad");
        inventario = Inventario.recuperarInstancia();
        producto = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        producto1 = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        productoNoInventario = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        productoNoFavorito = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);

        producto0 = new ProductoOcio("Nombre", 150, 20, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        producto00 = new ProductoOcio("Nombre", 150, 30, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);


        inventario.agregarProducto(producto);
        inventario.agregarProducto(producto1);
        inventario.agregarProducto(productoNoFavorito);

        inventario.agregarProducto(producto0);
        inventario.agregarProducto(producto00);

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
     * Testeo del método {@link Cliente#aRegistro()}. Comprueba el formato de entrada de registro generado
     * (COMPROBACIÓN VISUAL)
     */
    @Test
    public void aRegistro() {
        System.out.println(cliente.aRegistro());
    }

    /*

    INCORPORACION DE LAS PRUEBAS CORRESPONDIENTES A ComportamientoCompraEstandar y ComportamientoCompraVIP

    */

    /**
     * Testo del método {@link ComportamientoCompraEstandar#prepararPedido(Cliente)}
     */
    @Test
    public void prepararPedidoEstandar() {

        Set<Producto> pedidoEsperado = new HashSet<>();
        pedidoEsperado.add(producto00);
        pedidoEsperado.add(producto0);

        cliente.agregarFavorito(producto0, "favorito1");
        cliente.agregarFavorito(producto00, "favorito2");

        assertEquals(pedidoEsperado, ((ClienteEstandar) cliente).getComportamientoCompra().prepararPedido(cliente));
    }

    /**
     * Testo del método {@link ComportamientoCompraEstandar#calcularPrecio(Set)}
     */
    @Test
    public void calcularPrecioEstandar() {

        cliente.agregarFavorito(producto0, "favorito1");
        cliente.agregarFavorito(producto00, "favorito2");

        Set<Producto> pedido = (((ClienteEstandar) cliente).getComportamientoCompra().prepararPedido(cliente));

        assertEquals((50 * 20 + 50 * 30) * 1.2f, ((ClienteEstandar) cliente).getComportamientoCompra().calcularPrecio(pedido), 0);

    }

    /**
     * Testo del método {@link ComportamientoCompraEstandar#realizarPedido(Cliente, Set)}
     */
    @Test
    public void realizarPedidoEstandar() {

        cliente.agregarFavorito(producto0, "favorito1");
        cliente.agregarFavorito(producto00, "favorito2");

        Set<Producto> pedido = (((ClienteEstandar) cliente).getComportamientoCompra().prepararPedido(cliente));

        assertTrue(((ClienteEstandar) cliente).getComportamientoCompra().realizarPedido(cliente, pedido));

    }

    /**
     * Testo del método {@link ComportamientoCompraVIP#prepararPedido(Cliente)}
     */
    @Test
    public void prepararPedidoVIP() {

        Set<Producto> pedidoEsperado = new HashSet<>();
        pedidoEsperado.add(producto00);
        pedidoEsperado.add(producto0);

        clienteV.agregarFavorito(producto0, "favorito1");
        clienteV.agregarFavorito(producto00, "favorito2");

        assertEquals(pedidoEsperado, ((ClienteVIP) clienteV).getComportamientoCompra().prepararPedido(clienteV));
    }

    /**
     * Testo del método {@link ComportamientoCompraVIP#calcularPrecio(Set)}
     */
    @Test
    public void calcularPrecioVIP() {

        clienteV.agregarFavorito(producto0, "favorito1");
        clienteV.agregarFavorito(producto00, "favorito2");

        Set<Producto> pedido = (((ClienteVIP) clienteV).getComportamientoCompra().prepararPedido(clienteV));

        assertEquals((20 + 30) * 1.2f, ((ClienteVIP) clienteV).getComportamientoCompra().calcularPrecio(pedido), 0.1);
    }

    /**
     * Testo del método {@link ComportamientoCompraVIP#realizarPedido(Cliente, Set)}
     */
    @Test
    public void realizarPedidoVIP() {

        clienteV.agregarFavorito(producto0, "favorito1");
        clienteV.agregarFavorito(producto00, "favorito2");

        Set<Producto> pedido = (((ClienteVIP) clienteV).getComportamientoCompra().prepararPedido(clienteV));

        assertTrue(((ClienteVIP) clienteV).getComportamientoCompra().realizarPedido(clienteV, pedido));
    }


}