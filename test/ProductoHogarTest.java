import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase {@see ProductoHogar}.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase.
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ProductoHogarTest {

    // Fixture de productos del hogar de prueba
    private static ProductoHogar productoHogar;
    private static ProductoHogar productoDistinto;

    @Before
    public void setUp() {
        productoHogar = new ProductoHogar("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA, PARTES_CASA.ASEO);
        productoDistinto = new ProductoHogar("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA, PARTES_CASA.ASEO);
    }

    /**
     * Testeo del método accesor {@link ProductoHogar#getParteCasa()}
     */
    @Test
    public void getParteCasa() {
        assertEquals(PARTES_CASA.ASEO, productoHogar.getParteCasa());
    }

    /**
     * Testeo del método mutador {@link ProductoHogar#getParteCasa()}
     */
    @Test
    public void setParteCasa() {
        productoHogar.setParteCasa(PARTES_CASA.COCINA);
        assertEquals(PARTES_CASA.COCINA, productoHogar.getParteCasa());
    }

    /**
     * Testeo del método accesor {@link ProductoHogar#getLikes()}
     */
    @Test
    public void getLikes() {
        assertEquals(0, productoHogar.getLikes());
    }

    /**
     * Testo del método mutador {@link ProductoHogar#getLikes()}. Comprueba que incremente en 1 el contador de likes
     */
    @Test
    public void like() {
        productoHogar.like();
        assertEquals(1, productoHogar.getLikes());
    }

    /**
     * Testeo del método mutador {@link ProductoHogar#getLikes()}. Comprueba que decremente en 1 el contador de likes
     * siempre y cuando no lo deje por debajo de 0
     */
    @Test
    public void unlike() {
        productoHogar.unlike();
        assertEquals(0, productoHogar.getLikes());
        productoHogar.like();
        productoHogar.unlike();
        assertEquals(0, productoHogar.getLikes());
    }

    /**
     * Testeo del método accesor {@link ProductoHogar#getDescuento()}
     */
    @Test
    public void getDescuento() {
        assertEquals(-0.05f, productoHogar.getDescuento(), 0);
    }

    /**
     * Testeo del método mutador {@link ProductoHogar#getDescuento()}
     */
    @Test
    public void setDescuento() {
        float nuevoDescuento = 0.1f;

        productoHogar.setDescuento(nuevoDescuento);
        assertEquals(nuevoDescuento, productoHogar.getDescuento(), 0);
    }

    /**
     * Testeo del método 'calcularPrecioDescontado()'. Comprueba que devuelva el precio final de un produto
     * descontable tanto con descuentos positivos como negativos
     */
    @Test
    public void calcularPrecioDescontado() {
        float nuevoDescuento = 0.1f;

        assertEquals(0.95f, productoHogar.calcularPrecioDescontado(), 0);
        productoHogar.setDescuento(nuevoDescuento);
        assertEquals(1.1f, productoHogar.calcularPrecioDescontado(), 0);
    }

    /**
     * Testeo del método 'toString()'. Comprueba que se formatee bien la cadena que representa a un
     * producto del hogar. (COMPROBACIÓN VISUAL)
     */
    @Test
    public void testToString() {
        System.out.println(productoHogar);
    }

    /**
     * Testeo del método 'equals()'. Comprueba que dos productos del hogar sean iguales solo si sus atributos
     * son exáctamente iguales
     */
    @Test
    public void testEquals() {
        assertFalse(productoHogar.equals(null));
        assertTrue(productoHogar.equals(productoHogar));
        assertFalse(productoHogar.equals(productoDistinto));
    }

    /**
     * Testeo del método 'hashCode()'. Comprueba que solo dos hashCodes coincidad si son exactamente el mismo objeto
     */
    @Test
    public void testHashCode() {
        assertEquals(productoHogar.hashCode(), productoHogar.hashCode());
        assertNotEquals(productoHogar.hashCode(), productoDistinto.hashCode());
    }

}