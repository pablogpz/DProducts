import java.time.Month;
import java.util.GregorianCalendar;

public class ProductoAlimentacion extends Producto implements Gustable {

    private Month mesCaducidad;

    /**
     * @param nombre
     * @param cantidad
     * @param precio
     * @param stockMinimo
     * @param fabricante
     * @param prioridad
     * @param fechaLanzamiento
     * @param esReacondicionado
     * @param mesCaducidad
     */
    public ProductoAlimentacion(String nombre, int cantidad, float precio, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad, GregorianCalendar fechaLanzamiento, boolean esReacondicionado, Month mesCaducidad) {
        super(nombre, cantidad, stockMinimo, fabricante, prioridad, fechaLanzamiento, esReacondicionado);
        // TODO - implement ProductoAlimentacion.ProductoAlimentacion
    }

    public String toString() {
        // TODO - implement ProductoAlimentacion.toString
        return null;
    }

    /**
     * @param obj
     */
    public boolean equals(Object obj) {
        // TODO - implement ProductoAlimentacion.equals
        return false;
    }

    public int hashCode() {
        // TODO - implement ProductoAlimentacion.hashCode
        return 0;
    }

    @Override
    public int getLikes() {
        // TODO - implement
        return 0;
    }

    @Override
    public void like() {
        // TODO - implement
    }

    @Override
    public void unlike() {
        // TODO - implement
    }

}