import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase ProductoOcio.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase.
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ProductoOcioTest {

    // Fixture de productos de ocio de prueba
    private static ProductoOcio productoOcio;
    private static ProductoOcio productoDistinto;

    @Before
    public void setUp() {
        productoOcio = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        productoDistinto = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
    }

    /**
     * Testeo del método accesor del atributo 'descuento'
     */
    @Test
    public void getDescuento() {
        assertEquals(0.2f, productoOcio.getDescuento(), 0);
    }

    /**
     * Testeo del método mutador del atributo 'descuento'
     */
    @Test
    public void setDescuento() {
        float nuevoDescuento = 0.1f;

        productoOcio.setDescuento(nuevoDescuento);
        assertEquals(nuevoDescuento, productoOcio.getDescuento(), 0);
    }

    /**
     * Testeo del método 'calcularPrecioDescontado()'. Comprueba que devuelva el precio final de un produto
     * descontable tanto con descuentos positivos como negativos
     */
    @Test
    public void calcularPrecioDescontado() {
        float nuevoDescuento = 0.1f;

        assertEquals(1.2f, productoOcio.calcularPrecioDescontado(), 0);
        productoOcio.setDescuento(nuevoDescuento);
        assertEquals(1.1f, productoOcio.calcularPrecioDescontado(), 0);
    }

    /**
     * Testeo del método 'toString()'. Comprueba que se formatee bien la cadena que representa a un
     * producto de ocio. (El identificador ha sido eliminado por motivos de simplicidad en su
     * detección)
     */
    @Test
    public void testToString() {
        final String cadena = productoOcio.toString();
        assertEquals("PRODUCTO\tNombre-ACER\n" +
                "\tCantidad en stock : 30\n" +
                "\tCantidad en stock mínima : 25\n" +
                "\tPrioridad de reabastecimiento : MEDIA\n" +
                "\tComentarios:\n" +
                "\n" +
                "\tDescuento aplicable : 20.0%", cadena.substring(0, 22) + cadena.substring(45));
    }

    /**
     * Testeo del método 'equals()'. Comprueba que dos productos de ocio sean iguales solo si sus atributos
     * son exáctamente iguales
     */
    @Test
    public void testEquals() {
        assertFalse(productoOcio.equals(null));
        assertTrue(productoOcio.equals(productoOcio));
        assertFalse(productoOcio.equals(productoDistinto));
    }

    /**
     * Testeo del método 'hashCode()'. Comprueba que solo dos hashCodes coincidad si son exactamente el mismo objeto
     */
    @Test
    public void testHashCode() {
        assertEquals(productoOcio.hashCode(), productoOcio.hashCode());
        assertNotEquals(productoOcio.hashCode(), productoDistinto.hashCode());
    }

}