import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase {@link DatoEstadistico}.
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

public class DatoEstadisticoTest {

    // Fixture de objetos base de prueba
    private static Cliente cliente;
    private static Comentario comentario;

    // Fixture de alias de prueba
    private static String aliasPedidos = "numPedidos";
    private static String aliasCupones = "numCupones";
    private static String aliasOfertas = "numOfertas";

    // Fixture de valores de prueba
    private static int nPedidos = 2;
    private static int nCupones = 10;
    private static int nCupones1 = 1;

    // Fixture de datos estadísticos de prueba
    private static DatoEstadistico datoEstadisticoCliente;
    private static DatoEstadistico datoEstadisticoMismoCliente;
    private static DatoEstadistico datoEstadisticoDisntinto;

    @Before
    public void setUp() {
        cliente = new ClienteEstandar("Yo", 18, "Localidad");
        comentario = new Comentario(cliente, "Texto", 4);

        datoEstadisticoCliente = new DatoEstadistico(cliente);
        datoEstadisticoMismoCliente = new DatoEstadistico(cliente);
        datoEstadisticoDisntinto = new DatoEstadistico(comentario);

        datoEstadisticoCliente.registrarDato(aliasPedidos, nPedidos);
    }

    /**
     * Testeo del método {@link DatoEstadistico#registrarDato(String, Object)}. Comprueba que solo se pueda registrar
     * un dato (no nulo) una vez
     */
    @Test
    public void registrarDato() {
        assertTrue(datoEstadisticoCliente.registrarDato(aliasCupones, nCupones));
        assertFalse(datoEstadisticoCliente.registrarDato(aliasCupones, nCupones));
        assertFalse(datoEstadisticoCliente.registrarDato(null, nCupones));
        assertFalse(datoEstadisticoCliente.registrarDato(aliasCupones, null));
        assertFalse(datoEstadisticoCliente.registrarDato(null, null));
    }

    /**
     * Testeo del método {@link DatoEstadistico#eliminarDato(String)}. Comprueba que solo se puedan eliminar datos
     * registrados mediante aliases no nulos
     */
    @Test
    public void eliminarDato() {
        assertTrue(datoEstadisticoCliente.eliminarDato(aliasPedidos));
        assertFalse(datoEstadisticoCliente.eliminarDato(aliasPedidos));
        assertFalse(datoEstadisticoCliente.eliminarDato(null));
    }

    /**
     * Testeo del método {@link DatoEstadistico#getValor(String)}. Comprueba que solo se puedan recuperar datos registrados
     * mediante aliases no nulos
     */
    @Test
    public void getValor() {
        assertEquals(nPedidos, datoEstadisticoCliente.getValor(aliasPedidos));
        assertNull(datoEstadisticoCliente.getValor(aliasOfertas));
        assertNull(datoEstadisticoCliente.getValor(null));
    }

    /**
     * Testeo del método {@link DatoEstadistico#setValor(String, Object)}. Comprueba que solo se puedan actualizar datos
     * registrados con valores no nulos y mediante aliases no nulos
     */
    @Test
    public void setValor() {
        assertTrue(datoEstadisticoCliente.setValor(aliasPedidos, nPedidos + 1));
        assertEquals(nPedidos + 1, datoEstadisticoCliente.getValor(aliasPedidos));
        assertFalse(datoEstadisticoCliente.setValor(aliasOfertas, nPedidos));
        assertFalse(datoEstadisticoCliente.setValor(null, nPedidos));
        assertFalse(datoEstadisticoCliente.setValor(aliasOfertas, null));
        assertFalse(datoEstadisticoCliente.setValor(null, null));
    }

    /**
     * Testeo del método accesor {@link DatoEstadistico#getObjetoBase()}
     */
    @Test
    public void getObjetoBase() {
        assertEquals(cliente, datoEstadisticoCliente.getObjetoBase());
    }

    /**
     * Testeo del método {@link DatoEstadistico#recuperarListaDatos()}. Comprueba que el alias añadido esté incluido en
     * la colección devuelta
     */
    @Test
    public void recuperarListaDatos() {
        assertTrue(datoEstadisticoCliente.recuperarListaDatos().contains(aliasPedidos));
        assertFalse(datoEstadisticoCliente.recuperarListaDatos().contains(aliasCupones));
    }

    /**
     * Testeo del método {@link DatoEstadistico#toString()}. Comprueba que se formatee bien la cadena que representa a un
     * dato estadístico. (COMPROBACIÓN VISUAL)
     */
    @Test
    public void testToString() {
        System.out.println(datoEstadisticoCliente);
    }

    /**
     * Testeo del método {@link DatoEstadistico#equals(Object)}. Comprueba que dos datos estadisticos sean iguales
     * solo si tienen el mismo objeto base, y que un dato estadístico y un objeto sean equivalentes si el objeto base
     * coincide con dicho objeto
     */
    @Test
    public void testEquals() {
        assertTrue(datoEstadisticoCliente.equals(datoEstadisticoCliente));
        assertTrue(datoEstadisticoCliente.equals(cliente));
        assertTrue(datoEstadisticoCliente.equals(datoEstadisticoMismoCliente));
        assertFalse(datoEstadisticoCliente.equals(datoEstadisticoDisntinto));
        assertFalse(datoEstadisticoCliente.equals(comentario));
        assertFalse(datoEstadisticoCliente.equals(null));
    }

    /**
     * Testeo del método {@link DatoEstadistico#hashCode()}. Comprueba que solo dos hashCodes coincidad si son
     * exactamente el mismo objeto
     */
    @Test
    public void testHashCode() {
        assertEquals(datoEstadisticoCliente.hashCode(), datoEstadisticoCliente.hashCode());
        assertNotEquals(datoEstadisticoCliente.hashCode(), datoEstadisticoDisntinto.hashCode());
    }
}