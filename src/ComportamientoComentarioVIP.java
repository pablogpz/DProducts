public class ComportamientoComentarioVIP implements ComportamientoComentario {

    public ComportamientoComentarioVIP() {
        // TODO - implement ComportamientoComentarioVIP.ComportamientoComentarioVIP
    }

    @Override
    public boolean comentar(Producto producto, Cliente cliente) {
        return false;
    }

    @Override
    public int calcularPuntuacion(Producto producto) {
        return 0;
    }

    @Override
    public String obtenerTexto(Producto producto) {
        return null;
    }
}