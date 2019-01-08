import org.junit.Before;
import org.junit.Test;

import java.time.Month;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase {@link ProductoAlimentacion}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase.
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ProductoAlimentacionTest {

    // Fixture de productos de alimentacion de prueba
    private static ProductoAlimentacion productoAlimentacion;
    private static ProductoAlimentacion productoDistinto;

    @Before
    public void setUp() {
        productoAlimentacion = new ProductoAlimentacion("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA, Month.JANUARY);
        productoDistinto = new ProductoAlimentacion("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA, Month.JANUARY);
    }

    /**
     * Testo del método accesor {@link ProductoAlimentacion#getMesCaducidad()}
     */
    @Test
    public void getMesCaducidad() {
        assertEquals(Month.JANUARY, productoAlimentacion.getMesCaducidad());
    }

    /**
     * Testeo del método mutador {@link ProductoAlimentacion#getMesCaducidad()}
     */
    @Test
    public void setMesCaducidad() {
        productoAlimentacion.setMesCaducidad(Month.APRIL);
        assertEquals(Month.APRIL, productoAlimentacion.getMesCaducidad());
    }

    /**
     * Testeo del método accesor {@link ProductoAlimentacion#getLikes()}
     */
    @Test
    public void getLikes() {
        assertEquals(0, productoAlimentacion.getLikes());
    }

    /**
     * Testo del método mutador {@link ProductoAlimentacion#getLikes()}. Comprueba que incremente en 1 el contador de likes
     */
    @Test
    public void like() {
        productoAlimentacion.like();
        assertEquals(1, productoAlimentacion.getLikes());
    }

    /**
     * Testeo del método mutador {@link ProductoAlimentacion#getLikes()}. Comprueba que decremente en 1 el contador de likes
     * siempre y cuando no lo deje por debajo de 0
     */
    @Test
    public void unlike() {
        productoAlimentacion.unlike();
        assertEquals(0, productoAlimentacion.getLikes());
        productoAlimentacion.like();
        productoAlimentacion.unlike();
        assertEquals(0, productoAlimentacion.getLikes());
    }

    /**
     * Testeo del método accesor {@link ProductoAlimentacion#getDescuento()}
     */
    @Test
    public void getDescuento() {
        assertEquals(-0.1f, productoAlimentacion.getDescuento(), 0);

    }

    /**
     * Testeo del método mutador {@link ProductoAlimentacion#getDescuento()}
     */
    @Test
    public void setDescuento() {
        float nuevoDescuento = 0.1f;

        productoAlimentacion.setDescuento(nuevoDescuento);
        assertEquals(nuevoDescuento, productoAlimentacion.getDescuento(), 0);
    }

    /**
     * Testeo del método {@link ProductoAlimentacion#calcularPrecioDescontado()}. Comprueba que devuelva el
     * precio final de un produto descontable tanto con descuentos positivos como negativos
     */
    @Test
    public void calcularPrecioDescontado() {
        float nuevoDescuento = 0.1f;

        assertEquals(0.9f, productoAlimentacion.calcularPrecioDescontado(), 0);
        productoAlimentacion.setDescuento(nuevoDescuento);
        assertEquals(1.1f, productoAlimentacion.calcularPrecioDescontado(), 0);
    }

    /**
     * Testeo del método {@link ProductoAlimentacion#toString()}. Comprueba que se formatee bien la cadena que
     * representa a un producto de alimentación. (COMPROBACIÓN VISUAL)
     */
    @Test
    public void testToString() {
        System.out.println(productoAlimentacion);
    }

    /**
     * Testeo del método {@link ProductoAlimentacion#equals(Object)}. Comprueba que dos productos de alimentación sean
     * iguales solo si sus atributos son exáctamente iguales
     */
    @Test
    public void testEquals() {
        assertFalse(productoAlimentacion.equals(null));
        assertTrue(productoAlimentacion.equals(productoAlimentacion));
        assertFalse(productoAlimentacion.equals(productoDistinto));
    }

    /**
     * Testeo del método {@link ProductoAlimentacion#hashCode()}. Comprueba que solo dos hashCodes coincidad si son
     * exactamente el mismo objeto
     */
    @Test
    public void testHashCode() {
        assertEquals(productoAlimentacion.hashCode(), productoAlimentacion.hashCode());
        assertNotEquals(productoAlimentacion.hashCode(), productoDistinto.hashCode());
    }

}