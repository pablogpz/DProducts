/**
 * Interfaz para aquellos productos cuyo precio sea modificable
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */
public interface Descontable {

    /**
     * Calcula el valor del atributo precio una vez aplicado el descuento correspondiente
     */
    float calcularPrecioDescontado();

    /**
     * Método accesor del atributo descuento
     *
     * @return Valor del atributo descuento
     */
    float getDescuento();

    /**
     * Método modificador del atributo descuento
     *
     * @param nuevoDescuento Nuevo descuento del precio
     */
    void setDescuento(float nuevoDescuento);

}