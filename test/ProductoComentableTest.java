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
    private static ClienteEstandar autor2;
    private static ClienteEstandar autor3;

    // Fixture de comentarios de prueba
    Comentario comentario;
    Comentario comentario2;
    Comentario comentario3;
    Comentario comentarioRepetido;


    @Before
    public void setUp() {
        productoComentable = new ProductoOcio("Nombre", 30, 1, 25, FABRICANTES.ACER,
                PRIORIDAD_PRODUCTO.MEDIA);
        autor = new ClienteEstandar("Nombre", 18, "Localidad");
        autor2 = new ClienteEstandar("Segundo nombre", 18, "Localidad");
        autor3 = new ClienteEstandar("Tercer nombre", 18, "Localidad");
        comentario = new Comentario(autor, "Texto", 5);
        comentario2 = new Comentario(autor2, "Texto", 1);
        comentario3 = new Comentario(autor3, "Texto", 3);
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
        final String cadena = productoComentable.toString();
        assertEquals("PRODUCTO\tNombre-ACER\n" +
                "\tCantidad en stock : 30\n" +
                "\tCantidad en stock mínima : 25\n" +
                "\tPrioridad de reabastecimiento : MEDIA\n" +
                "\tComentarios:\n" +
                "\n" +
                "\tDescuento aplicable : 20.0%", cadena.substring(0, 22) + cadena.substring(45));
    }

    /**
     * Testeo de la ordenación de comentarios por puntuación. Comprueba que al inserar varios comentarios
     * después se muestren en orden
     * <p>
     * [INIT] productoComentable.comentarios -> c1(5) - c2(1) - c3(3)
     * [FIN] roductoComentable.comentarios -> c1(5) - c3(3) - c2(1)
     */
    @Test
    public void ordenacion() {
        // TODO - Revisar
        productoComentable.comentar(comentario);
        productoComentable.comentar(comentario2);
        productoComentable.comentar(comentario3);

        assertEquals("\n===============================================================\n" +
                "Autor : CLIENTE\tNombre\n" +
                "\tIdentificador : X5P1W\n" +
                "\tEdad : 18\n" +
                "\tLocalidad : Localidad\n" +
                "Calificación *****\n" +
                "\tReseña :\n" +
                "Texto\n" +
                "\n" +
                "===============================================================\n" +
                "Autor : CLIENTE\tTercer nombre\n" +
                "\tIdentificador : X5P1T\n" +
                "\tEdad : 18\n" +
                "\tLocalidad : Localidad\n" +
                "Calificación ***\n" +
                "\tReseña :\n" +
                "Texto\n" +
                "\n" +
                "===============================================================\n" +
                "Autor : CLIENTE\tSegundo nombre\n" +
                "\tIdentificador : X5P1N\n" +
                "\tEdad : 18\n" +
                "\tLocalidad : Localidad\n" +
                "Calificación *\n" +
                "\tReseña :\n" +
                "Texto\n", productoComentable.recuperarComentarios());
    }

}