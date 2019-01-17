public class ClienteVIP extends Cliente {

    private ComportamientoCompra comportamientoCompra;
    private ComportamientoComentario comportamientoComentario;

    /**
     * @param nombre
     * @param edad
     * @param localidad
     */
    public ClienteVIP(String nombre, int edad, String localidad) {
        super(nombre, edad, localidad);
        comportamientoCompra = new ComportamientoCompraVIP();
        comportamientoComentario = new ComportamientoComentarioVIP();
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