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
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class ClienteVIPTest {

    // Fixture de clientes estandar de prueba
    private static ClienteVIP clienteUno;

    // Fixture de comportamientos de clientes estandar
    private static ComportamientoCompraVIP compraVIP;
    private static ComportamientoCompraEstandar compraEstandar;
    private static ComportamientoComentarioVIP comentarioVIP;
    private static ComportamientoComentarioEstandar comentarioEstandar;

    @Before
    public void setUp() {
        compraVIP = new ComportamientoCompraVIP();
        compraEstandar = new ComportamientoCompraEstandar();
        comentarioVIP = new ComportamientoComentarioVIP();
        comentarioEstandar = new ComportamientoComentarioEstandar();
        clienteUno = new ClienteVIP("Juan", 30, "Caceres");
    }

    /**
     * Testo del método accesor {@link ClienteVIP#getComportamientoCompra()}
     */
    @Test
    public void getComportamientoCompra() {
        assertEquals(compraVIP.getClass(), clienteUno.getComportamientoCompra().getClass());
    }

    /**
     * Testo del método accesor {@link ClienteVIP#getComportamientoComentario()}
     */
    @Test
    public void getComportamientoComentario() {
        assertEquals(comentarioVIP.getClass(), clienteUno.getComportamientoComentario().getClass());
    }

    /**
     * Testeo del método mutador {@link ClienteVIP#getComportamientoCompra()}
     */
    @Test
    public void setComportamientoCompra() {
        clienteUno.setComportamientoCompra(compraEstandar);
        assertEquals(compraEstandar, clienteUno.getComportamientoCompra());
    }

    /**
     * Testeo del método mutador {@link ClienteVIP#getComportamientoComentario()}
     */
    @Test
    public void setComportamientoComentario() {
        clienteUno.setComportamientoComentario(comentarioEstandar);
        assertEquals(comentarioEstandar, clienteUno.getComportamientoComentario());
    }

}
