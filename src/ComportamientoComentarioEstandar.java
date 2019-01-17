public class ComportamientoComentarioEstandar implements ComportamientoComentario {

    static final String[] comentariosPredefinidos = {"Bad Product", "Not very good product", "Good product", "Very good product", "Excellent product"};

    public ComportamientoComentarioEstandar() {
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
        return (producto.getNombre().length() % 5) + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String obtenerTexto(Producto producto) {
        return comentariosPredefinidos[(producto.getNombre().length() % 5) + 1];
    }
}