import java.util.HashMap;
import java.util.Map;

/**
 * TODO DESCRIPCION
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class GestorStock {

    private GestorStock instanciaActual;
    private Map<String, Producto> stock;

    /**
     * Constructor por defecto de la clase. Sigue el patrón de diseño Singleton
     */
    private GestorStock() {
        // TODO - implement GestorStock.GestorStock
    }

    /**
     * Añade un producto al inventario
     *
     * @param producto Producto que va a ser añadido al inventario.
     * @return Booleano indicando si se ha permitido la inserción del producto al inventario. Devuelve falso si se intentan insertar productos repetidos
     */
    public boolean agregarProducto(Producto producto) {
        // TODO - implement GestorStock.agregarProducto
    }

    /**
     * Elimina un producto del inventario
     *
     * @param producto Instancia de la clase Producto a borrar
     * @return Booleano indicando si se ha encontrado el producto a borrar
     */
    public boolean eliminarProducto(Producto producto) {
        // TODO - implement GestorStock.eliminarProducto
    }

    /**
     * Realiza el pedido de un número cualquiera de unidades de un producto
     *
     * @param cantidad Número de unidades que debe entregar
     * @param producto Producto del que realizar el pedido
     * @return Booleano indicando si se ha podido enviar el pedido, bien sea por falta de stock o porque el producto no se ha encontrado
     */
    public boolean venderProducto(int cantidad, Producto producto) {
        // TODO - implement GestorStock.venderProducto
    }

    /**
     * Publica un comentario sobre un producto
     *
     * @param producto   Producto al que añadir un comentario
     * @param comentario Objeto de la clase Comentario que añadir al producto indicado
     * @return Booleano indicando si se ha podido añadir el comentario, bien porque el producto no existía o porque el comentario no es válido
     */
    public boolean comentarProducto(Producto producto, Comentario comentario) {
        // TODO - implement GestorStock.comentarProducto
    }

    /**
     * Recupera un producto de la colección de productos del inventario
     *
     * @param identificador Cadena que identifica de forma única un producto
     * @return Producto buscado. En caso de no encontrarlo devuelve el valor null
     */
    private Producto recuperarProducto(String identificador) {
        // TODO - implement GestorStock.recuperarProducto
    }

    /**
     * Devuelve la instancia del GestorStock asociada a todos los clientes. Sigue el patrón de diseño Singleton
     *
     * @return Única instancia de GestorStock existente
     */
    public GestorStock recuperarInstancia() {
        // TODO - implement GestorStock.recuperarInstancia
    }

    /**
     * Reporta un error en alguna operación del GestorStock
     *
     * @param error               Cadena con información en relación al error
     * @param productoRelacionado Instancia de la clase Producto que generó el error
     */
    private void reportarError(String error, Producto productoRelacionado) {
        // TODO - implement GestorStock.reportarError
    }

    /**
     * Muestra por terminal una lista con los detalles básicos de cada producto del inventario
     * (nombre, identificador, cantidad, fabricante, fecha de lanzamiento, estado y la lista de comentarios)
     */
    public void mostrarDetallesStock() {
        // TODO - implement GestorStock.mostrarDetallesStock
    }

    /**
     * Muestra por consola una lista con todos los detalles de cada producto del inventario
     * (los básicos junto a su stock mínimo y su prioridad de reabastecimiento)
     */
    public void mostrarDetallesCompletosStock() {
        // TODO - implement GestorStock.mostrarDetallesCompletosStock
    }

}