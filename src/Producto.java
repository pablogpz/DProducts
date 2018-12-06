import Identificadores.GeneradorIdentificador;
import Identificadores.Identificador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que modela el comportamiento de un producto. Permite consultar sus detalles, añadir comentarios y hacer un pedido
 * del un producto en particular. Cada producto es asignado con una clave única en el ciclo de vida del programa que establece
 * una relación biunívoca entre su identididad y su identificador
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class Producto {

    // Valores de reabasteciemiento de productos según su prioridad
    public static final int REABASTECIMIENTO_PRIORIDAD_BAJA = 25;
    public static final int REABASTECIMIENTO_PRIORIDAD_MEDIA = 75;
    public static final int REABASTECIMIENTO_PRIORIDAD_ALTA = 150;
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
     * @throws IllegalArgumentException Si la cantidad es un entero negativo o 0 o si el stock mínimo es un entero negativo
     */
    public Producto(String nombre, int cantidad, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad,
                    GregorianCalendar fechaLanzamiento, boolean esReacondicionado) throws IllegalArgumentException {
        if (cantidad <= 0 || stockMinimo < 0) throw new
                IllegalArgumentException("Parámetros inválidos. Compruebe que 'cantidad' y 'stockMinimo' sean valores positivos" +
                " y mayores que 0 (stockMinimo sí puede ser 0)");

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
    public boolean entregar(int cantidad) {
        // Comprueba que la cantidad sea positiva mayor que 0 y que el pedido no supere el stock actual
        if (haySuficienteStock(cantidad))
            this.cantidad -= cantidad;
        else
            return false;                                               // No se pudo servir el pedido

        return true;
    }

    /**
     * Método mutador del atributo 'cantidad'. Incrementa la cantidad del producto solo si está por
     * debajo de su stock mínimo
     *
     * @param cantidad Incremento del stock del producto
     * @return Booleano indicando si la operación se ha realizado
     */
    public boolean incCantidad(int cantidad) {
        if (getCantidad() < getStockMinimo())
            this.cantidad += cantidad;
        else
            return false;

        return true;
    }

    /**
     * Añade un comentario al producto. Solo se permite un comentario por cliente y con una calificación entre 1 y 5 (ambos inclusive)
     *
     * @param comentario Objeto de la clase Comentario que representa el comentario a añadir a la colección de comentarios
     * @return Booleano indicando si se ha podido publicar el comentario. Devuelve falso si el autor ya ha publicado un comentario
     */
    public boolean comentar(Comentario comentario) {
        boolean autorRepetido = false;                                  // Bandera para comprobar la duplicidad de autores en comentarios

        Iterator<Comentario> it = comentarios.iterator();
        while (it.hasNext() && !autorRepetido) {
            if (it.next().getAutor().getIdentificador().equals(
                    comentario.getAutor().getIdentificador()))          // Comprueba que no exista ya un comentario con el mismo autor
                autorRepetido = true;
        }

        if (!autorRepetido)                                             // Si no ha habido coincidencia se publica el comentario
            comentarios.add(comentario);

        return !autorRepetido;
    }

    /**
     * Comprueba si hay suficiente stock como para cubrir un pedido de cierta cantidad
     *
     * @param cantidad Cantidad válida a cubrir por el pedido
     * @return Booleano indicando si hay suficiente stock
     */
    public boolean haySuficienteStock(int cantidad) {
        return cantidad > 0 && this.cantidad - cantidad >= 0;
    }

    /**
     * Devuelve una cadena formateada con todos los detalles del producto
     *
     * @return Cadena formatrada de información del producto
     */
    public String detalles() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATO_FECHA);
        String estado = getEsReacondicionado() ? "Reacondicionado" : "Nuevo";

        String detalles = "PRODUCTO\t" + getNombre() + "-" + getFrabricante().toString() +
                "\n\tIdentificador : " + getIdentificador().toString() + "\n\tCantidad en stock : " + getCantidad() +
                "\n\tCantidad en stock mínima : " + getStockMinimo() + "\n\tPrioridad de reabastecimiento : " +
                getPrioridad().toString() + "\n\tFecha de lanzamiento : " + simpleDateFormat.format(getFechaLanzamiento().getTime()) +
                "\n\tEstado : " + estado + "\n\tComentarios : \n";

        return detalles + recuperarComentarios();
    }

    /**
     * Devuelve los comentarios publicados sobre el producto
     *
     * @return Cadena formateada con todos los comentarios sobre el producto
     */
    private String recuperarComentarios() {
        String decorador = "\n===============================================================\n";
        String comentarios = "";

        // Adjunta los detalles de todos los comentarios publicados sobre el producto
        for (Comentario comentario : this.comentarios)
            comentarios += decorador + comentario.comentarioCompleto();

        return comentarios;
    }

}