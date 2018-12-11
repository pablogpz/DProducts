public class ComportamientoComentarioEstandar implements ComportamientoComentario {

    public ComportamientoComentarioEstandar() {
        // TODO - implement ComportamientoComentarioEstandar.ComportamientoComentarioEstandar
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