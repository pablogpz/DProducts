/**
 * Clase que extiende el producto base. Modela un producto de ocio.
 * Es descontable
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ProductoOcio extends ProductoComentable implements Descontable {

    public static final float DESCUENTO_PRODUCTO_OCIO = 0.2f;               // Descuento por defecto del producto

    private float descuento;                                                // Descuento sobre el precio base del producto

    /**
     * Constructor parametrizado de la clase. Genera un producto a partir de su nombre, cantidad en stock, precio, cantidad mínima en stock,
     * fabricante, prioridad de reabastecimiento y parte de la casa a la que pertenece.
     *
     * @param nombre      Nombre del producto
     * @param cantidad    Cantidad en stock del producto
     * @param precio      Precio del producto
     * @param stockMinimo Cantidad mínima en stock que siempre debe existir del producto
     * @param fabricante  Valor del tipo enumerado de FABRICANTES
     * @param prioridad   Valor del tipo enumerado PRIORIDAD_PRODUCTO. Representa la demanda del producto
     *                    y se tiene en cuenta al reabastecerlo
     */
    public ProductoOcio(String nombre, int cantidad, float precio, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad) {
        super(nombre, cantidad, precio, stockMinimo, fabricante, prioridad);
        descuento = DESCUENTO_PRODUCTO_OCIO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float calcularPrecioDescontado() {
        return getPrecio() * (1 + getDescuento());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getDescuento() {
        return descuento;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDescuento(float nuevoDescuento) {
        descuento = nuevoDescuento;
    }

    /**
     * @return Cadena con el contenido base del producto y su descuento
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());

        stringBuilder.append("\n\t").append("Descuento aplicable : ").append(getDescuento() * 100).append("%");

        return stringBuilder.toString() + "\n";
    }

    /**
     * @param obj Objeto con el que comparar
     * @return Devuelve verdadero si entre esta instancia y 'obj' hay coincidencia entre todos los atributos
     * y pertenecen a la misma clase
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;                                       // Comprueba si es la misma instancia
        if (!(obj instanceof ProductoHogar)) return false;                  // Si pertenecen a la misma clase no procede

        ProductoOcio objCasteado = (ProductoOcio) obj;                      // Casteado del objeto

        return super.equals(obj) &&
                getDescuento() == objCasteado.getDescuento();
    }

    /**
     * @return Valor hashCode único de instancia. Basado en productos de números primos
     */
    public int hashCode() {
        int hashCode = super.hashCode();                                    // Hash base
        int primo = 37;                                                     // Operador primo

        hashCode += primo * Math.round(getDescuento());

        return hashCode;
    }

}