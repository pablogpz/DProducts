import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Clase que implementa el archivo de registro asociado a la simulación del proyecto. Genera un archivo de registro con
 * los datos que son registrados en la ruta especificada. Sigue el formato de la entrega
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class Registro {

    private StringBuilder contenido;                                        // Contenido del fichero de registro
    private String rutaRegistro;                                            // Ruta relativa del fichero de registro

    /**
     * Constructor parametrizado. Inicializa el contenido del fichero de registro
     *
     * @param rutaRegistro Ruta relativa del fichero de registro
     */
    public Registro(String rutaRegistro) {
        contenido = new StringBuilder();
        this.rutaRegistro = rutaRegistro;
    }

    /**
     * Método accesor del atributo {@link Registro#contenido}
     *
     * @return {@code StringBuilder} representando el contenido del fichero de registro
     */
    public StringBuilder getContenido() {
        return contenido;
    }

    /**
     * Método accesor del atributo {@link Registro#rutaRegistro}
     *
     * @return Ruta relativa del fichero de registro
     */
    public String getRutaRegistro() {
        return rutaRegistro;
    }

    /**
     * Método mutador del atributo {@link Registro#rutaRegistro}
     *
     * @param rutaRegistro Ruta relativa del fichero de registro
     */
    public void setRutaRegistro(String rutaRegistro) {
        this.rutaRegistro = rutaRegistro;
    }

    /**
     * Registra el inicio del turno i-ésimo
     *
     * @param turno Identificador del turno
     */
    public void inicioTurno(int turno) {
        registrarDato(turno + "\n", "turn: ");
    }

    /**
     * Registra cada cliente que realiza su turno
     *
     * @param cliente Cliente que realiza su turno
     */
    public void registrarCliente(Cliente cliente) {
        registrarDato(cliente.aRegistro() + "\n", "client: ");
    }

    /**
     * Registra el proceso de pedido de un turno. Incluye productos pedidos, comentarios de los productos que los posean
     * y productos repuestos
     *
     * @param cliente       Cliente que realiza el pedido
     * @param prodRepuestos Productos que fueros repuestos durante el pedido
     */
    public void registrarPedido(Cliente cliente, Set<Producto> prodRepuestos) {
        int tipoCliente = 0;                                    // Bandera para indicar el tipo de cliente
        int cantidad = 0;                                       // Cantidad de cada pedido
        if (cliente instanceof ClienteEstandar) {               // Determinación de parámetros
            tipoCliente = 1;
            cantidad = ComportamientoCompraEstandar.UNIDADES_PEDIDO;
        } else if (cliente instanceof ClienteVIP) {
            tipoCliente = 2;
            cantidad = ComportamientoCompraVIP.UNIDADES_PEDIDO;
        }

        Set<Producto> pedido = null;
        switch (tipoCliente) {                                  // Determina como obtener el pedido
            case 1:
                pedido = ((ClienteEstandar) cliente).getComportamientoCompra().prepararPedido(cliente);
                break;
            case 2:
                pedido = ((ClienteVIP) cliente).getComportamientoCompra().prepararPedido(cliente);
                break;
        }

        // PRODUCTOS VENDIDOS POR TURNO
        String descuento = "";                                  // Descuento asociado a cada producto (si procede)
        for (Producto producto : pedido) {
            if (producto instanceof Descontable)                // Comprueba si contiene un descuento
                descuento = ((Descontable) producto).getDescuento() * 100 + "%";

            registrarDato(producto.aRegistro() + " " + descuento + " " + cantidad + "\n", "product: ");

            if (producto instanceof ProductoComentable)         // Comprueba si contiene comentarios
                registrarComentariosProducto((ProductoComentable) producto);
        }

        // PRODUCTOS REPUESTOS POR TURNO (SI PROCEDE)
        if (prodRepuestos.size() > 0) {                         // Comprueba si se han repuesto productos
            registrarDato("The order is done and these products need to be replenished\n", "");
            for (Producto repuesto : prodRepuestos)
                registrarDato(repuesto.aRegistro() + "\n", "product: ");
        } else {
            registrarDato("The order is done\n", "");
        }
    }

    /**
     * Registra los comentarios de un producto comentable
     *
     * @param producto Producto Comentable
     */
    private void registrarComentariosProducto(ProductoComentable producto) {
        registrarDato(producto.comentariosARegistro(), "");
    }

    /**
     * Registra todos los productos vendidos durante simulación en la tienda correspondiente
     *
     * @param tienda Instancia de Inventario que contiene el registro de productos vendidos
     */
    public void registrarProductosVendidos(Inventario tienda) {
        Iterator<Producto> itProdVendidos = tienda.recuperarProductosVendidos();
        Producto producto;
        while (itProdVendidos.hasNext()) {                      // Registra cada producto vendido en la simulacion
            producto = itProdVendidos.next();
            registrarDato(producto.aRegistro() + "\n", "soldProduct: ");
            if (producto instanceof ProductoComentable)         // Comprueba si el producto es comentable
                registrarComentariosProducto((ProductoComentable) producto);
        }
    }

    /**
     * Registra el producto más vendido durante la simulación
     *
     * @param tienda Instancia de Inventario que contiene los datos estadísticos
     */
    public void registrarProductoMasVendido(Inventario tienda) {
        Map<String, Object> datos = tienda.recuperarProductoMasVendido();
        Producto prodMasVendido = (Producto) datos.get(Inventario.CLAVE_PRODUCTO_MAS_VENDIDO_PRODUCTO);
        int numVentas = (int) datos.get(Inventario.CLAVE_PRODUCTO_MAS_VENDIDO_VENTAS);
        String cadenaDato = prodMasVendido.aRegistro() + " " + numVentas + " uds.";

        if (prodMasVendido instanceof Gustable) {                 // Comprueba si el producto es gustable
            int likes = recuperarLikes((Gustable) prodMasVendido);
            cadenaDato += " " + likes + " likes";
        }

        registrarDato(cadenaDato + "\n", "mostSoldProduct: ");
    }

    /**
     * @param prodMasVendido Producto gustable
     * @return Número de likes de un producto gustable
     */
    private int recuperarLikes(Gustable prodMasVendido) {
        return prodMasVendido.getLikes();
    }

    /**
     * Registra el producto más comentado durante la simulación
     *
     * @param tienda Instancia de Inventario que contiene los datos estadísticos
     */
    public void registrarProductoMasComentado(Inventario tienda) {
        ProductoComentable prodMasComentado = (ProductoComentable) tienda.recuperarProductoMasComentado();
        String cadenaDato = prodMasComentado.aRegistro();

        if (prodMasComentado instanceof Gustable) {                 // Comprueba si el producto es gustable
            cadenaDato += " " + recuperarLikes((Gustable) prodMasComentado) + " likes";
        }

        registrarDato(cadenaDato + " " + prodMasComentado.recuperarNumComentarios() + " comments\n",
                "mostCommentedProduct: ");
    }

    /**
     * Registra el cliente que más gastos ha hecho durante la simulación
     *
     * @param tienda Instancia de Inventario que contiene los datos estadísticos
     */
    public void registrarClienteMasGastos(Inventario tienda) {
        Map<String, Object> datos = tienda.recuperarClienteMasGastos();
        Cliente clienteMasGastos = (Cliente) datos.get(Inventario.CLAVE_CLIENTE_MAS_GASTOS_CLIENTE);
        float gasto = (float) datos.get(Inventario.CLAVE_CLIENTE_MAS_GASTOS_GASTO);

        registrarDato(clienteMasGastos.aRegistro() + " " + gasto + "€\n", "clientWhoSpentMore: ");
    }

    /**
     * Registra información sobre un dato en forma de cadena de texto
     *
     * @param dato    Dato a registrar en forma de cadena de texto
     * @param prefijo Prefijo que adjuntar al dato
     */
    private void registrarDato(String dato, String prefijo) {
        getContenido().append(prefijo).append(dato);
    }

    /**
     * Crea el archivo de registro en la ubicación indicada
     *
     * @return Booleano indicando si se pudo escribir el fichero de registro. Es falso si ocurre algún problema
     */
    public boolean crearFicheroRegistro() {
        try (FileWriter fileWriter = new FileWriter(getRutaRegistro())) {
            fileWriter.write(getContenido().toString());
        } catch (IOException e) {                                   // Ocurrió algún error al abrir el archivo
            return false;
        }

        return true;                                                // El archivo se escribió correctamente
    }

}
