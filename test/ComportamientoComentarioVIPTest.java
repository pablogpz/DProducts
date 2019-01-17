import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * CLASE DE TESTEO de la clase {@link ComportamientoComentarioVIP}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase.
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */
public class ComportamientoComentarioVIPTest {

    // Fixture de clientes estandar de prueba
    private static ClienteVIP clienteUno;
    // Fixture de productos de prueba
    private static Producto producto;
    // Fixture de comportamientos de clientes estandar
    private static ComportamientoComentarioVIP comentarioVIP = new ComportamientoComentarioVIP();

    @Before
    public void setUp() {
        clienteUno = new ClienteVIP("Rafa", 62, "Caceres");
        clienteUno.setComportamientoComentario(comentarioVIP);
        producto = new ProductoOcio("Auriculares", 140, 29.95f, 70, FABRICANTES.AOC, PRIORIDAD_PRODUCTO.BAJA);
    }

    /**
     * Testo del método {@link ComportamientoComentarioVIP#comentar(Producto, Cliente)}
     */
    @Test
    public void comentar() {
        assertTrue(clienteUno.getComportamientoComentario().comentar(producto, clienteUno));
    }

    /**
     * Testo del método {@link ComportamientoComentarioVIP#calcularPuntuacion(Producto)}
     */
    @Test
    public void calcularPuntuacion() {
        assertEquals(clienteUno.getComportamientoComentario().calcularPuntuacion(producto), 4);
    }

    /**
     * Testo del método {@link ComportamientoComentarioVIP#obtenerTexto(Producto)}
     */
    @Test
    public void obtenerTexto() {
        assertEquals(clienteUno.getComportamientoComentario().obtenerTexto(producto), "I really like this product");
    }

}
