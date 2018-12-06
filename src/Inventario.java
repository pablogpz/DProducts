import Identificadores.Identificador;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa a la empresa de compra/venta de productos. Para esta entrega solo se tiene en consideración
 * la existencia de una única empresa, por lo que se ha implementado siguiendo el patrón de diseño Singleton. Es
 * fácilmente escalable y refactorizable para incorporarle una identidad propia en un futuro
 * <p>
 * El perfil de la empresa es de venta de componentes de ordenador y periféricos
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class Inventario {

    private static Inventario instanciaActual = null;                           // Instancia Singleton del inventario
    private Map<String, Producto> stock;                                        // Colección de productos en el inventario

    /**
     * Constructor por defecto de la clase. Sigue el patrón de diseño Singleton
     */
    private Inventario() {
        stock = new HashMap<>();
    }

    /**
     * Añade un producto al inventario
     *
     * @param producto Producto que va a ser añadido al inventario.
     * @return Booleano indicando si se ha permitido la inserción del producto al inventario.
     * Devuelve falso si se intentan insertar productos repetidos
     */
    public boolean agregarProducto(Producto producto) {
        boolean existeProducto = true;

        try {
            existeProducto = existeProducto(producto);
        } catch (NullPointerException e) {
            reportarError(e.getMessage(), null);
        }

        if (!existeProducto)                                                    // Comprueba que no exista ya el producto
            stock.put(producto.getIdentificador().toString(), producto);        // Agrega el prodcuto al inventario
        else                                                                    // Error si existía en inventario
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
        boolean existeProducto = false;

        try {
            existeProducto = existeProducto(producto);
        } catch (NullPointerException e) {
            reportarError(e.getMessage(), null);
        }

        if (existeProducto)                                                     // Comprueba si el producto está catalogado
            stock.remove(producto.getIdentificador().toString());               // Elimina el producto del inventario
        else                                                                    // Error si no existía en inventario
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
    public boolean venderProducto(Producto producto, int cantidad) {
        boolean existeProducto = false;

        try {
            existeProducto = existeProducto(producto);
        } catch (NullPointerException e) {
            reportarError(e.getMessage(), null);
            return false;
        }

        if (existeProducto) {                                                   // Comprueba que el producto exista en inventario
            if (producto.entregar(cantidad)) {                                  // Intenta realiza el pedido
                reponerStock(producto);                                         // Comprueba si es necesario reponer el stock
            } else {
                reportarError("ERROR al vender producto. Cantidad errónea o no hay stock suficiente", producto);
                return false;                                                   // Error en la venta
            }
        } else {
            reportarError("ERROR al vender producto. No existe en el inventario", producto);
            return false;                                                       // El producto no está catalogado
        }

        return true;                                                            // Venta completada
    }

    /**
     * Repone la cantidad en stock de un producto según su prioridad de reabastecimiento. Solo se permite el
     * reabastecimiento si su cantidad en stock actual está estrictamente por debajo del la cantidad en stock mínima
     *
     * @param producto Producto que reabastecer
     */
    private void reponerStock(Producto producto) {
        switch (producto.getPrioridad()) {
            case BAJA:
                producto.incCantidad(Producto.REABASTECIMIENTO_PRIORIDAD_BAJA);
                break;
            case MEDIA:
                producto.incCantidad(Producto.REABASTECIMIENTO_PRIORIDAD_MEDIA);
                break;
            case ALTA:
                producto.incCantidad(Producto.REABASTECIMIENTO_PRIORIDAD_ALTA);
        }
    }


    /**
     * Comprueba si existe un determinado producto catalogado en el inventario
     *
     * @param producto Producto a comprobar su existencia
     * @return Booleano indicando si existía o no el producto en el inventario
     * @throws NullPointerException Si el producto es nulo
     */
    private boolean existeProducto(Producto producto) throws NullPointerException {
        if (producto == null) throw new NullPointerException("Producto nulo");

        return existeProducto(producto.getIdentificador());
    }

    /**
     * Comprueba si existe un determinado producto catalogado en el inventario
     *
     * @param identificador Identificador del producto a comprobar su existencia
     * @return Booleano indicando si existía o no el producto en el inventario
     * @throws NullPointerException Si el identificador es nulo
     */
    private boolean existeProducto(Identificador identificador) throws NullPointerException {
        if (identificador == null) throw new NullPointerException("Identificador nulo");

        return stock.containsKey(identificador.toString());
    }

    /**
     * Recupera un producto de la colección de productos del inventario
     *
     * @param identificador Identificador único del producto
     * @return Producto buscado. En caso de no encontrarlo devuelve el valor null
     */
    public Producto recuperarProducto(Identificador identificador) {
        boolean existeProducto = false;

        try {
            existeProducto = existeProducto(identificador);
        } catch (NullPointerException e) {
            reportarError(e.getMessage(), null);
            return null;
        }

        if (existeProducto) {                                               // Comprueba que el producto exista en inventaio
            return stock.get(identificador.toString());                     // Devuelve el producto buscado
        } else {
            reportarError("ERROR al recuperar un producto. " +
                    "El identificador \"" + identificador.toString() +
                    "\" no está asociado a ningún producto", null);
            return null;                                                    // El producto no está catalogado
        }
    }

    /**
     * Devuelve la instancia del Inventario asociada a todos los clientes. Sigue el patrón de diseño Singleton
     *
     * @return Única instancia de Inventario existente
     */
    public static Inventario recuperarInstancia() {
        if (instanciaActual == null)
            instanciaActual = new Inventario();

        return instanciaActual;
    }

    /**
     * Reporta un error en alguna operación del Inventario
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