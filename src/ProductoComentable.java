import java.util.ArrayList;
import java.util.GregorianCalendar;

public abstract class ProductoComentable extends Producto {

    private ArrayList<Comentario> comentarios;

    public ProductoComentable(String nombre, int cantidad, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad,
                              GregorianCalendar fechaLanzamiento, boolean esReacondicionado) {
        super(nombre, cantidad, stockMinimo, fabricante, prioridad, fechaLanzamiento, esReacondicionado);
        // TODO - implement ProductoComentable.ProductoComentable
    }

    /**
     * @param comentario
     */
    public boolean comentar(Comentario comentario) {
        // TODO - implement ProductoComentable.comentar
        return false;
    }

    public String toString() {
        // TODO - implement ProductoComentable.toString
        return null;
    }

    /**
     * @param obj
     */
    public boolean equals(Object obj) {
        // TODO - implement ProductoComentable.equals
        return false;
    }

    public int hashCode() {
        // TODO - implement ProductoComentable.hashCode
        return 0;
    }

}