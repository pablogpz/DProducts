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
    public boolean realizarPedido(Cliente cliente, Set<Producto> prodRepuestos) {
        Set<Producto> pedido = prepararPedido(cliente);         // Pedido

        prodRepuestos = cliente.getTienda().venderColeccionProductos(pedido, cliente);
        if (prodRepuestos != null)
            cliente.getTienda().registrarGastoCliente(cliente, calcularPrecio(pedido));

        return !(prodRepuestos == null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float calcularPrecio(Set<Producto> pedido) {
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