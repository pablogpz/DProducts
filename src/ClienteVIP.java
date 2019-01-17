/**
 * Clase que implementa ek subtipo de cliente VIP. Implementa el comportamiento de compra y comentado de este subtipo
 * de clientes. Como sigue el patrón Strategy, se puede cambiar sus comportamientos en tiempo de ejecución mediante los
 * métodos mutadores al igual que consultar cuál es el actual
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class ClienteVIP extends Cliente {

    private ComportamientoCompra comportamientoCompra;                      // Comportamiento de compra
    private ComportamientoComentario comportamientoComentario;              // Comportamiento de comentado

    /**
     * Constuctor parametrizado de la clase. Inicializa los distintos comportamientos de compra y comentado que
     * tomará inicialmente el cliente VIP
     *
     * @param nombre    Nombre completo del cliente. No puede ser vacío
     * @param edad      Edad actual del cliente. Debe ser un natural
     * @param localidad Nombre de la localidad de residencia del cliente. No puede ser vacío
     */
    public ClienteVIP(String nombre, int edad, String localidad) {
        super(nombre, edad, localidad);
        comportamientoCompra = new ComportamientoCompraVIP();
        comportamientoComentario = new ComportamientoComentarioVIP();
    }

    /**
     * Método accesor del atributo {@link ClienteVIP#comportamientoCompra}
     *
     * @return Comportamiento de compra actual
     */
    public ComportamientoCompra getComportamientoCompra() {
        return comportamientoCompra;
    }

    /**
     * Método accesor del atributo {@link ClienteVIP#comportamientoComentario}
     *
     * @return Comportamiento de comentado actual
     */
    public ComportamientoComentario getComportamientoComentario() {
        return comportamientoComentario;
    }

    /**
     * Método mutador del atributo {@link ClienteVIP#comportamientoCompra}
     *
     * @param comportamientoCompra Nuevo comportamiento de compra
     */
    public void setComportamientoCompra(ComportamientoCompra comportamientoCompra) {
        this.comportamientoCompra = comportamientoCompra;
    }

    /**
     * Método mutador del atributo {@link ClienteVIP#comportamientoComentario}
     *
     * @param comportamientoComentario Nuevo comportamiento de comentado
     */
    public void setComportamientoComentario(ComportamientoComentario comportamientoComentario) {
        this.comportamientoComentario = comportamientoComentario;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean realizarPedido() {
        return comportamientoCompra.realizarPedido(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean comentarProducto(String alias) {
        return comportamientoComentario.comentar(recuperarFavorito(alias), this);
    }

}