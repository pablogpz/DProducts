public interface ComportamientoComentario {

    /**
     * @param producto
     * @param cliente
     */
    boolean comentar(Producto producto, Cliente cliente);

    /**
     * @param producto
     */
    int calcularPuntuacion(Producto producto);

    /**
     * @param producto
     */
    String obtenerTexto(Producto producto);

}