import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase Producto.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase base utilizando la clase
 * ProductoOcio como subclase para probar los métodos de la clase base que no son sobreescritos por otras subclases
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ProductoTest {

    // Fixture de objetos Producto de prueba
    private static Producto producto;

    @Before
    public void setUp() {
        producto = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA);
    }

    /**
     * Testea que se produzcan las excepciones de argumentos ilegales al intentar instancias productos con valores
     * negativos
     */
    @Test(expected = IllegalArgumentException.class)
    public void comprobarArgumentos() {
        producto = new ProductoOcio("Nombre", 0, 1, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA);
        producto = new ProductoOcio("Nombre", -1, 1, 25, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA);
        producto = new ProductoOcio("Nombre", 1, 1, -1, FABRICANTES.ACER, PRIORIDAD_PRODUCTO.MEDIA);
    }

    /**
     * Testeo del método accesor del atribtuo 'nombre'
     */
    @Test
    public void getNombre() {
        assertEquals("Nombre", producto.getNombre());
    }

    /**
     * Testeo del método accesor del atribtuo 'identificador'
     */
    @Test
    public void getIdentificador() {
        assertNotNull(producto.getIdentificador());
    }

    /**
     * Testeo del método accesor del atribtuo 'cantidad'
     */
    @Test
    public void getCantidad() {
        assertEquals(30, producto.getCantidad());
    }

    /**
     * Testeo del método accesor del atributo 'precio'
     */
    @Test
    public void getPrecio() {
        assertEquals(1f, producto.getPrecio(), 0);
    }

    /**
     * Testeo del método accesor del atribtuo 'stockMínimo'
     */
    @Test
    public void getStockMinimo() {
        assertEquals(25, producto.getStockMinimo());
    }

    /**
     * Testeo del método accesor del atribtuo 'prioridad'
     */
    @Test
    public void getPrioridad() {
        assertEquals(PRIORIDAD_PRODUCTO.MEDIA, producto.getPrioridad());
    }

    /**
     * Testeo del método accesor del atribtuo 'fabricante'
     */
    @Test
    public void getFabricante() {
        assertEquals(FABRICANTES.ACER, producto.getFabricante());
    }


    @Test
    public void setNombre() {
        String nuevoNombre = "Nuevo nombre";
        producto.setNombre(nuevoNombre);
        assertEquals(nuevoNombre, producto.getNombre());
    }

    @Test
    public void setPrecio() {
        float nuevoPrecio = 2.5f;
        assertTrue(producto.setPrecio(nuevoPrecio));
        assertEquals(nuevoPrecio, producto.getPrecio(), 0);
        assertFalse(producto.setPrecio(0f));
        assertFalse(producto.setPrecio(-1f));
    }

    @Test
    public void setStockMinimo() {
        int nuevoStockMinimo = 20;
        assertTrue(producto.setStockMinimo(nuevoStockMinimo));
        assertEquals(nuevoStockMinimo, producto.getStockMinimo());
        assertFalse(producto.setStockMinimo(-1));
    }

    @Test
    public void setPrioridad() {
        producto.setPrioridad(PRIORIDAD_PRODUCTO.BAJA);
        assertEquals(PRIORIDAD_PRODUCTO.BAJA, producto.getPrioridad());
    }

    @Test
    public void setFabricante() {
        producto.setFabricante(FABRICANTES.AMD);
        assertEquals(FABRICANTES.AMD, producto.getFabricante());
    }

    @Test
    public void varCantidad() {
        producto.varCantidad(10);
        assertEquals(40, producto.getCantidad());
        producto.varCantidad(-10);
        assertEquals(30, producto.getCantidad());
    }

    @Test
    public void enStockMinimo() {
        assertFalse(producto.enStockMinimo());
        producto.varCantidad(-6);
        assertTrue(producto.enStockMinimo());
        producto.varCantidad(1);
        assertFalse(producto.enStockMinimo());
    }

    /**
     * Testeo del método 'entregar()'. Comprueba que solo se puedan entregar cantidades positivas distintas de 0
     * y que haya stock suficiente para cubrirlo
     */
    @Test
    public void entregar() {
        assertFalse(producto.entregar(-1));
        assertFalse(producto.entregar(9999));
        assertTrue(producto.entregar(4));
    }

    /**
     * Testeo del método 'haySuficienteStock()'. Comprueba que solo se acepten cantidades válidas en la venta de productos
     */
    @Test
    public void haySuficienteStock() {
        assertFalse(producto.haySuficienteStock(50));
        assertFalse(producto.haySuficienteStock(-1));
        assertTrue(producto.haySuficienteStock(25));
    }

}