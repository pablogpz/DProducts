import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de cargar desde un fichero xml bien formado (sigue el DTD) colecciones de productos
 * y clientes en el inventario
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */
public class CargadorInventario {

    private static final String XML_TAG_PRODUCTOS = "stock";    // Tag XML de la colección de productos
    private static final String XML_TAG_CLIENTES = "clients";   // Tag XML de la colección de productos
    private static final String XML_TAG_FAVORITOS = "favorites";// Tag XML de la colección de productos

    // Instancia que representa el documento XML de entrada
    private Document datosEntrada;

    /*
     * Colección de productos parseados. Son indexados por nombre para asociarlos a los clientes
     * que lo han incluido como favorito
     */
    private Map<String, Producto> productos;

    // Colección de clientes parseados. Son indexados por nombre para asociarlos a sus productos favoritos
    private Map<String, Cliente> clientes;

    // Bandera que indica si ocurrió algún error de configuración inicial
    private boolean estadoIlegal;

    /**
     * Constructor parametrizado de la clase. Crea un documento XML a partir de un fichero de entrada
     *
     * @param ficheroDatos Contiene la información acerca del fichero de datos de entrada
     */
    public CargadorInventario(File ficheroDatos) {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        productos = new HashMap<>();
        clientes = new HashMap<>();
        estadoIlegal = false;                                   // Estado legal inicial
        try {
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            datosEntrada = docBuilder.parse(ficheroDatos);      // Inicialización del fichero XML de entrada
            datosEntrada.getDocumentElement().normalize();      // Inicialización de la raíz del DOM
        } catch (ParserConfigurationException e) {
            reportarError("ERROR al construir un parseador XML\n" + e.getMessage());
            estadoIlegal = true;
        } catch (SAXException e) {
            reportarError("ERROR parsando el documento XML\n" + e.getMessage());
            estadoIlegal = true;
        } catch (IOException e) {
            reportarError("ERROR al abrir el fichero de datos de entrda. Compruebe la ruta el archivo y sus permisos\n" +
                    e.getMessage());
            estadoIlegal = true;
        }
    }

    /**
     * Carga todos los datos leídos del fichero (productos, clientes y productos favoritos)
     * de entrada en la instancia de inventario
     *
     * @return Verdadero si el documento XML es válido y todos los datos fueron cargardos correctamente.
     * Falso en caso de que el documento XML no se pueda validar o algún dato estaba mal formado
     * @throws IllegalStateException Si el cargador no fue correctamente incializado
     */
    public boolean cargarDatos() {
        if (estadoIlegal)                                       // Comprueba que el cargador esté bien inicializado
            throw new IllegalStateException("Ocurrió un problema al inicializar el cargador del inventario");

        int cargaCorrecta = 0;                                   // Bandera que indica si ocurrió algún error en la carga
        boolean insercionCorrecta = false;                       // Bandera para indicar si ocurrió algún error en la inserción

        // *****    LECTURA DE DATOS    *****

        cargaCorrecta += cargarProductos();                      // Intenta cargar los productos en el inventario
        cargaCorrecta += cargarClientes();                       // Intenta cargar los clientes en el inventario
        cargaCorrecta += cargarProductosFavoritos();             // Intenta relacionar los favoritos con sus clientes

        if (cargaCorrecta < 0) {                                 // Comprueba que no haya ocurrido ningún error hasta ahora
            reportarError("ERROR. Algo fue mal en la carga de datos");
            return false;
        }

        // *****    CARGA DE DATOS      *****

        Inventario inventario = Inventario.recuperarInstancia();
        // Carga de los productos en el inventario
        for (Producto producto : productos.values()) {
            insercionCorrecta = inventario.agregarProducto(producto);
        }
        if (!insercionCorrecta) {
            reportarError("ERROR. No se pudieron añadir todos los productos al inventario");
            return false;                                      // Algún producto no se pudo añadir al inventario
        }

        // Carga de los clientes en el inventario
        for (Cliente cliente : clientes.values()) {
            insercionCorrecta = inventario.agregarCliente(cliente);
        }
        if (!insercionCorrecta) {
            reportarError("ERROR. No se pudieron añadir todos los clientes al inventario");
            return false;                                      // Algún cliente no se pudo añadir al inventario
        }

        return true;                                           // Todos los datos se cargaron correctamente
    }

    /**
     * Parsea la colección de productos contenida en el documento XML de datos de entrada
     *
     * @return 0 si todos los productos pudieron ser correctamente parseados. -1 en otro caso
     * @throws IllegalStateException Si el cargador no fue correctamente incializado
     */
    private int cargarProductos() {
        if (estadoIlegal)                                       // Comprueba que el cargador esté bien inicializado
            throw new IllegalStateException("Ocurrió un problema al inicializar el cargador del inventario");

        // Bandera para indicar su ocurrió algún error en la carga de productos
        boolean cargaCorrecta = true;

        // Elementos del DOM
        Element stock = (Element) datosEntrada.getElementsByTagName(XML_TAG_PRODUCTOS).item(0);
        NodeList listaProductos = stock.getElementsByTagName("product");
        Element entradaProducto;

        // Campos de todos los tipos de productos
        String nombre;
        int cantidad;
        float precio;
        int stockMinimo;
        FABRICANTES fabricante;
        PRIORIDAD_PRODUCTO prioridad;
        TIPOS_PRODUCTO tipo;
        PARTES_CASA habitacion;
        Month mesCaducidad;

        for (int i = 0; i < listaProductos.getLength() && cargaCorrecta; i++) {
            entradaProducto = (Element) listaProductos.item(i);

            nombre = entradaProducto.getAttribute("name");
            cantidad = Integer.parseInt(entradaProducto.getAttribute("quantity"));
            precio = Float.parseFloat(entradaProducto.getAttribute("price"));
            stockMinimo = Integer.parseInt(entradaProducto.getAttribute("minimum_stock"));
            fabricante = FABRICANTES.valueOf(entradaProducto.getAttribute("manufacturer"));
            prioridad = PRIORIDAD_PRODUCTO.valueOf(entradaProducto.getAttribute("priority"));
            tipo = TIPOS_PRODUCTO.valueOf(entradaProducto.getAttribute("category"));

            try {
                switch (tipo) {
                    case OCIO:
                        productos.put(nombre, new ProductoOcio(nombre, cantidad, precio, stockMinimo, fabricante, prioridad));
                        break;
                    case HOGAR:
                        habitacion = PARTES_CASA.valueOf(entradaProducto.getAttribute("room"));
                        productos.put(nombre, new ProductoHogar(nombre, cantidad, precio, stockMinimo, fabricante, prioridad, habitacion));
                        break;
                    case ALIMENTACION:
                        mesCaducidad = Month.valueOf(entradaProducto.getAttribute("expiration"));
                        productos.put(nombre, new ProductoAlimentacion(nombre, cantidad, precio, stockMinimo, fabricante, prioridad, mesCaducidad));
                }
            } catch (IllegalArgumentException e) {
                cargaCorrecta = false;
            }
        }

        return cargaCorrecta ? 0 : -1;
    }

    /**
     * Parsea la colección de clientes contenida en el documento XML de datos de entrada
     *
     * @return 0 si todos los clientes fueron parseados correctamente. -1 en otro caso
     */
    private int cargarClientes() {
        // TODO - implement CargadorInventario.cargarClientes
        return -1;
    }

    /**
     * Realiza las relaciones entre clientes y sus productos favoritos indicadas en el documento XML de entrada
     *
     * @return 0 si todas las relaciones tienen referencias correctas y el alias
     * no se repite en el mismo cliente. -1 en otro caso
     */
    private int cargarProductosFavoritos() {
        // TODO - implement CargadorInventario.cargarProductosFavoritos
        return -1;
    }

    /**
     * Reporta un error al usuario por consola
     *
     * @param mensaje Mensaje de error
     */
    private void reportarError(String mensaje) {
        System.out.println(mensaje);
    }

}