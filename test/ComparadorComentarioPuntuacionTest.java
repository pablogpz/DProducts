import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * CLASE DE TESTEO de la clase {@link ComparadorComentarioPuntuacion}.
 * <p>
 * Comprueba que puedan ordenarse correctamente colecciones de comentarios por el criterio
 * de puntuación en orden descendente
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComparadorComentarioPuntuacionTest {

    // Fixture de colecciones de prueba
    private static List<Comentario> coleccion;

    // Fixture de clientes de prueba
    Cliente cliente;

    // Fixture de comentarios de prueba
    Comentario c1;
    Comentario c2;
    Comentario c3;


    @Before
    public void setUp() {
        coleccion = new ArrayList<>();
        cliente = new ClienteEstandar("Nombre", 18, "Localidad");
        c1 = new Comentario(cliente, "Comentario 1", 3);
        c2 = new Comentario(cliente, "Comentario 2", 1);
        c3 = new Comentario(cliente, "Comentario 3", 5);

        coleccion.add(c3);
        coleccion.add(c2);
        coleccion.add(c1);
        coleccion.add(c3);
    }

    /**
     * Testeo del método {@link ComparadorComentarioPuntuacion#compare(Comentario, Comentario)}. Comprueba que se ordene
     * correctamente la colección de comentarios por su puntuación descendente
     * <p>
     * [INIT] coleccion {@literal ->} c3(5) - c2(1) - c1(3) - c3(5)
     * [FIN] coleccion {@literal ->} c3(5) - c3(5) - c1(3) - c2(1)
     */
    @Test
    public void compare() {
        coleccion.sort(new ComparadorComentarioPuntuacion());
        assertEquals(c3, coleccion.get(0));
        assertEquals(c3, coleccion.get(1));
        assertEquals(c1, coleccion.get(2));
        assertEquals(c2, coleccion.get(3));
    }
}