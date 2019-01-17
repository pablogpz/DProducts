import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * CLASE DE TESTEO de la clase {@link ComportamientoCompraEstandar}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase.
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComportamientoCompraEstandarTest {

    // Fixture de clientes estandar de prueba
    private static ClienteEstandar clienteUno;

    // Fixture de productos de prueba
    private static Producto producto1;
    private static Producto producto2;

    // Fixture de comportamientos de clientes estandar
    private static ComportamientoCompraEstandar compraEstandar;

    @Before
    public void setUp() {
        compraEstandar = new ComportamientoCompraEstandar();
        clienteUno = new ClienteEstandar("Pepe", 57, "Caceres");

        producto1 = new ProductoOcio("Auriculares", 140, 29.95f, 70, FABRICANTES.AOC, PRIORIDAD_PRODUCTO.BAJA);
        producto2 = new ProductoOcio("Camiseta", 140, 29.95f, 70, FABRICANTES.AOC, PRIORIDAD_PRODUCTO.BAJA);

        clienteUno.agregarFavorito(producto1, "auri");
        clienteUno.agregarFavorito(producto2, "cami");

    }

    /**
     * Testo del método {@link ComportamientoCompraEstandar#prepararPedido(Cliente)}
     */
    @Test
    public void prepararPedido() {

        Set<Producto> pedidoEsperado = new HashSet<>();
        pedidoEsperado.add(producto1);
        pedidoEsperado.add(producto2);


        assertEquals(pedidoEsperado, clienteUno.getComportamientoCompra().prepararPedido(clienteUno));
    }

    /**
     * Testo del método {@link ComportamientoCompraEstandar#calcularPrecio(Set)}
     */
    @Test
    public void calcularPrecio() {
        Set<Producto> pedido = clienteUno.getComportamientoCompra().prepararPedido(clienteUno);
        assertEquals(2995, clienteUno.getComportamientoCompra().calcularPrecio(pedido), 0);
    }

    /**
     * Testo del método {@link ComportamientoCompraEstandar#realizarPedido(Cliente, Set)}
     */
    @Test
    public void realizarPedido() {
        Set<Producto> pedido = clienteUno.getComportamientoCompra().prepararPedido(clienteUno);
        assertTrue(clienteUno.getComportamientoCompra().realizarPedido(clienteUno, pedido));
    }

}
