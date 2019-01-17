public class ComportamientoComentarioVIP implements ComportamientoComentario {

    static final String COMENTARIO_VIP = "I really like this product";
    static final int PUNTUACION_VIP = 4;

    public ComportamientoComentarioVIP() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean comentar(Producto producto, Cliente cliente) {

        boolean esValido = false;
        int puntuacion = calcularPuntuacion(producto);

        Comentario comentario = new Comentario(cliente, obtenerTexto(producto), puntuacion);

        try {

            esValido = ((ProductoComentable) producto).comentar(comentario);

            if (esValido) {

                Gustable productoGustable = (Gustable) producto;

                if (puntuacion <= 2) {
                    productoGustable.unlike();
                } else if (puntuacion >= 4) {
                    productoGustable.like();

                }
            }

        } catch (ClassCastException e) {
            if (esValido) {
                System.out.print("El producto " + producto.getNombre() + " es comentable pero no gustable");
            } else {
                System.out.println("El producto " + producto.getNombre() + " no es comentable");
            }
        }

        return esValido;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calcularPuntuacion(Producto producto) {
        return PUNTUACION_VIP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String obtenerTexto(Producto producto) {
        return COMENTARIO_VIP;
    }
}