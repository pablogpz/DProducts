/**
 * Interfaz para aquellos productos cuyo precio sea modificable
 */
public interface Descontable {

    /**
     * Cálcula el valor del atributo precio una vez aplicado el descuento correspondiente
     */
    void calcularPrecioDescontado();

    /**
     * Método accesor del atributo descuento
     *
     * @return Valor del atributo descuento
     */
    int getDescuento();

    /**
     * Método modificador del atributo descuento
     *
     * @param nuevoDescuento Nuevo descuento del precio
     */
    void setDescuento(int nuevoDescuento);

}