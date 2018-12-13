import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void getParteCasa() {
    }

    @Test
    public void setParteCasa() {
    }

    /**
     * Testeo del método accesor del atributo 'likes'
     */
    @Test
    public void getLikes() {
        assertEquals(0, productoHogar.getLikes());
    }

    /**
     * Testo del método mutador del atributo 'likes'. Comprueba que incremente en 1 el contador de 'likes'
     */
    @Test
    public void like() {
        productoHogar.like();
        assertEquals(1, productoHogar.getLikes());
    }

    /**
     * Testeo del método mutador del atributo 'likes'. Comprueba que decremente en 1 el contador de 'likes'
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
     * Testeo del método accesor del atributo 'descuento'
     */
    @Test
    public void getDescuento() {
        assertEquals(-0.05f, productoHogar.getDescuento(), 0);

    }

    /**
     * Testeo del método mutador del atributo 'descuento'
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
     * producto del hogar. (El identificador ha sido eliminado por motivos de simplicidad en su
     * detección)
     */
    @Test
    public void testToString() {
        final String cadena = productoHogar.toString();
        assertEquals("PRODUCTO\tNombre-ACER\n" +
                "\tCantidad en stock : 30\n" +
                "\tCantidad en stock mínima : 25\n" +
                "\tPrioridad de reabastecimiento : MEDIA\n" +
                "\tComentarios:\n" +
                "\n" +
                "\tParte de la casa : ASEO\n" +
                "\tLikes : 0\n" +
                "\tDescuento aplicable : -5.0%", cadena.substring(0, 22) + cadena.substring(45));
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