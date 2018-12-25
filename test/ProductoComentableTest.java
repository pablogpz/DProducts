import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * CLASE DE TESTEO de la clase {@see ProductoComentable}.
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
     * Testeo del método {@link ProductoComentable#comentar(Comentario)}. Comprueba que solo se puedan insertar
     * comentarios válidos y una sola vez por autor
     */
    @Test
    public void comentar() {
        assertTrue(productoComentable.comentar(comentario));
        assertFalse(productoComentable.comentar(comentario));
        assertFalse(productoComentable.comentar(comentarioRepetido));
    }

    /**
     * Testeo del método {@link ProductoComentable#recuperarComentarios()}. Comprueba que se formateen correctamente
     * los comentarios almacenados (TESTEO VISUAL)
     */
    @Test
    public void recuperarComentarios() {
        productoComentable.recuperarComentarios();
    }

    /**
     * Testeo de la ordenación de comentarios por puntuación. Comprueba que al inserar varios comentarios
     * después se muestren en orden (COMRPOBACIÓN VISUAL. YA FUE COMPROBADO EN LOS COMPARADORES)
     * <p>
     * [INIT] productoComentable.comentarios {@literal ->} c1(5) - c2(1) - c3(3)
     * [FIN] roductoComentable.comentarios {@literal ->} c1(5) - c3(3) - c2(1)
     */
    @Test
    public void ordenacion() {
        productoComentable.comentar(comentario);
        productoComentable.comentar(comentario2);
        productoComentable.comentar(comentario3);

        productoComentable.recuperarComentarios();
    }

}