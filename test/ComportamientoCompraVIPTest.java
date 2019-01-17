import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * CLASE DE TESTEO de la clase {@link ComportamientoCompraVIP}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase.
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComportamientoCompraVIPTest {

    // Fixture de clientes estandar de prueba
    private static ClienteVIP clienteUno;

    // Fixture de productos de prueba
    private static Producto producto1;
    private static Producto producto2;

    // Fixture de comportamientos de clientes estandar
    private static ComportamientoCompraVIP compraVIP;

    @Before
    public void setUp() {
        compraVIP = new ComportamientoCompraVIP();
        clienteUno = new ClienteVIP("Pepe", 57, "Caceres");

        producto1 = new ProductoOcio("Auriculares", 140, 29.95f, 70, FABRICANTES.AOC, PRIORIDAD_PRODUCTO.BAJA);
        producto2 = new ProductoOcio("Camiseta", 140, 29.95f, 70, FABRICANTES.AOC, PRIORIDAD_PRODUCTO.BAJA);

        clienteUno.agregarFavorito(producto1, "auri");
        clienteUno.agregarFavorito(producto2, "cami");
    }

    /**
     * Testo del método {@link ComportamientoCompraVIP#prepararPedido(Cliente)}
     */
    @Test
    public void prepararPedido() {

    }

    /**
     * Testo del método {@link ComportamientoCompraVIP#calcularPrecio(Cliente, Set)}
     */
    @Test
    public void calcularPrecio() {

    }

    /**
     * Testo del método {@link ComportamientoCompraVIP#realizarPedido(Cliente)}
     */
    @Test
    public void realizarPedido() {

    }

}
