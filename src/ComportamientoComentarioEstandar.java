/**
 * Clase que implementa el comportamiento de comentado de los clientes estándar. La publicación del comentario consiste
 * en calcular los parámetros de un comentario a partir de la longitud del nombre del producto, así el texto correspondiente
 * se determinará a partir de la entrada indexada del vector de cadenas predefinidas según el índice
 * {@code (producto.getNombre().length() % 5) + 1}. La puntuación coincide con el índice calculado
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComportamientoComentarioEstandar implements ComportamientoComentario {

    private static final String[] comentariosPredefinidos = {"Bad Product",
            "Not very good product",
            "Good product",
            "Very good product",
            "Excellent product"};

    /**
     * Constructor por defecto de la clase
     */
    public ComportamientoComentarioEstandar() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean comentar(Producto producto, Cliente cliente) {
        boolean esValido = false;                                   // Bandera de éxito del proceso de comentado
        int puntuacion = calcularPuntuacion(producto);              // Puntuación calculada para el comentario

        try {
            // Intenta crear el comentario calculado
            Comentario comentario = new Comentario(cliente, obtenerTexto(producto), puntuacion);

            // Intenta publicar el comentario, si el producto es comentable
            esValido = ((ProductoComentable) producto).comentar(comentario);
            System.out.println("Se ha publicado un comentario\n\t" + comentario);

            if (esValido) {                                         // Comprueba que se haya podido publicar
                // Comprueba si el producto además es gustable
                Gustable productoGustable = (Gustable) producto;

                if (puntuacion <= 2)                                // Determina si gustar o no gustar el producto
                    productoGustable.unlike();
                else if (puntuacion >= 4)
                    productoGustable.like();

            }
        } catch (IllegalArgumentException e) {
            System.out.println("El comentario está mal formado");
        } catch (ClassCastException e) {
            if (esValido)
                System.out.print("El producto " + producto.getNombre() + " es comentable pero no gustable");
            else
                System.out.println("No es posible comentar este producto. Clase (" + producto.getClass() +
                        ") No es comentable ");
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