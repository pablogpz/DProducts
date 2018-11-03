import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO DESCRIPCION
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class GestorStock {

    private GestorStock instanciaActual = new GestorStock();                    // Inicialización de la instancia Singleton
    private Map<String, Producto> stock;                                        // Colección de productos en el inventario

    /**
     * Constructor por defecto de la clase. Sigue el patrón de diseño Singleton
     */
    private GestorStock() {
        stock = new HashMap<>();
    }

    /**
     * Añade un producto al inventario
     *
     * @param producto Producto que va a ser añadido al inventario.
     * @return Booleano indicando si se ha permitido la inserción del producto al inventario. Devuelve falso si se intentan insertar productos repetidos
     */
    public boolean agregarProducto(Producto producto) {
        boolean existeProducto = existeProducto(producto);

        if (!existeProducto)                                                    // Comprueba que no exista ya el producto
            stock.put(producto.getIdentificador().valorDe(), producto);         // Agrega el prodcuto al inventario

        return !existeProducto;
    }

    /**
     * Elimina un producto del inventario
     *
     * @param producto Instancia de la clase Producto a borrar
     * @return Booleano indicando si se ha encontrado el producto a borrar
     */
    public boolean eliminarProducto(Producto producto) {
        boolean existeProducto = existeProducto(producto);

        if (existeProducto)                                                     // Comprueba si el producto está catalogado
            stock.remove(producto.getIdentificador().valorDe());                // Elimina el producto del inventario

        return existeProducto;
    }

    /**
     * Realiza el pedido de un número cualquiera de unidades de un producto
     *
     * @param cantidad Número de unidades que debe entregar
     * @param producto Producto del que realizar el pedido
     * @return Booleano indicando si se ha podido enviar el pedido, bien sea por falta de stock o porque el producto no se ha encontrado
     */
    public boolean venderProducto(int cantidad, Producto producto) {
        if (existeProducto(producto))                                           // Comprueba que el producto exista en inventario
            return producto.pedir(cantidad);                                    // Intenta realiza el pedido
        else
            return false;                                                       // El producto no estaba catalogado
    }

    /**
     * Publica un comentario sobre un producto
     *
     * @param producto   Producto al que añadir un comentario
     * @param comentario Objeto de la clase Comentario que añadir al producto indicado
     * @return Booleano indicando si se ha podido añadir el comentario, bien porque el producto no existía o porque el comentario no es válido
     */
    public boolean comentarProducto(Producto producto, Comentario comentario) {
        if (existeProducto(producto))                                           // Comprueba que el producto exista en inventario
            return producto.comentar(comentario);                               // Intenta publicar el comentario
        else
            return false;                                                       // El producto no estaba catalogado
    }

    /**
     * Comprueba si existe un determinado producto catalogado en el inventario
     *
     * @param producto Producto a comprobar su existencia
     * @return Booleano indicando si existía o no el producto en el inventario
     */
    private boolean existeProducto(Producto producto) {
        return existeProducto(producto.getIdentificador().valorDe());
    }

    /**
     * Comprueba si existe un determinado producto catalogado en el inventario
     *
     * @param identificador Cadena identificadora del producto a comprobar su existencia
     * @return Booleano indicando si existía o no el producto en el inventario
     */
    private boolean existeProducto(String identificador) {
        return stock.containsKey(identificador);
    }

    /**
     * Recupera un producto de la colección de productos del inventario
     *
     * @param identificador Cadena que identifica de forma única un producto
     * @return Producto buscado. En caso de no encontrarlo devuelve el valor null
     */
    @Nullable
    public Producto recuperarProducto(String identificador) {
        return null;
    }

    /**
     * Devuelve la instancia del GestorStock asociada a todos los clientes. Sigue el patrón de diseño Singleton
     *
     * @return Única instancia de GestorStock existente
     */
    public GestorStock recuperarInstancia() {
        // TODO - implement GestorStock.recuperarInstancia
        return null;
    }

    /**
     * Reporta un error en alguna operación del GestorStock
     *
     * @param error               Cadena con información en relación al error
     * @param productoRelacionado Instancia de la clase Producto que generó el error
     */
    private void reportarError(String error, Producto productoRelacionado) {
        // TODO - implement GestorStock.reportarError
        // TODO Realizar las llamadas
    }

    /**
     * Muestra por terminal una lista con los detalles básicos de cada producto del inventario
     * (nombre, identificador, cantidad, fabricante, fecha de lanzamiento, estado y la lista de comentarios)
     */
    public void mostrarDetallesStock() {
        // TODO - implement GestorStock.mostrarDetallesStock
    }

}