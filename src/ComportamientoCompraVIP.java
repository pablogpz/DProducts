public class ComportamientoCompraVIP implements ComportamientoCompra {

    public ComportamientoCompraVIP() {
        // TODO - implement ComportamientoCompraVIP.ComportamientoCompraVIP
    }

    @Override
    public boolean realizarPedido() {
        return false;
    }

    @Override
    public float calcularPrecio() {
        return 0;
    }

    @Override
    public boolean prepararPedido() {
        return false;
    }
}