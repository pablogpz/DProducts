import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * TODO DESCRIPCION
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class Producto {

    private String nombre;                                              // Nombre comercial del producto
    private Identificador identificador;                                // Identificador único del producto
    private int cantidad;                                               // Cantidad actual en stock del producto
    private int stockMinimo;                                            // Cantidad mínima que siempre debe existir en stock
    private PRIORIDAD_PRODUCTO prioridad;                               // Valor directamente relacionado con la cantidad con la que se reabastece el producto
    private FABRICANTES fabricante;                                     // Fabricante del producto
    private Calendar fechaLanzamiento;                                  // Fecha de lanzamiento al mercado del producto
    private boolean esReacondicionado;                                  // Estado del producto. Si es verdadero significa que es de segunda mano, sino es nuevo
    private List<Comentario> comentarios;                               // Colección de comentarios que los clientes han publicado sobre el producto

    /**
     * Constructor parametrizado de la clase. Genera un producto a partir de su nombre, fabricante, prioridad de reabastecimiento,
     * fecha de lanzamiento y estado actual
     *
     * @param nombre            Nombre del producto
     * @param cantidad          Cantidad en stock del producto
     * @param stockMinimo       Cantidad mínima en stock que siempre debe existir del producto
     * @param fabricante        Valor del tipo enumerado de FABRICANTES
     * @param prioridad         Valor del tipo enumerado PRIORIDAD_PRODUCTO. Representa la demanda del producto
     *                          y se tiene en cuenta al reabastecerlo
     * @param fechaLanzamiento  Fecha de lanzamiento reprensentada por el tipo Calendar asociado
     * @param esReacondicionado Estado actual del producto. Representa si es de segunda mano o nuevo
     */
    public Producto(String nombre, int cantidad, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad,
                    Calendar fechaLanzamiento, boolean esReacondicionado) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.stockMinimo = stockMinimo;
        this.fabricante = fabricante;
        this.prioridad = prioridad;
        this.fechaLanzamiento = fechaLanzamiento;
        this.esReacondicionado = esReacondicionado;

        identificador = GeneradorIdentificador.recuperarInstancia().generarIdentificador();
        comentarios = new ArrayList<>();
    }

    /**
     * Método accesor del atributo 'nombre'
     *
     * @return Nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método accesor del atributo 'identificador'
     *
     * @return Identificador del producto
     */
    public Identificador getIdentificador() {
        return identificador;
    }

    /**
     * Método accesor del atributo 'cantidad'
     *
     * @return Cantidad actual en stock del producto
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Método accesor del atributo 'stockMinimo'
     *
     * @return Cantidad en stock mínima del producto
     */
    public int getStockMinimo() {
        return stockMinimo;
    }

    /**
     * Método accesor del atributo 'prioridad'
     *
     * @return Factor de prioridad con el que debe restablecerse el stock del producto
     */
    public PRIORIDAD_PRODUCTO getPrioridad() {
        return prioridad;
    }

    /**
     * Método accesor del atributo 'fabricante'
     *
     * @return Fabricante del producto
     */
    public FABRICANTES getFrabricante() {
        return fabricante;
    }

    /**
     * Método accesor del atributo 'fechaLanzamiento'
     *
     * @return Objeto Calendar que representa la fecha de lanzamiento del producto
     */
    public Calendar getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    /**
     * Método accesor del atributo 'esReacondicionado'
     *
     * @return Booleano que representa si el producto es de segunda mano o no
     */
    public boolean getEsReacondicionado() {
        return esReacondicionado;
    }

    /**
     * Decrementa la cantidad en stock actual por el número de unidades del pedido
     *
     * @param cantidad Número en el que decrementar el stock actual del producto. Solo se admiten valores positivos mayores que 0
     * @return Booleano indicando si se ha permitido o no el decremento del stock del producto
     */
    public boolean pedir(int cantidad) {
        // TODO - implement Producto.pedir
        return false;
    }

    /**
     * Repone la cantidad en stock del producto actual según su prioridad de reabastecimiento. Solo se permite el reabastecimiento
     * si su cantidad en stock actual está estrictamente por debajo del la cantidad en stock mínima
     *
     * @return Booleano indicando si se ha permitido o no el reabastecimiento de stock del producto
     */
    private boolean reponerStock() {
        // TODO - implement Producto.reponerStock
        return false;
    }

    /**
     * Añade un comentario al producto. Solo se permite un comentario por cliente y con una calificación entre 1 y 5 (ambos inclusive)
     *
     * @param comentario Objeto de la clase Comentario que representa el comentario a añadir a la colección de comentarios
     * @return Booleano indicando si el comentario fue publicado. Devuelve falso en caso de que no sea válido y no se publicará
     */
    public boolean comentar(Comentario comentario) {
        // TODO - implement Producto.comentar
        return false;
    }

    /**
     * Devuelve una cadena formateada con los detalles más relevantes del producto (nombre, identificador, cantidad, fabricante,
     * fecha de lanzamiento, estado y la lista de comentarios)
     *
     * @return Cadena formatrada de información del producto
     */
    public String detalles() {
        // TODO - implement Producto.detalles
        return null;
    }

    /**
     * Devuelve todos los detalles del producto (los detalles básicos junto al stock mínimo y su prioridad de reabastecimiento
     *
     * @return Cadena con todos los detalles del producto
     */
    public String detallesCompletos() {
        // TODO - implement Producto.detallesCompletos
        return null;
    }

}