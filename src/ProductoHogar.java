public class ProductoHogar extends ProductoComentable implements Gustable, Descontable {

    private PARTES_CASA parteCasa;

    /**
     * @param nombre
     * @param cantidad
     * @param precio
     * @param stockMinimo
     * @param fabricante
     * @param prioridad
     * @param parteCasa
     */
    public ProductoHogar(String nombre, int cantidad, float precio, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad, PARTES_CASA parteCasa) {
        super(nombre, cantidad, precio, stockMinimo, fabricante, prioridad);
        // TODO - implement ProductoHogar.ProductoHogar
    }

    public String toString() {
        // TODO - implement ProductoHogar.toString
        return null;
    }

    /**
     * @param obj
     */
    public boolean equals(Object obj) {
        // TODO - implement ProductoHogar.equals
        return false;
    }

    public int hashCode() {
        // TODO - implement ProductoHogar.hashCode
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
    public void setDescuento(float nuevoDescuento) {
        // TODO - implement
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