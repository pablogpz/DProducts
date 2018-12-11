public class ComportamientoCompraEstandar implements ComportamientoCompra {

    public ComportamientoCompraEstandar() {
        // TODO - implement ComportamientoCompraEstandar.ComportamientoCompraEstandar
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