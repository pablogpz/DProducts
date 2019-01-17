import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * CLASE DE TESTEO de la clase {@link ClienteEstandar}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase.
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class ClienteEstandarTest {

    // Fixture de clientes estandar de prueba
    private static ClienteEstandar clienteUno;

    // Fixture de comportamientos de clientes estandar
    private static ComportamientoCompraEstandar compraEstandar;
    private static ComportamientoCompraVIP compraVIP;
    private static ComportamientoComentarioEstandar comentarioEstandar;
    private static ComportamientoComentarioVIP comentarioVIP;

    @Before
    public void setUp() {
        compraEstandar = new ComportamientoCompraEstandar();
        compraVIP = new ComportamientoCompraVIP();
        comentarioEstandar = new ComportamientoComentarioEstandar();
        comentarioVIP = new ComportamientoComentarioVIP();
        clienteUno = new ClienteEstandar("Pedro", 34, "Caceres");
    }

    /**
     * Testo del método accesor {@link ClienteEstandar#getComportamientoCompra()}
     */
    @Test
    public void getComportamientoCompra() {
        assertEquals(compraEstandar.getClass(), clienteUno.getComportamientoCompra().getClass());
    }

    /**
     * Testo del método accesor {@link ClienteEstandar#getComportamientoComentario()}
     */
    @Test
    public void getComportamientoComentario() {
        assertEquals(comentarioEstandar.getClass(), clienteUno.getComportamientoComentario().getClass());
    }

    /**
     * Testeo del método mutador {@link ClienteEstandar#getComportamientoCompra()}
     */
    @Test
    public void setComportamientoCompra() {
        clienteUno.setComportamientoCompra(compraVIP);
        assertEquals(compraVIP, clienteUno.getComportamientoCompra());
    }

    /**
     * Testeo del método mutador {@link ClienteEstandar#getComportamientoComentario()}
     */
    @Test
    public void setComportamientoComentario() {
        clienteUno.setComportamientoComentario(comentarioVIP);
        assertEquals(comentarioVIP, clienteUno.getComportamientoComentario());
    }

}
