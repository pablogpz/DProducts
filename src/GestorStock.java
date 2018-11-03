import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa a la empresa de compra/venta de productos. Para esta entrega solo se tiene en consideración
 * la existencia de una única empresa, por lo que se ha implementado siguiendo el patrón de diseño Singleton. Es
 * fácilmente escalable y refactorizable para incorporarle una identidad propia en un futuro
 *
 * El perfil de la empresa es de venta de componentes de ordenador y periféricos
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class GestorStock {

    private static final GestorStock instanciaActual = new GestorStock();       // Inicialización de la instancia Singleton
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
     * @return Booleano indicando si se ha permitido la inserción del producto al inventario.
     *      Devuelve falso si se intentan insertar productos repetidos
     */
    public boolean agregarProducto(Producto producto) {
        boolean existeProducto = existeProducto(producto);

        if (!existeProducto)                                                    // Comprueba que no exista ya el producto
            stock.put(producto.getIdentificador().valorDe(), producto);         // Agrega el prodcuto al inventario
        else
            reportarError("ERROR al agregar el producto. Ya existe en inventario", producto);

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
        else
            reportarError("ERROR al eliminar producto. No existe en el inventario", producto);

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
        if (existeProducto(producto)) {                                         // Comprueba que el producto exista en inventario
            if (producto.pedir(cantidad)) {                                     // Intenta realiza el pedido
                return true;                                                    // Venta completada
            } else {
                reportarError("ERROR al vender producto. Cantidad errónea o no hay stock suficiente", producto);
                return false;                                                   // Error en la venta
            }
        } else {
            reportarError("ERROR al vender producto. No existe en el inventario", producto);
            return false;                                                       // El producto no está catalogado
        }
    }

    /**
     * Publica un comentario sobre un producto
     *
     * @param producto   Producto al que añadir un comentario
     * @param comentario Objeto de la clase Comentario que añadir al producto indicado
     * @return Booleano indicando si se ha podido añadir el comentario, bien porque el producto no existía o
     *      porque el comentario no es válido
     */
    public boolean comentarProducto(Producto producto, Comentario comentario) {
        if (existeProducto(producto)) {                                         // Comprueba que el producto exista en inventario
            if (producto.comentar(comentario)) {                                // Intenta publicar el comentario
                return true;                                                    // El comentario fue publicado
            } else {
                reportarError("ERROR al publicar comentario. El autor ya ha publicado un comentario", producto);
                return false;                                                   // Error en la publicación
            }
        } else {
            reportarError("ERROR al publicar comentario. El producto no existe en el inventario", producto);
            return false;                                                       // El producto no está catalogado
        }
    }

    /**
     * Recupera un producto de la colección de productos del inventario
     *
     * @param identificador Cadena que identifica de forma única un producto
     * @return Producto buscado. En caso de no encontrarlo devuelve el valor null
     */
    @Nullable
    public Producto recuperarProducto(String identificador) {
        if (existeProducto(identificador)) {                                    // Comprueba que el producto exista en inventaio
            return stock.get(identificador);                                    // Devuelve el producto buscado
        } else {
            reportarError("ERROR al recuperar un producto. " +
                    "El identificador \"" + identificador + "\" no está asociado a ningún producto", null);
            return null;                                                        // El producto no está catalogado
        }
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
     * Devuelve la instancia del GestorStock asociada a todos los clientes. Sigue el patrón de diseño Singleton
     *
     * @return Única instancia de GestorStock existente
     */
    public static GestorStock recuperarInstancia() {
        return instanciaActual;
    }

    /**
     * Reporta un error en alguna operación del GestorStock
     *
     * @param error               Cadena con información en relación al error
     * @param productoRelacionado Instancia de la clase Producto que generó el error
     */
    private void reportarError(String error, Producto productoRelacionado) {
        String productoErroneo = productoRelacionado == null ? "" : "\nProducto : \n\t" +
                productoRelacionado.detalles();

        mostrarMensaje(error + productoErroneo);
    }

    /**
     * Muestra por terminal una cadena de texto
     *
     * @param texto Texto a mostrar
     */
    private void mostrarMensaje(String texto) {
        System.out.println(texto);
    }

    /**
     * Muestra por terminal una lista con todos los detalles de cada producto del inventario
     */
    public void mostrarDetallesStock() {
        String decorador = "***************************************************************";

        mostrarMensaje("INVENTARIO");
        mostrarMensaje(decorador);
        for (Producto producto : stock.values()) {                              // Muestra los detalles de cada producto
            mostrarMensaje(producto.detalles());
            mostrarMensaje(decorador);
        }
    }

}