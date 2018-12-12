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
        // TODO - implement ClienteVIP.ClienteVIP
    }

    @Override
    public boolean realizarPedido() {
        // TODO - implement
        return false;
    }

    @Override
    public boolean comentarProducto(String alias) {
        // TODO - implement
        return false;
    }

}