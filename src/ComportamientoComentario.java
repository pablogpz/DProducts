/**
 * Interfaz que modela el comportamiento de los comentarios en función del tipo de cliente que lo realiza.
 */
public interface ComportamientoComentario {

    /**
     * Realiza el comentario de un cliente sobre un determinado producto.
     *
     * @param producto Producto sobre el que se comenta
     * @param cliente Cliente que realiza el comentario
     */
    boolean comentar(Producto producto, Cliente cliente);

    /**
     * Realiza el cálculo asociado a la puntuación del comentario
     *
     * @param producto Producto que se emplea para conocer dicha puntuación
     */
    int calcularPuntuacion(Producto producto);

    /**
     * Obtiene el texto asociado al producto
     *
     * @param producto Producto que se emplea para conocer dicho texto
     */
    String obtenerTexto(Producto producto);

}