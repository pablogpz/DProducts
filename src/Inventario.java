import Identificadores.Identificador;

import java.util.*;

/**
 * Clase que representa a la empresa de compra/venta de productos. Se tiene en consideración
 * la existencia de una única empresa, por lo que se ha implementado siguiendo el patrón de diseño Singleton
 * <p>
 * El perfil de la empresa es de venta de componentes de ordenador y periféricos
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class Inventario {

    // CONSTANTES RELACIONADAS CON LOS DATOS ESTADÍSTICOS
    private static final String DAT_EST_ALIAS_UNIDADES_VENDIDAS = "udVendidas";

    // Cantidad vendida de cada unidad de la coleccion de productos a vender
    private final static int CANTIDAD_VENTA_COLECCION = 1;

    private static Inventario instanciaActual = null;                           // Instancia Singleton del inventario
    private Map<Identificador, Cliente> clientes;                               // Colección de clientes usuarios
    private Map<Identificador, Producto> stock;                                 // Colección de productos en el inventario

    // Colecciones relacionados con los datos estadísticos de la simulación

    private Set<Producto> productosVendidos;                                    // Productos que se han vendido
    private List<DatoEstadistico> estadisticasProductos;                        // Estadísticas relacionadas con los productos
    private List<DatoEstadistico> estadisticasClientes;                         // Estadísticas relacionadas con los clientes

    /**
     * Constructor por defecto de la clase. Sigue el patrón de diseño Singleton
     */
    private Inventario() {
        clientes = new HashMap<>();
        stock = new HashMap<>();

        productosVendidos = new HashSet<>();
        estadisticasProductos = new ArrayList<>();
        estadisticasClientes = new ArrayList<>();
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
     * Registra a un nuevo cliente
     *
     * @param cliente Cliente a registrar
     * @return Si se pudo agregar. No se permite añadir múltiples veces un mismo cliente.
     * Si se le pasa un cliente con valor nulo devuelve falso
     */
    public boolean agregarCliente(Cliente cliente) {
        if (cliente == null)
            return false;

        boolean existeCliente = clientes.containsKey(cliente.getIdentificador());

        if (!existeCliente)
            clientes.put(cliente.getIdentificador(), cliente);

        return !existeCliente;
    }

    /**
     * Da de baja a un cliente
     *
     * @param identificador Identificador del cliente a dar de baja
     * @return Si se pudo eliminar al cliente. No se pueden eliminar clientes que no estén registrados
     */
    public boolean eliminarCliente(Identificador identificador) {
        if (identificador == null)
            return false;

        boolean existeCliente = clientes.containsKey(identificador);

        if (existeCliente)
            clientes.remove(identificador);

        return existeCliente;
    }

    /**
     * Añade un producto al inventario
     *
     * @param producto Producto que va a ser añadido al inventario.
     * @return Booleano indicando si se ha permitido la inserción del producto al inventario.
     * Devuelve falso si se intentan insertar productos repetidos
     */
    public boolean agregarProducto(Producto producto) {
        boolean existeProducto = true;                                          // Bandera que indica existía ya el producto

        try {
            existeProducto = existeProducto(producto);
        } catch (NullPointerException e) {
            reportarError(e.getMessage(), producto);
        }

        if (!existeProducto)                                                    // Comprueba que no exista ya el producto
            stock.put(producto.getIdentificador(), producto);                   // Agrega el prodcuto al inventario
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
        boolean existeProducto = false;                                         // Bandera que indica si el producto ya existía

        try {
            existeProducto = existeProducto(producto);
        } catch (NullPointerException e) {
            reportarError(e.getMessage(), producto);
        }

        if (existeProducto)                                                     // Comprueba si el producto está catalogado
            stock.remove(producto.getIdentificador());                          // Elimina el producto del inventario
        else                                                                    // Error si no existía en inventario
            reportarError("ERROR al eliminar producto. No existe en el inventario", producto);

        return existeProducto;
    }

    /**
     * Realiza el pedido de un número cualquiera de unidades de un producto
     *
     * @param cantidad Número de unidades que debe entregar
     * @param producto Producto del que realizar el pedido
     * @return Booleano indicando si se ha podido enviar el pedido, bien sea por falta de stock o
     * porque el producto no se ha encontrado
     */
    public boolean venderProducto(Producto producto, int cantidad) {
        boolean existeProducto = false;                                         // Bandera para indicar si el producto ya existía

        try {
            existeProducto = existeProducto(producto);
        } catch (NullPointerException e) {
            reportarError(e.getMessage(), producto);
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

        // ESTADISTICAS
        registrarVentaProducto(producto, cantidad);                             // Registra la venta del producto

        return true;                                                            // Venta completada
    }

    /**
     * Realiza el pedido de una cantidad fija determinado por {@code CANTIDAD_VENTA_COLECCION} de una colección de productos.
     * Si el stock actual no puede cubrir odos los pedidos no se realiza ninguno.
     *
     * @param coleccionProductos Colección de productos a despachar
     * @return Devuelve verdadero si se pudieron realizar todos los pedidos. Devuelve falso si la colección es nula o vacía,
     * no se puede cubrir algún pedido u ocurrió algún error en la venta de alguno de los productos
     */
    public boolean venderColeccionProductos(Collection<Producto> coleccionProductos) {
        if (coleccionProductos == null || coleccionProductos.size() == 0) {     // Comprueba que la coleccion no sea nula ni vacía
            mostrarMensaje("La colección de productos a pedir está vacía");
            return false;
        }

        boolean pedidoCompleto = true;                                          // Indica si se pueden cubrir todos los pedidos
        boolean ocurrioError = false;                                           // Indica si ocurrió algún error en la venta

        // Primer bucle para determinar si se pueden cubrir los pedidos
        Iterator<Producto> iterator = coleccionProductos.iterator();
        while (iterator.hasNext() && pedidoCompleto) {
            if (!iterator.next().haySuficienteStock(CANTIDAD_VENTA_COLECCION))
                pedidoCompleto = false;
        }

        if (!pedidoCompleto) {                                                    // Si no se pudieron cubrir los pedidos no se realizan
            reportarError("ERROR al procesar el pedido de todos los productos favoritos. " +
                    "No hay stock de alguno de los productos que desea", coleccionProductos);
            return false;
        }

        // Intenta realizar la venta de todos los pedidos
        iterator = coleccionProductos.iterator();
        while (iterator.hasNext()) {
            ocurrioError = venderProducto(iterator.next(), CANTIDAD_VENTA_COLECCION);
        }

        return ocurrioError;
    }

    /**
     * Repone la cantidad en stock de un producto según su prioridad de reabastecimiento. Solo se permite el
     * reabastecimiento si su cantidad en stock actual está estrictamente por debajo del la cantidad en stock mínima
     *
     * @param producto Producto que reabastecer
     */
    private void reponerStock(Producto producto) {
        if (producto.enStockMinimo()) {
            switch (producto.getPrioridad()) {
                case BAJA:
                    producto.varCantidad(Producto.REABASTECIMIENTO_PRIORIDAD_BAJA);
                    break;
                case MEDIA:
                    producto.varCantidad(Producto.REABASTECIMIENTO_PRIORIDAD_MEDIA);
                    break;
                case ALTA:
                    producto.varCantidad(Producto.REABASTECIMIENTO_PRIORIDAD_ALTA);
            }
        }
    }

    /**
     * Comprueba si existe un determinado producto catalogado en el inventario
     *
     * @param producto Producto a comprobar su existencia
     * @return Booleano indicando si existía o no el producto en el inventario
     * @throws NullPointerException Si el producto es nulo
     */
    private boolean existeProducto(Producto producto) {
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
    private boolean existeProducto(Identificador identificador) {
        if (identificador == null) throw new NullPointerException("Identificador nulo");

        return stock.containsKey(identificador);
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
            reportarError(e.getMessage(), identificador);
            return null;
        }

        if (existeProducto) {                                                   // Comprueba que el producto exista en inventario
            return stock.get(identificador);                                    // Devuelve el producto buscado
        } else {
            reportarError("ERROR al recuperar un producto. " +
                    "El identificador \"" + identificador.toString() +
                    "\" no está asociado a ningún producto", identificador);
            return null;                                                        // El producto no está catalogado
        }
    }

    /**
     * @return Iterador sobre la colección de productos vendidos durante la simulación
     */
    public Iterator<Producto> recuperarProductosVendidos() {
        return productosVendidos.iterator();
    }

    /**
     * Registra los datos estadísticos relativos a la venta de un producto (qué productos se han vendido y
     * unidades totales vendidas)
     *
     * @param producto Producto vendido
     * @param cantidad Dato sobre las unidades que contiene el pedido
     */
    private void registrarVentaProducto(Producto producto, int cantidad) {
        // Intenta recuperar el dato estadístico equivalente
        DatoEstadistico datoEstadistico = recuperarDatoEstadisticoProducto(producto);

        if (datoEstadistico != null) {                                          // Comprueba si existía ya o hay que crearlo
            // Existe. Se actualizan las unidades vendidas
            datoEstadistico.setValor(DAT_EST_ALIAS_UNIDADES_VENDIDAS,
                    (int) datoEstadistico.getValor(DAT_EST_ALIAS_UNIDADES_VENDIDAS) + cantidad);
        } else {
            // NO existe. Se crea el dato estadístico equivalente y se añade a la colección
            datoEstadistico = new DatoEstadistico(producto);
            datoEstadistico.registrarDato(DAT_EST_ALIAS_UNIDADES_VENDIDAS, cantidad);
            estadisticasProductos.add(datoEstadistico);
            productosVendidos.add(producto);                                    // Registra el nuevo producto
        }
    }

    /**
     * @return Producto más vendido del inventario
     */
    public Producto recuperarProductoMasVendido() {
        estadisticasProductos.sort(new Comparator<DatoEstadistico>() {          // Ordena descendentemente por ud. totales vendidas
            @Override
            public int compare(DatoEstadistico o1, DatoEstadistico o2) {
                return Integer.compare((int) o2.getValor(DAT_EST_ALIAS_UNIDADES_VENDIDAS),
                        (int) o1.getValor(DAT_EST_ALIAS_UNIDADES_VENDIDAS));
            }
        });

        return (Producto) estadisticasProductos.get(0).getObjetoBase();         // Devuelve el primer producto (más vendido)
    }

    /**
     * @return Producto más comentado del inventario
     */
    public Producto recuperarProductoMasComentado() {
        Iterator<Producto> itStock = stock.values().iterator();                 // Iterador de los productos en stock
        List<Producto> productos = new ArrayList<>();                           // Lista auxiliar para apoyar la busqueda mediante ordenación

        // Vuelca todos los productos en stock en la lista auxiliar
        while (itStock.hasNext())
            productos.add(itStock.next());

        productos.sort(new Comparator<Producto>() {                             // Ordena descendentemente por el número de comentarios
            @Override
            public int compare(Producto o1, Producto o2) {
                if (o1 instanceof ProductoComentable) {
                    ProductoComentable o1Com = (ProductoComentable) o1;         // El primer producto es comentable
                    if (o2 instanceof ProductoComentable) {
                        ProductoComentable o2Com = (ProductoComentable) o2;     // El segundo producto es comentable
                        // El resultado es la comparación entre el número de sus comentarios
                        return Integer.compare(o2Com.recuperarNumComentarios(), o1Com.recuperarNumComentarios());
                    } else {                                                    // Solo es comentable el primer producto
                        return -1;                                              // Debe ir primero el primer producto
                    }
                } else {                                                        // El primer producto no es comentable
                    if (o2 instanceof ProductoComentable)
                        // El segundo producto es comentable
                        return 1;                                               // Debe ir primero el segundo producto
                    else                                                        // Ningún producto es comentable
                        return 0;                                               // Da igual su orden
                }
            }
        });

        return productos.get(0);                                                // Devuelve el primer producto (más comentado)
    }

    /**
     * Halla el dato estadístico equivalente a un producto dado
     *
     * @param producto Producto del que buscar sus estadísticas
     * @return {@code DatoEstadistico} del producto buscado, si no existe devuelve nulo
     */
    private DatoEstadistico recuperarDatoEstadisticoProducto(Producto producto) {
        boolean encontrado = false;                                             // Bandera de búsqueda
        Iterator<DatoEstadistico> it = estadisticasProductos.iterator();        // Iterador de datos est. de productos
        DatoEstadistico datoEstadistico = null;                                 // Dato estadístico temporal

        while (it.hasNext() && !encontrado) {                                   // Busca el dato est. equivalente al producto
            datoEstadistico = it.next();
            if (datoEstadistico.equals(producto))
                encontrado = true;
        }

        return encontrado ? datoEstadistico : null;
    }

    /**
     * Reporta un error en alguna operación del Inventario
     *
     * @param error             Cadena con información en relación al error
     * @param objetoRelacionado Instancia que generó el error
     */
    private void reportarError(String error, Object objetoRelacionado) {
        String objetoErroneo = objetoRelacionado == null ? "" : "\nObjeto : \n\t" +
                objetoRelacionado.toString();

        mostrarMensaje(error + objetoErroneo);
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
     * @return Lista formateada con todos los detalles de cada producto del inventario
     */
    @Override
    public String toString() {
        // TODO - Revisar todo lo que debe mostrar
        String decorador = "***************************************************************";
        StringBuilder stringBuilder = new StringBuilder(decorador);

        stringBuilder.append("INVENTARIO");
        stringBuilder.append(decorador).append("\n");
        for (Producto producto : stock.values()) {                              // Almacena los detalles de cada producto
            stringBuilder.append(producto.toString());
            stringBuilder.append(decorador).append("\n");
        }

        return stringBuilder.toString() + "\n";
    }

}