import Identificadores.GeneradorIdentificador;
import Identificadores.Identificador;

/**
 * Clase que modela el comportamiento base de un producto. Permite consultar sus detalles base y hacer un pedido
 * del un producto en particular. Cada producto es asignado con una clave única en el ciclo de vida del programa que establece
 * una relación biunívoca entre su identididad y su identificador
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public abstract class Producto {

    // Valores de reabasteciemiento de productos según su prioridad
    public static final int REABASTECIMIENTO_PRIORIDAD_BAJA = 25;
    public static final int REABASTECIMIENTO_PRIORIDAD_MEDIA = 75;
    public static final int REABASTECIMIENTO_PRIORIDAD_ALTA = 150;

    private String nombre;                                              // Nombre comercial del producto
    private Identificador identificador;                                // Identificador único del producto

    private int cantidad;                                               // Cantidad actual en stock del producto

    private float precio;                                               // Precio del producto
    private int stockMinimo;                                            // Cantidad mínima que siempre debe existir en stock
    private PRIORIDAD_PRODUCTO prioridad;                               // Valor directamente relacionado con la cantidad con la que se reabastece el producto
    private FABRICANTES fabricante;                                     // Fabricante del producto
    /**
     * Constructor parametrizado de la clase. Genera un producto a partir de su nombre, camtidad en stock, cantidad mínima en stock,
     * fabricante, prioridad de reabastecimiento, fecha de lanzamiento y estado actual
     *
     * @param nombre      Nombre del producto
     * @param cantidad    Cantidad en stock del producto
     * @param precio      Precio del producto
     * @param stockMinimo Cantidad mínima en stock que siempre debe existir del producto
     * @param fabricante  Valor del tipo enumerado de FABRICANTES
     * @param prioridad   Valor del tipo enumerado PRIORIDAD_PRODUCTO. Representa la demanda del producto
     *                    y se tiene en cuenta al reabastecerlo
     * @throws IllegalArgumentException Si la cantidad o el precio es un entero negativo o 0 o si el stock mínimo es un entero negativo
     */
    public Producto(String nombre, int cantidad, float precio, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad) {
        if (!esCorrecto(cantidad, precio, stockMinimo)) throw new
                IllegalArgumentException("Parámetros inválidos. Compruebe que 'cantidad', 'precio' y 'stockMinimo' sean valores positivos" +
                " y mayores que 0 (stockMinimo sí puede ser 0)");

        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
        this.fabricante = fabricante;
        this.prioridad = prioridad;

        identificador = GeneradorIdentificador.recuperarInstancia().generarIdentificador();
    }

    /**
     * Método accesor del atributo 'nombre'
     *
     * @return Nombre del producto
     */
    protected String getNombre() {
        return nombre;
    }

    /**
     * Método accesor del atributo 'identificador'
     *
     * @return Identificador del producto
     */
    protected Identificador getIdentificador() {
        return identificador;
    }

    /**
     * Método accesor del atributo 'cantidad'
     *
     * @return Cantidad actual en stock del producto
     */
    protected int getCantidad() {
        return cantidad;
    }

    /**
     * Método accesor del atributo 'precio'
     *
     * @return Precio del producto
     */
    protected float getPrecio() {
        return precio;
    }

    /**
     * Método accesor del atributo 'stockMinimo'
     *
     * @return Cantidad en stock mínima del producto
     */
    protected int getStockMinimo() {
        return stockMinimo;
    }

    /**
     * Método accesor del atributo 'prioridad'
     *
     * @return Factor de prioridad con el que debe restablecerse el stock del producto
     */
    protected PRIORIDAD_PRODUCTO getPrioridad() {
        return prioridad;
    }

    /**
     * Método accesor del atributo 'fabricante'
     *
     * @return Fabricante del producto
     */
    protected FABRICANTES getFabricante() {
        return fabricante;
    }

    /**
     * Método mutador del atributo 'nombre'
     *
     * @param nombre Nuevo nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método mutador del atributo 'precio'
     *
     * @param precio Nuevo precio del producto. Debe ser correcto
     * @return Si el cambio no fue aceptado
     */
    public boolean setPrecio(float precio) {
        boolean esCorrecto = esCorrecto(getCantidad(), precio, getStockMinimo());
        if (esCorrecto) {
            this.precio = precio;
        }
        return esCorrecto;
    }

    /**
     * Método mutador del atributo 'stockMinimo'
     *
     * @param stockMinimo Nuevo stock minimo del producto. Debe ser correcto
     * @return Si el cambio fue aceptado
     */
    public boolean setStockMinimo(int stockMinimo) {
        boolean esCorrecto = esCorrecto(getCantidad(), getPrecio(), stockMinimo);
        if (esCorrecto) {
            this.stockMinimo = stockMinimo;
        }
        return esCorrecto;
    }

    /**
     * Método mutador del atributo 'prioridad'
     *
     * @param prioridad Nueva prioridad del producto
     */
    public void setPrioridad(PRIORIDAD_PRODUCTO prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Método mutador del atributo 'fabricante'
     *
     * @param fabricante Nuevo fabricante del producto
     */
    public void setFabricante(FABRICANTES fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * Método mutador del atributo 'cantidad'
     *
     * @param cantidad Incremento del stock del producto
     */
    public void varCantidad(int cantidad) {
        this.cantidad += cantidad;
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
            varCantidad(-cantidad);
        else
            return false;                                               // No se pudo servir el pedido

        return true;
    }

    /**
     * Comprueba si hay suficiente stock como para cubrir un pedido de cierta cantidad
     *
     * @param cantidad Cantidad válida a cubrir por el pedido
     * @return Booleano indicando si hay suficiente stock
     */
    public boolean haySuficienteStock(int cantidad) {
        return cantidad > 0 && getCantidad() - cantidad >= 0;
    }

    /**
     * @return Si es necesario reponer el stock del producto
     */
    public boolean enStockMinimo() {
        return getCantidad() < getStockMinimo();
    }

    /**
     * @param cantidad    Cantidad de entrada en stock. Debe ser un entero positivo
     * @param precio      Precio del producto. Debe ser un real no negativo distinto de 0
     * @param stockMinimo Cantidad mínima en inventario. Debe ser un natural
     * @return Si los campos del producto son válidos
     */
    private boolean esCorrecto(int cantidad, float precio, int stockMinimo) {
        return cantidad >= 0 && precio > 0f && stockMinimo > 0;
    }

    /**
     * @return Cadena formateada de información del producto
     */
    @Override
    public String toString() {
        // TODO - Revisar qué mostrar
        return "PRODUCTO\t" + getNombre() + "-" + getFabricante().toString() +
                "\n\tIdentificador : " + getIdentificador().toString() + "\n\tCantidad en stock : " + getCantidad() +
                "\n\tCantidad en stock mínima : " + getStockMinimo() + "\n\tPrioridad de reabastecimiento : " +
                getPrioridad().toString();
    }


    /**
     * @param obj Objeto con el que se ha de comparar
     * @return Devuelve verdadero si 'obj' es hay coincidencia entre todos los atributos
     * y pertenecen a la misma clase
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Producto)) return false;

        Producto castedObj = (Producto) obj;

        return getNombre().equals(castedObj.getNombre()) &&
                getIdentificador().equals(castedObj.getIdentificador()) &&
                getCantidad() == castedObj.getCantidad() &&
                getPrecio() == castedObj.getPrecio() &&
                getStockMinimo() == castedObj.getStockMinimo() &&
                getPrioridad().equals(castedObj.getPrioridad()) &&
                getFabricante().equals(castedObj.getFabricante());
    }

    /**
     * @return valor hashCode único de la clase Producto. Basado en productos de números primos
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        int primo = 37;

        result = primo * result + getNombre().hashCode();
        result = primo * result + getIdentificador().hashCode();
        result = primo * result + getCantidad();
        result = primo * result + Math.round(getPrecio());
        result = primo * result + getStockMinimo();
        result = primo * result + getPrioridad().hashCode();
        result = primo * result + getFabricante().hashCode();

        return result;
    }
}