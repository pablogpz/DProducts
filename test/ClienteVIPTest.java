import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * CLASE DE TESTEO de la clase {@link ClienteVIP}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase.
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ClienteVIPTest {

    // Fixture de clientes estandar de prueba
    private static ClienteVIP clienteUno;
    // Fixture de comportamientos de clientes estandar
    private static ComportamientoCompraVIP compraVIP = new ComportamientoCompraVIP();
    private static ComportamientoCompraVIP compraVIP2 = new ComportamientoCompraVIP();
    private static ComportamientoComentarioVIP comentarioVIP = new ComportamientoComentarioVIP();
    private static ComportamientoComentarioVIP comentarioVIP2 = new ComportamientoComentarioVIP();

    @Before
    public void setUp() {
        clienteUno = new ClienteVIP("Juan", 30, "Caceres");
    }

    /**
     * Testo del método accesor {@link ClienteVIP#getComportamientoCompra()}
     */
    @Test
    public void getComportamientoCompra() {
        clienteUno.setComportamientoCompra(compraVIP);
        assertEquals(compraVIP, clienteUno.getComportamientoCompra());
    }

    /**
     * Testo del método accesor {@link ClienteVIP#getComportamientoComentario()}
     */
    @Test
    public void getComportamientoComentario() {
        clienteUno.setComportamientoComentario(comentarioVIP);
        assertEquals(comentarioVIP, clienteUno.getComportamientoComentario());
    }

    /**
     * Testeo del método mutador {@link ClienteVIP#getComportamientoCompra()}
     */
    @Test
    public void setComportamientoCompra() {
        clienteUno.setComportamientoCompra(compraVIP2);
        assertEquals(compraVIP2, clienteUno.getComportamientoCompra());
    }

    /**
     * Testeo del método mutador {@link ClienteVIP#getComportamientoComentario()}
     */
    @Test
    public void setComportamientoComentario() {
        clienteUno.setComportamientoComentario(comentarioVIP2);
        assertEquals(comentarioVIP2, clienteUno.getComportamientoComentario());
    }


}
