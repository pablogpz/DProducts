import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public abstract class ProductoComentable extends Producto {

    private List<Comentario> comentarios;                               // Colección de comentarios que los clientes han publicado sobre el producto

    public ProductoComentable(String nombre, int cantidad, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad,
                              GregorianCalendar fechaLanzamiento, boolean esReacondicionado) {
        super(nombre, cantidad, stockMinimo, fabricante, prioridad, fechaLanzamiento, esReacondicionado);
        comentarios = new ArrayList<>();
        // TODO - implement ProductoComentable.ProductoComentable
    }

    /**
     * Añade un comentario al producto. Solo se permite un comentario por cliente y con una calificación entre 1 y 5 (ambos inclusive)
     *
     * @param comentario Objeto de la clase Comentario que representa el comentario a añadir a la colección de comentarios
     * @return Booleano indicando si se ha podido publicar el comentario. Devuelve falso si el autor ya ha publicado un comentario
     */
    public boolean comentar(Comentario comentario) {
        boolean autorRepetido = false;                                  // Bandera para comprobar la duplicidad de autores en comentarios

        Iterator<Comentario> it = comentarios.iterator();
        while (it.hasNext() && !autorRepetido) {
            if (it.next().getAutor().getIdentificador().equals(
                    comentario.getAutor().getIdentificador()))          // Comprueba que no exista ya un comentario con el mismo autor
                autorRepetido = true;
        }

        if (!autorRepetido)                                             // Si no ha habido coincidencia se publica el comentario
            comentarios.add(comentario);

        return !autorRepetido;
    }

    /**
     * Devuelve los comentarios publicados sobre el producto
     *
     * @return Cadena formateada con todos los comentarios sobre el producto
     */
    private String recuperarComentarios() {
        String decorador = "\n===============================================================\n";
        String comentarios = "";

        // Adjunta los detalles de todos los comentarios publicados sobre el producto
        for (Comentario comentario : this.comentarios)
            comentarios += decorador + comentario.toString();

        return comentarios;
    }

    public String toString() {
        // TODO - implement ProductoComentable.toString
        return null;
    }

    /**
     * @param obj
     */
    public boolean equals(Object obj) {
        // TODO - implement ProductoComentable.equals
        return false;
    }

    public int hashCode() {
        // TODO - implement ProductoComentable.hashCode
        return 0;
    }
}