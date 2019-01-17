import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ComportamientoCompraVIP implements ComportamientoCompra {

    public ComportamientoCompraVIP() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean realizarPedido(Cliente cliente, Set<Producto> pedido) {

        boolean realizado = true;
        Iterator<Producto> it = pedido.iterator();

        while (it.hasNext() && realizado) {
            Producto producto = it.next();
            realizado = cliente.getTienda().venderColeccionProductos(pedido, cliente);
        }

        return realizado;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float calcularPrecio(Cliente cliente, Set<Producto> pedido) {

        float precio = 0;
        Iterator<Producto> it = pedido.iterator();
        while (it.hasNext()) {
            Producto producto = it.next();
            precio = precio + ((Descontable) producto).calcularPrecioDescontado();
        }

        return precio;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Producto> prepararPedido(Cliente cliente) {

        Set<Producto> todos = new HashSet<>(cliente.getProductosFavoritos().values());
        Set<Producto> pedido = new HashSet<>();

        Iterator it = todos.iterator();
        while (it.hasNext()) {
            Producto producto = (Producto) it.next();
            pedido.add(producto);
        }

        return pedido;
    }
}