import java.util.GregorianCalendar;

public class ProductoOcio extends ProductoComentable implements Descontable {

    /**
     * @param nombre
     * @param cantidad
     * @param precio
     * @param stockMinimo
     * @param fabricante
     * @param prioridad
     * @param fechaLanzamiento
     * @param esReacondicionado
     */
    public ProductoOcio(String nombre, int cantidad, float precio, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad, GregorianCalendar fechaLanzamiento, boolean esReacondicionado) {
        super(nombre, cantidad, stockMinimo, fabricante, prioridad, fechaLanzamiento, esReacondicionado);
        // TODO - implement ProductoOcio.ProductoOcio
    }

    public String toString() {
        // TODO - implement ProductoOcio.toString
        return null;
    }

    /**
     * @param obj
     */
    public boolean equals(Object obj) {
        // TODO - implement ProductoOcio.equals
        return false;
    }

    public int hashCode() {
        // TODO - implement ProductoOcio.hashCode
        return 0;
    }

    @Override
    public void calcularPrecioDescontado() {
        // TODO - implement
    }

    @Override
    public float getDescuento() {
        // TODO - implement
        return 0;
    }

    @Override
    public void setDescuento() {
        // TODO - implement
    }
}