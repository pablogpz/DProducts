import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase {@link ProductoOcio}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase.
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
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
     * Testeo del método accesor {@link ProductoOcio#getDescuento()} ()}
     */
    @Test
    public void getDescuento() {
        assertEquals(0.2f, productoOcio.getDescuento(), 0);
    }

    /**
     * Testeo del método mutador {@link ProductoOcio#setDescuento(float)}
     */
    @Test
    public void setDescuento() {
        float nuevoDescuento = 0.1f;

        productoOcio.setDescuento(nuevoDescuento);
        assertEquals(nuevoDescuento, productoOcio.getDescuento(), 0);
    }

    /**
     * Testeo del método {@link ProductoOcio#calcularPrecioDescontado()}. Comprueba que devuelva el precio final de un produto
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
     * Testeo del método {@link ProductoOcio#aRegistro()}. Comprueba el formato de entrada de registro generado
     * (COMPROBACIÓN VISUAL)
     */
    @Test
    public void aRegistro() {
        System.out.println(productoOcio.aRegistro());
    }

    /**
     * Testeo del método {@link ProductoOcio#aRegistroDescuento()}. Comprueba el formato de entrada de registro
     * generado (COMPROBACIÓN VISUAL)
     */
    @Test
    public void aRegistroDescuento() {
        System.out.println(productoOcio.aRegistroDescuento());
    }

    /**
     * Testeo del método {@link ProductoOcio#toString()}. Comprueba que se formatee bien la cadena que representa a un
     * producto de ocio. (COMPROBACIÓN VISUAL)
     */
    @Test
    public void testToString() {
        System.out.println(productoOcio);
    }

    /**
     * Testeo del método {@link ProductoOcio#equals(Object)}. Comprueba que dos productos de ocio sean iguales
     * solo si sus atributos son exáctamente iguales
     */
    @Test
    public void testEquals() {
        assertFalse(productoOcio.equals(null));
        assertTrue(productoOcio.equals(productoOcio));
        assertFalse(productoOcio.equals(productoDistinto));
    }

    /**
     * Testeo del método {@link ProductoOcio#hashCode()}. Comprueba que solo dos hashCodes coincidad si son
     * exactamente el mismo objeto
     */
    @Test
    public void testHashCode() {
        assertEquals(productoOcio.hashCode(), productoOcio.hashCode());
        assertNotEquals(productoOcio.hashCode(), productoDistinto.hashCode());
    }

}