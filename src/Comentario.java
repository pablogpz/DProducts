/**
 * TODO DESCRIPCION
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class Comentario {

    private String autor;
    private String texto;
    private int puntuacion;

    /**
     * Constructor parametrizado de la clase. Genera un comentario a partir de un autor, un cuerpo del comentario y una puntuación. La puntuación debe estar entre 1 y 5 (ambos inclusive)
     *
     * @param autor      Nombre completo del cliente que publica el comentario
     * @param texto      Cuerpo del comentario
     * @param puntuacion Calificación del producto
     */
    public Comentario(String autor, String texto, int puntuacion) {
        // TODO - implement Comentario.Comentario
    }

    /**
     * Método accesor del atributo 'autor'
     *
     * @return Autor del comentario
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     * Método accesor del atributo 'texto'
     *
     * @return Texto del comentario
     */
    public String getTexto() {
        return this.texto;
    }

    /**
     * Método accesor del atributo 'puntuacion'
     *
     * @return Puntuación del comentario
     */
    public int getPuntuacion() {
        return this.puntuacion;
    }

    /**
     * Devuelve una cadena formateada con todos los detalles del comentario
     *
     * @return Cadena con el contenido del comentario formateado
     */
    public String comentarioCompleto() {
        // TODO - implement Comentario.comentarioCompleto
        return null;
    }

}