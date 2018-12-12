import java.time.Month;

/**
 * Clase que extiende el producto base. Modela un producto de alimentación con mes de caducidad,
 * descontable y gustable
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ProductoAlimentacion extends Producto implements Gustable, Descontable {

    private static final int LIKES_BASE = 0;                                // Likes base del producto de alimentacion
    public static final int DESCUENTO_PRODUCTO_ALIMENTACION = 10;           // Descuento por defecto del producto

    private Month mesCaducidad;                                             // Mes en el que caduca el producto
    private int likes;                                                      // Votos positivos del producto
    private float descuento;                                                // Modificador del precio base del producto

    /**
     * Constructor parametrizado de la clase. Agrega mes de caducidad al producto base
     *
     * @param nombre            Nombre comercial del producto
     * @param cantidad          Cantidad actual en stock del producto
     * @param precio            Precio del producto
     * @param stockMinimo       Cantidad mínima que siempre debe existir en stock
     * @param fabricante        Valor del tipo enumerado de FABRICANTES
     * @param prioridad         Valor del tipo enumerado PRIORIDAD_PRODUCTO. Representa la demanda del producto
     *                          y se tiene en cuenta al reabastecerlo
     * @param mesCaducidad      Mes de caducidad del producto de alimentación
     */
    public ProductoAlimentacion(String nombre, int cantidad, float precio, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad, Month mesCaducidad) {
        super(nombre, cantidad, precio, stockMinimo, fabricante, prioridad);
        this.mesCaducidad = mesCaducidad;
        this.likes = LIKES_BASE;
        descuento = DESCUENTO_PRODUCTO_ALIMENTACION;
    }

    /**
     * Método accesor del atributo 'mesCaducidad'
     *
     * @return Mes de caducidad del producto
     */
    public Month getMesCaducidad() {
        return mesCaducidad;
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
     * @return Cadena con el contenido base del producto, su mes de caducidad, likes y su descuento
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());

        stringBuilder.append("\n\t").append("Mes de caducidad :").append(getMesCaducidad());
        stringBuilder.append("\n\t").append("Likes :").append(getLikes());
        stringBuilder.append("\n\t").append("Descuento aplicable :").append(getDescuento() * 100).append("%");

        return stringBuilder.toString();
    }

    /**
     * @param obj Objeto con el que comparar
     * @return Devuelve verdadero si entre esta instancia y 'obj' hay coincidencia entre todos los atributos
     * y pertenecen a la misma clase
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;                                       // Comprueba si es la misma instancia
        if (!(obj instanceof ProductoAlimentacion)) return false;           // Si pertenecen a la misma clase no procede

        ProductoAlimentacion objCasteado = (ProductoAlimentacion) obj;      // Casteado del objeto

        return super.equals(objCasteado) &&
                getMesCaducidad().equals(objCasteado.getMesCaducidad()) &&
                getLikes() == objCasteado.getLikes() &&
                getDescuento() == objCasteado.getDescuento();
    }

    /**
     * @return Valor hashCode único de instancia. Basado en productos de números primos
     */
    public int hashCode() {
        int hashCode = super.hashCode();                                    // Hash base
        int primo = 37;                                                     // Operador primo

        hashCode += primo * getMesCaducidad().hashCode();
        hashCode += primo * getLikes();
        hashCode += primo * Math.round(getDescuento());

        return hashCode;
    }

}