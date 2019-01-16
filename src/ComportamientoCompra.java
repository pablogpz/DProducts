import java.util.Set;

public interface ComportamientoCompra {

    /**
     * Realiza el pedido solicitado por el cliente.
     *
     * @return
     */
    boolean realizarPedido();

    /**
     * Devuelve el importe del pedido a realizar, considerando la variacion de descuentos.
     *
     * @param cliente El cliente que realiza el pedido
     * @return Precio del pedido
     */
    float calcularPrecio(Cliente cliente);

    /**
     * Devuelve el conjunto de productos que se seleccionan para el pedido.
     *
     * @param cliente El cliente que realiza el pedido
     * @return El conjunto de productos del pedido
     */
    Set<Producto> prepararPedido(Cliente cliente);

}