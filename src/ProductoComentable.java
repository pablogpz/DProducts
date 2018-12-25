import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que extiende el producto base para añadir comentarios.
 * Permite realizar comentarios ordenados por puntuación sobre el producto y recuperarlos
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public abstract class ProductoComentable extends Producto {

    private List<Comentario> comentarios;                               // Colección de comentarios que los clientes han publicado sobre el producto

    /**
     * Constructor parametrizado de la clase. Agrega comentarios al producto base
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
    public ProductoComentable(String nombre, int cantidad, float precio, int stockMinimo, FABRICANTES fabricante, PRIORIDAD_PRODUCTO prioridad) {
        super(nombre, cantidad, precio, stockMinimo, fabricante, prioridad);
        comentarios = new ArrayList<>();
    }

    /**
     * Añade un comentario al producto. Solo se permite un comentario por cliente y con una calificación entre 1 y 5 (ambos inclusive)
     *
     * @param comentario Objeto de la clase Comentario que representa el comentario a añadir a la colección de comentarios
     * @return Booleano indicando si se ha podido publicar el comentario. Devuelve falso si el autor ya ha publicado un comentario
     */
    public boolean comentar(Comentario comentario) {
        boolean autorRepetido = false;                                  // Bandera para comprobar la duplicidad de autores en comentarios

        Iterator<Comentario> it = comentarios.iterator();
        while (it.hasNext() && !autorRepetido) {
            if (it.next().getAutor().getIdentificador().equals(
                    comentario.getAutor().getIdentificador()))          // Comprueba que no exista ya un comentario con el mismo autor
                autorRepetido = true;
        }

        if (!autorRepetido) {                                           // Si no ha habido coincidencia se publica el comentario
            comentarios.add(comentario);
            orderarComentarios();                                       // Ordena el nuevo comentario
        }

        return !autorRepetido;
    }

    /**
     * Ordena la lista de comentarios por puntuación
     */
    protected void orderarComentarios() {
        comentarios.sort(new ComparadorComentarioPuntuacion());
    }

    /**
     * Devuelve los comentarios publicados sobre el producto
     *
     * @return Cadena formateada con todos los comentarios sobre el producto
     */
    protected String recuperarComentarios() {
        String decorador = "\n===============================================================\n";
        String comentarios = "";

        orderarComentarios();                                           // Ordena los comentarios antes de mostrarlos
        // Adjunta los detalles de todos los comentarios publicados sobre el producto
        for (Comentario comentario : this.comentarios)
            comentarios += decorador + comentario.toString();

        return comentarios;
    }

    /**
     * @return Cadena formateada base y con todos los comentarios del producto
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder().append(super.toString());

        stringBuilder.append("\n\t").append("Comentarios:\n").append(recuperarComentarios());

        return stringBuilder.toString() + "\n";
    }

    /**
     * Indica si es exactamente igual que el Objeto obj
     *
     * @param obj Objeto con el que comparar
     * @return Devuelve verdadero si entre esta instancia y 'obj' hay coincidencia entre todos los atributos
     * y pertenecen a la misma clase
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;                                       // Comprueba si es la misma instancia
        if (!(obj instanceof ProductoComentable)) return false;             // Si pertenecen a la misma clase no procede

        ProductoComentable objCasteado = (ProductoComentable) obj;          // Casteado del objeto

        return super.equals(obj) &&
                recuperarComentarios().equals(objCasteado.recuperarComentarios());

    }

    /**
     * @return Valor hashCode único de instancia. Basado en productos de números primos
     */
    public int hashCode() {
        int hashCode = super.hashCode();                                    // Hash base
        int primo = 37;                                                     // Operador primo

        hashCode += primo * recuperarComentarios().hashCode();

        return hashCode;
    }

}