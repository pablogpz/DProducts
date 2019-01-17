/**
 * Clase que implementa el comportamiento de comentado de los clientes VIP. La publicación del comentario consiste
 * en calcular los parámetros de un comentario a partir de unos valores predefinidos. El cuerpo del comentario siempre
 * será {@link ComportamientoComentarioVIP#CUERPO_COMENTARIO} y la puntuación
 * {@link ComportamientoComentarioVIP#PUNTUACION}
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComportamientoComentarioVIP implements ComportamientoComentario {

    public static final String CUERPO_COMENTARIO = "I really like this product";  // Texto por defecto
    public static final int PUNTUACION = 4;                                       // Puntuación por defecto

    /**
     * Constructor por defecto de la clase
     */
    public ComportamientoComentarioVIP() {
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
        return PUNTUACION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String obtenerTexto(Producto producto) {
        return CUERPO_COMENTARIO;
    }
}