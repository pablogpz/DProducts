public class ClienteEstandar extends Cliente {

    private ComportamientoCompra comportamientoCompra;
    private ComportamientoComentario comportamientoComentario;

    /**
     * @param nombre
     * @param edad
     * @param localidad
     */

    public ClienteEstandar(String nombre, int edad, String localidad) {
        super(nombre, edad, localidad);
        comportamientoCompra = new ComportamientoCompraEstandar();
        comportamientoComentario = new ComportamientoComentarioEstandar();
    }

    @Override
    public boolean realizarPedido() {
        return comportamientoCompra.realizarPedido(this, comportamientoCompra.prepararPedido(this));
    }

    @Override
    public boolean comentarProducto(String alias) {
        return comportamientoComentario.comentar(this.getProductosFavoritos().get(alias), this);
    }

}