import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CLASE DE TESTEO de la clase ProductoComentable.
 * <p>
 * Realiza las pruebas de todos los métodos públicos y protegidos de la clase base utilizando la clase
 * ProductoOcio como subclase para probar los métodos de la clase base que no son sobreescritos por otras subclases
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ProductoComentableTest {

    // Fixture de productos comentables de prueba
    private static ProductoComentable productoComentable;

    // Fixture de clientes de prueba
    private static ClienteEstandar autor;

    // Fixture de comentarios de prueba
    Comentario comentario;
    Comentario comentarioRepetido;


    @Before
    public void setUp() {
        productoComentable = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        autor = new ClienteEstandar("Nombre", 18, "Localidad");
        comentario = new Comentario(autor, "Texto", 5);
        comentarioRepetido = new Comentario(autor, "Texto", 5);
    }

    /**
     * Testeo del método 'comentar()'. Comprueba que solo se puedan insertar comentarios válidos y una sola
     * vez por autor
     */
    @Test
    public void comentar() {
        assertTrue(productoComentable.comentar(comentario));
        assertFalse(productoComentable.comentar(comentario));
        assertFalse(productoComentable.comentar(comentarioRepetido));
    }

    /**
     * Testeo del método 'recuperarComentarios()'. Comprueba que se formateen correctamente los comentarios
     * almacenados
     */
    @Test
    public void recuperarComentarios() {
        productoComentable.comentar(comentario);
        assertEquals("\n===============================================================\n" +
                "Autor : CLIENTE\tNombre\n" +
                "\tIdentificador : X5P1U\n" +
                "\tEdad : 18\n" +
                "\tLocalidad : Localidad\n" +
                "Calificación *****\n" +
                "\tReseña :\n" +
                "Texto\n", productoComentable.recuperarComentarios());
    }

}