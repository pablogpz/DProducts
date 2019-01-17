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
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ClienteEstandarTest {

    // Fixture de clientes estandar de prueba
    private static ClienteEstandar clienteUno;

    // Fixture de comportamientos de clientes estandar
    private static ComportamientoCompraEstandar compraEstandar;
    private static ComportamientoCompraEstandar compraEstandar2;
    private static ComportamientoComentarioEstandar comentarioEstandar;
    private static ComportamientoComentarioEstandar comentarioEstandar2;

    @Before
    public void setUp() {
        compraEstandar = new ComportamientoCompraEstandar();
        compraEstandar2 = new ComportamientoCompraEstandar();
        comentarioEstandar = new ComportamientoComentarioEstandar();
        comentarioEstandar2 = new ComportamientoComentarioEstandar();
        clienteUno = new ClienteEstandar("Pedro", 34, "Caceres");
    }

    /**
     * Testo del método accesor {@link ClienteEstandar#getComportamientoCompra()}
     */
    @Test
    public void getComportamientoCompra() {
        clienteUno.setComportamientoCompra(compraEstandar);
        assertEquals(compraEstandar, clienteUno.getComportamientoCompra());
    }

    /**
     * Testo del método accesor {@link ClienteEstandar#getComportamientoComentario()}
     */
    @Test
    public void getComportamientoComentario() {
        clienteUno.setComportamientoComentario(comentarioEstandar);
        assertEquals(comentarioEstandar, clienteUno.getComportamientoComentario());
    }

    /**
     * Testeo del método mutador {@link ClienteEstandar#getComportamientoCompra()}
     */
    @Test
    public void setComportamientoCompra() {
        clienteUno.setComportamientoCompra(compraEstandar2);
        assertEquals(compraEstandar2, clienteUno.getComportamientoCompra());
    }

    /**
     * Testeo del método mutador {@link ClienteEstandar#getComportamientoComentario()}
     */
    @Test
    public void setComportamientoComentario() {
        clienteUno.setComportamientoComentario(comentarioEstandar2);
        assertEquals(comentarioEstandar2, clienteUno.getComportamientoComentario());
    }


}
