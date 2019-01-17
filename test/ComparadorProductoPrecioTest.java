import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * CLASE DE TESTEO de la clase {@link ComparadorProductoPrecio}.
 * <p>
 * Comprueba que puedan ordenarse correctamente colecciones de productos por el criterio
 * de precio en orden descendente. Utiliza la subclase ProductoOcio para los tests
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComparadorProductoPrecioTest {

    // Fixture de colecciones de productos de prueba de prueba
    private static List<Producto> coleccion;

    // Fixture de productos de prueba
    private static Producto p1;
    private static Producto p2;
    private static Producto p3;

    @Before
    public void setUp() {
        coleccion = new ArrayList<>();
        p1 = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        p2 = new ProductoOcio("Nombre", 30, 30, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        p3 = new ProductoOcio("Nombre", 30, 10, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);

        coleccion.add(p1);
        coleccion.add(p3);
        coleccion.add(p2);
        coleccion.add(p3);
    }

    /**
     * Testeo el método {@link ComparadorProductoPrecio#compare(Producto, Producto)}. Comprueba que se ordene
     * correctamente la coleccion de productos por su precio en orden descendente
     * <p>
     * [INIT] coleccion {@literal ->} p1(1) - p3(10) - p2(30) - p3(10)
     * [FIN] coleccion {@literal ->} p2(30) - p3(10) - p3(10) - p1(1)
     */
    @Test
    public void compare() {
        coleccion.sort(new ComparadorProductoPrecio());
        assertEquals(p2, coleccion.get(0));
        assertEquals(p3, coleccion.get(1));
        assertEquals(p3, coleccion.get(2));
        assertEquals(p1, coleccion.get(3));
    }
}