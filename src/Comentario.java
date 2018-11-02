/**
 * Clase que da soporte a comentarios sobre productos. Los comentarios contienen el nombre completo de su autor,
 * una breve reseña sobre el producto y una calificiación en el rango [1,5]
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class Comentario {

    private String autor;                                                   // Nombre completo del cliente que publicó el comentario
    private String texto;                                                   // Cuerpo del comentario
    private int puntuacion;                                                 // Calificación que le dio el usuario. Es un valor en el rango [1,5]

    /**
     * Constructor parametrizado de la clase. Genera un comentario a partir de un autor, un cuerpo del comentario y una puntuación.
     * La puntuación debe estar entre 1 y 5 (ambos inclusive)
     *
     * @param autor      Nombre completo del cliente que publica el comentario
     * @param texto      Cuerpo del comentario
     * @param puntuacion Calificación del producto. Es un valor en el rango [1,5]
     */
    public Comentario(String autor, String texto, int puntuacion) {
        this.autor = autor;
        this.texto = texto;
        this.puntuacion = puntuacion;
    }

    /**
     * Método accesor del atributo 'autor'
     *
     * @return Autor del comentario
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Método accesor del atributo 'texto'
     *
     * @return Texto del comentario
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Método accesor del atributo 'puntuacion'
     *
     * @return Puntuación del comentario
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Devuelve una cadena formateada con todos los detalles del comentario
     *
     * @return Cadena con el contenido del comentario formateado
     */
    public String comentarioCompleto() {
        return "Autor : " + autor + "\n" +
                "Comentario :\n" + texto +
                "\nCalificación : " + "*****".substring(0, puntuacion);
    }

}