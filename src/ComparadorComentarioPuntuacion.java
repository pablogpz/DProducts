import java.util.Comparator;

/**
 * Clase que implementa la interfaz Comparator para ordenar comentarios por su puntuación en orden
 * descendente
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComparadorComentarioPuntuacion implements Comparator<Comentario> {

    /**
     * Constructor por defecto de la clase. Es vacío
     */
    public ComparadorComentarioPuntuacion() {
    }

    /**
     * Compara dos objetos comentario según su puntuación
     *
     * @param c1 Primer comentario
     * @param c2 Segundo comentario
     * @return 1 sii c1 {@literal <} c2
     * <p>
     * 0 sii c1 == c2
     * <p>
     * -1 sii c1 {@literal >} c2
     */
    public int compare(Comentario c1, Comentario c2) {
        if (c1.getPuntuacion() < c2.getPuntuacion())
            return 1;
        else if (c1.getPuntuacion() == c2.getPuntuacion())
            return 0;
        else
            return -1;
    }

}