import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ComportamientoCompraEstandar implements ComportamientoCompra {

    static final int CANTIDAD_ESTANDAR = 50;
    static final int NUMERO_PRODUCTOS_ESTANDAR = 2;

    public ComportamientoCompraEstandar() {
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
            realizado = cliente.getTienda().venderProducto(producto, cliente, CANTIDAD_ESTANDAR);
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
        int numIteraciones = 0;
        while (numIteraciones < NUMERO_PRODUCTOS_ESTANDAR && it.hasNext()) {
            Producto producto = (Producto) it.next();
            pedido.add(producto);
            numIteraciones++;
        }

        return pedido;
    }
}