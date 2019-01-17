import java.util.HashSet;
import java.util.Set;

/**
 * Clase que implementa el comportamiento de compra de los clientes estándar. La compra consiste en realizar el pedido
 * de todos los productos favoritos. De cada producto se realiza un pedido de 1 unidad
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComportamientoCompraVIP implements ComportamientoCompra {

    /**
     * Constructor por defecto de la clase
     */
    public ComportamientoCompraVIP() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean realizarPedido(Cliente cliente) {
        return cliente.getTienda().venderColeccionProductos(prepararPedido(cliente), cliente);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float calcularPrecio(Cliente cliente, Set<Producto> pedido) {
        float precio = 0;                                       // Acumulador del importe total (1 ud. de cada producto)

        for (Producto producto : pedido) {
            if (producto instanceof Descontable)                // Comprueba si hay que aplicar descuento
                precio += ((Descontable) producto).calcularPrecioDescontado();
            else
                precio += producto.getPrecio();
        }

        return precio;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Producto> prepararPedido(Cliente cliente) {
        return new HashSet<>(cliente.getProductosFavoritos());
    }
}