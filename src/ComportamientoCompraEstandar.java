import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Clase que implementa el comportamiento de compra de los clientes estándar. La compra consiste en realizar el pedido
 * de los {@link ComportamientoCompraEstandar#NUMERO_PRODUCTOS} productos favoritos más caros como mucho,
 * si no hay suficientes productos para cubrir esa cantidad se realizará el pedido de los productos favoritos
 * disponibles. De cada producto se realiza un pedido de {@link ComportamientoCompraEstandar#UNIDADES_PEDIDO}
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ComportamientoCompraEstandar implements ComportamientoCompra {

    private static final int UNIDADES_PEDIDO = 50;              // Unidades a pedir de cada producto
    private static final int NUMERO_PRODUCTOS = 2;              // Número de productos a incluir en el pedido

    /**
     * Constructor por defecto de la clase
     */
    public ComportamientoCompraEstandar() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean realizarPedido(Cliente cliente) {
        Set<Producto> pedido = prepararPedido(cliente);         // Prepara el pedido de un cliente estándar
        Iterator<Producto> it = pedido.iterator();
        boolean ocurrioError = false;                           // Bandera de éxito del pedido

        while (it.hasNext()) {                                  // Intenta despachar todos los productos del pedido
            Producto producto = it.next();
            if (!ocurrioError) {                                // Comprueba si ya ocurrió un error
                ocurrioError = !cliente.getTienda().venderProducto(producto, cliente, UNIDADES_PEDIDO);
            } else {
                cliente.getTienda().venderProducto(producto, cliente, UNIDADES_PEDIDO);
            }
        }

        return !ocurrioError;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float calcularPrecio(Cliente cliente, Set<Producto> pedido) {
        float precio = 0;                                       // Acumulador del importe total

        for (Producto producto : pedido) {
            if (producto instanceof Descontable)                // Comprueba si hay que aplicar descuento
                precio += ((Descontable) producto).calcularPrecioDescontado() * UNIDADES_PEDIDO;
            else
                precio += producto.getPrecio() * UNIDADES_PEDIDO;
        }

        return precio;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Producto> prepararPedido(Cliente cliente) {
        Set<Producto> prodFavoritos = new TreeSet<>(new ComparadorProductoPrecio());    // Todos los favoritos del cliente
        Set<Producto> pedido = new HashSet<>();                 // Pedido a realizar
        Iterator<Producto> it;                                  // Iterador ordenado por precio descendente
        int numIteraciones = 0;                                 // Contador de productos que se deben añadir

        prodFavoritos.addAll(cliente.getProductosFavoritos());  // Inserta ordenadamente los favoritos por precio
        it = prodFavoritos.iterator();
        while (numIteraciones < NUMERO_PRODUCTOS && it.hasNext()) {                     // Inserta los productos a pedir
            pedido.add(it.next());
            numIteraciones++;
        }

        return pedido;
    }

}