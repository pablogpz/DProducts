import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * TODO DESCRIPCION
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class Producto {

    // Valores de reabasteciemiento de productos según su prioridad
    private static final int REABASTECIMIENTO_PRIORIDAD_BAJA = 25;
    private static final int REABASTECIMIENTO_PRIORIDAD_MEDIA = 75;
    private static final int REABASTECIMIENTO_PRIORIDAD_ALTA = 150;
    private static final String FORMATO_FECHA = "YYYY/MM/dd";           // Formato en el que mostrar las fechas de lanzamiento

    private String nombre;                                              // Nombre comercial del producto
    private Identificador identificador;                                // Identificador único del producto
    private int cantidad;                                               // Cantidad actual en stock del producto
    private int stockMinimo;                                            // Cantidad mínima que siempre debe existir en stock
    private PRIORIDAD_PRODUCTO prioridad;                               // Valor directamente relacionado con la cantidad con la que se reabastece el producto
    private FABRICANTES fabricante;                                     // Fabricante del producto
    private GregorianCalendar fechaLanzamiento;                         // Fecha de lanzamiento al mercado del producto
    private boolean esReacondicionado;                                  // Estado del producto. Si es verdadero significa que es de segunda mano, sino es nuevo
    private List<Comentario> comentarios;                               // Colección de comentarios que los clientes han publicado sobre el producto

    /**
     * Constructor parametrizado de la clase. Genera un producto a partir de su nombre, camtidad en stock, cantidad mínima en stock,
     * fabricante, prioridad de reabastecimiento, fecha de lanzamiento y estado actual
     *
     * @param nombre            Nombre del producto
     * @param cantidad          Cantidad en stock del producto
     * @param stockMinimo       Cantidad mínima en stock que siempre debe existir del producto
     * @param fabricante        Valor del tipo enumerado de FABRICANTES
     * @param prioridad         Valor del tipo enumerado PRIORIDAD_PRODUCTO. Representa la demanda del producto
     *                          y se tiene en cuenta al reabastecerlo
     * @param fechaLanzamiento  Fecha de lanzamiento reprensentada por el tipo GregorianCalendar asociado
     * @param esReacondicionado Estado actual del producto. Representa si es de segunda mano o nuevo
     */
    public Producto(String nombre, int cantidad, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad,
                    GregorianCalendar fechaLanzamiento, boolean esReacondicionado) {
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
    public GregorianCalendar getFechaLanzamiento() {
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
        // Comprueba que la cantidad sea positiva mayor que 0 y que el pedido no supere el stock actual
        if (cantidad <= 0 || this.cantidad - cantidad < 0) {
            this.cantidad -= cantidad;
            if (cantidad < stockMinimo)                                 // Comprueba si hay que reponer el stock
                reponerStock();
        } else {
            return false;                                               // No se pudo servir el pedido
        }

        return true;
    }

    /**
     * Repone la cantidad en stock del producto actual según su prioridad de reabastecimiento. Solo se permite el reabastecimiento
     * si su cantidad en stock actual está estrictamente por debajo del la cantidad en stock mínima
     */
    private void reponerStock() {
        switch (prioridad) {
            case BAJA:
                cantidad += REABASTECIMIENTO_PRIORIDAD_BAJA;
                break;
            case MEDIA:
                cantidad += REABASTECIMIENTO_PRIORIDAD_MEDIA;
                break;
            case ALTA:
                cantidad += REABASTECIMIENTO_PRIORIDAD_ALTA;
        }
    }

    /**
     * Añade un comentario al producto. Solo se permite un comentario por cliente y con una calificación entre 1 y 5 (ambos inclusive)
     *
     * @param comentario Objeto de la clase Comentario que representa el comentario a añadir a la colección de comentarios
     */
    public void comentar(Comentario comentario) {
        comentarios.add(comentario);
    }

    /**
     * Devuelve una cadena formateada con todos los detalles del producto
     *
     * @return Cadena formatrada de información del producto
     */
    public String detalles() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATO_FECHA);
        String estado = esReacondicionado ? "Reacondicionado" : "Nuevo";

        String detalles = "PRODUCTO\t" + nombre + "-" + fabricante.toString() +
                "\n\tIdentificador : " + identificador.valorDe() + "\n\tCantidad en stock : " + cantidad +
                "\n\tCantidad en stock mínima : " + stockMinimo + "\n\tPrioridad de reabastecimiento : " + prioridad.toString() +
                "\n\tFecha de lanzamiento : " + simpleDateFormat.format(fechaLanzamiento.getTime()) + "\n\tEstado : " +
                estado + "\n\tComentarios : \n";

        // Adjunta los detalles de todos los comentarios publicados sobre el producto
        for (Comentario comentario : comentarios) {
            detalles += "\n===============================================================================\n";
            detalles += comentario.comentarioCompleto();
        }

        return detalles;
    }

}