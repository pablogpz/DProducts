/**
 * Clase que extiende el producto base. Modela un producto del hogar que es usado en una parte de la casa.
 * Es descontable y gustable
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ProductoHogar extends ProductoComentable implements Gustable, Descontable {

    public static final float DESCUENTO_PRODUCTO_HOGAR = -0.05f;            // Descuento por defecto del producto
    private static final int LIKES_BASE = 0;                                // Likes base del producto del hogar
    private PARTES_CASA parteCasa;                                          // Zona de la casa para el uso del producto
    private int likes;                                                      // Votos positivos del producto
    private float descuento;                                                // Modificador del precio base del producto

    /**
     * Constructor parametrizado de la clase. Genera un producto a partir de su nombre, cantidad en stock, precio,
     * cantidad mínima en stock, fabricante, prioridad de reabastecimiento y parte de la casa a la que pertenece.
     *
     * @param nombre      Nombre del producto
     * @param cantidad    Cantidad en stock del producto
     * @param precio      Precio del producto
     * @param stockMinimo Cantidad mínima en stock que siempre debe existir del producto
     * @param fabricante  Valor del tipo enumerado de {@code FABRICANTES}
     * @param prioridad   Valor del tipo enumerado {@code PRIORIDAD_PRODUCTO}. Representa la demanda del producto
     *                    y se tiene en cuenta al reabastecerlo
     * @param parteCasa   Parte de la casa a la que está destinado el producto.
     */
    public ProductoHogar(String nombre, int cantidad, float precio, int stockMinimo, FABRICANTES fabricante,
                         PRIORIDAD_PRODUCTO prioridad, PARTES_CASA parteCasa) {
        super(nombre, cantidad, precio, stockMinimo, fabricante, prioridad);
        this.parteCasa = parteCasa;
        likes = LIKES_BASE;
        descuento = DESCUENTO_PRODUCTO_HOGAR;
    }

    /**
     * Método accesor del atributo 'parteCasa'
     *
     * @return Parte de la casa a la que está destinado el producto
     */
    public PARTES_CASA getParteCasa() {
        return parteCasa;
    }

    /**
     * Método mutador del atriburo 'parteCasa'
     *
     * @param parteCasa Nueva parte de la casa del producto
     */
    protected void setParteCasa(PARTES_CASA parteCasa) {
        this.parteCasa = parteCasa;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLikes() {
        return likes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void like() {
        likes++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unlike() {
        if (likes > 0) likes--;
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
     * @return Cadena con el contenido base del producto, parte de la casa, likes y su descuento
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());

        stringBuilder.append("\n\t").append("Parte de la casa : ").append(getParteCasa());
        stringBuilder.append("\n\t").append("Likes : ").append(getLikes());
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

        ProductoHogar objCasteado = (ProductoHogar) obj;                    // Casteado del objeto

        return super.equals(obj) &&
                getParteCasa().equals(objCasteado.getParteCasa()) &&
                getLikes() == objCasteado.getLikes() &&
                getDescuento() == objCasteado.getDescuento();
    }

    /**
     * @return Valor hashCode único de instancia. Basado en productos de números primos
     */
    public int hashCode() {
        int hashCode = super.hashCode();                                    // Hash base
        int primo = 37;                                                     // Operador primo

        hashCode += primo * getParteCasa().hashCode();
        hashCode += primo * getLikes();
        hashCode += primo * Math.round(getDescuento());

        return hashCode;
    }

}