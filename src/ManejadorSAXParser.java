import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador del parseador SAX personalizado para atender a los eventos generador por éste parser.
 * Implementa el parseado de los objetos definidos en el fichero xml de datos de entrada.
 * El documento XML debe ser validado con anterioridad, por lo que se presupone que las
 * relaciones incluyen referencias correctas
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class ManejadorSAXParser extends DefaultHandler {

    // Tags XML que denominan los objetos de entrada
    private static final String XML_TAG_PRODUCTO = "product";
    private static final String XML_TAG_CLIENTE = "client";
    private static final String XML_TAG_PRODUCTO_FAVORITO = "favorite";

    // Nombres de los atributos de los productos
    private static final String XML_PRODUCTO_NOMBRE = "name";
    private static final String XML_PRODUCTO_CANTIDAD = "quantity";
    private static final String XML_PRODUCTO_PRECIO = "price";
    private static final String XML_PRODUCTO_STOCk_MINIMO = "minimum_stock";
    private static final String XML_PRODUCTO_FABRICANTE = "manufacturer";
    private static final String XML_PRODUCTO_PRIORIDAD = "priority";
    private static final String XML_PRODUCTO_CATEGORIA = "category";
    private static final String XML_PRODUCTO_PARTE_CASA = "room";
    private static final String XML_PRODUCTO_MES_CADUCIDAD = "expiration";

    // Nombres de los atributos de los clientes
    private static final String XML_CLIENTE_NOMBRE = "name";
    private static final String XML_CLIENTE_EDAD = "age";
    private static final String XML_CLIENTE_LOCALIDAD = "location";
    private static final String XML_CLIENTE_CATEGORIA = "category";

    // Nombres de los atributos de los productos favoritos
    private static final String XML_PRODUCTO_FAV_NOMBRE = "product_name";
    private static final String XML_PRODUCTO_FAV_CLIENTE = "client_name";
    private static final String XML_PRODUCTO_FAV_ALIAS = "alias";

    /*
     * Colección de productos parseados. Son indexados por nombre para asociarlos a los clientes
     * que lo han incluido como favorito
     */
    private Map<String, Producto> productos;

    // Colección de clientes parseados. Son indexados por nombre para asociarlos a sus productos favoritos
    private Map<String, Cliente> clientes;

    /*
     * Bandera que indica el estado en el que terminó el proceso de parseado. Un valor de 0 indica una carga correcta,
     * y un valor negativo que ocurrió algún error durante la carga y no fue correcta
     */
    private int estado;

    /**
     * Constructor por defecto de la clase. Inicializa las estructuras de datos para almacenar productos y clientes
     */
    public ManejadorSAXParser() {
        productos = new HashMap<>();
        clientes = new HashMap<>();
        estado = 0;
    }

    /**
     * Manejador del evento de inicio de lectura de un elemento XML. Encargado de determinar cómo tratar el elemento
     * parseado actual y qué objeto instanciar de él
     *
     * @param uri        URI del recurso
     * @param localName  Nombre local del recurso
     * @param qName      Nombre de la etiqueta
     * @param attributes Conjunto de atributos asociados
     * @throws SAXException Si se produce algún error en el parseado de la entrada
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // Determina de qué tipo es el elemento actual leído
        if (qName.equalsIgnoreCase(XML_TAG_PRODUCTO))
            estado += cargarProducto(attributes);
        else if (qName.equalsIgnoreCase(XML_TAG_CLIENTE))
            estado += cargarCliente(attributes);
        else if (qName.equalsIgnoreCase(XML_TAG_PRODUCTO_FAVORITO))
            estado += cargarProductoFavorito(attributes);
    }

    /**
     * Intenta parsear un producto a partir de su etiqueta de especificación XML
     *
     * @param attributes Datos acerca del producto a instanciar
     * @return 0 si se pudo instanciar el producto. -1 Si ocurrió algún error al instanciar el producto
     */
    private int cargarProducto(Attributes attributes) {
        // Campos comunes a todos los productos
        String nombre = attributes.getValue(XML_PRODUCTO_NOMBRE);
        int cantidad = Integer.parseInt(attributes.getValue(XML_PRODUCTO_CANTIDAD));
        float precio = Float.parseFloat(attributes.getValue(XML_PRODUCTO_PRECIO));
        int stockMinimo = Integer.parseInt(attributes.getValue(XML_PRODUCTO_STOCk_MINIMO));
        FABRICANTES fabricante = FABRICANTES.valueOf(attributes.getValue(XML_PRODUCTO_FABRICANTE));
        PRIORIDAD_PRODUCTO prioridad = PRIORIDAD_PRODUCTO.valueOf(attributes.getValue(XML_PRODUCTO_PRIORIDAD));
        TIPOS_PRODUCTO tipo = TIPOS_PRODUCTO.valueOf(attributes.getValue(XML_PRODUCTO_CATEGORIA));

        // Determina el tipo de producto e intenta instanciarlo. Puede contener argumentos ilegales
        try {
            switch (tipo) {
                case OCIO:
                    productos.put(nombre, new ProductoOcio(nombre, cantidad, precio, stockMinimo, fabricante, prioridad));
                    break;
                case HOGAR:
                    PARTES_CASA habitacion = PARTES_CASA.valueOf(attributes.getValue(XML_PRODUCTO_PARTE_CASA));
                    productos.put(nombre, new ProductoHogar(nombre, cantidad, precio, stockMinimo, fabricante, prioridad, habitacion));
                    break;
                case ALIMENTACION:
                    Month mesCaducidad = Month.valueOf(attributes.getValue(XML_PRODUCTO_MES_CADUCIDAD));
                    productos.put(nombre, new ProductoAlimentacion(nombre, cantidad, precio, stockMinimo, fabricante, prioridad, mesCaducidad));
            }
        } catch (IllegalArgumentException e) {                              // Algún parámetro del constructor no era válido
            return -1;
        }

        return 0;                                                           // El producto fue instanciado correctamente
    }

    /**
     * Intenta parsear un cliente a partir de su etiqueta de especificación XML
     *
     * @param attributes Datos acerca del cliente a instanciar
     * @return 0 si se pudo instanciar el cliente. -1 Si ocurrió algún error al instanciar el cliente
     */
    private int cargarCliente(Attributes attributes) {
        // Campos comunes a todos los clientes
        String nombre = attributes.getValue(XML_CLIENTE_NOMBRE);
        int edad = Integer.parseInt(attributes.getValue(XML_CLIENTE_EDAD));
        String localidad = attributes.getValue(XML_CLIENTE_LOCALIDAD);
        TIPOS_CLIENTE tipo = TIPOS_CLIENTE.valueOf(attributes.getValue(XML_CLIENTE_CATEGORIA));

        // Determina el tipo de ciente e intenta instanciarlo. Puede contener argumento ilegales
        try {
            switch (tipo) {
                case ESTANDAR:
                    clientes.put(nombre, new ClienteEstandar(nombre, edad, localidad));
                    break;
                case VIP:
                    clientes.put(nombre, new ClienteVIP(nombre, edad, localidad));
            }
        } catch (IllegalArgumentException e) {                              // Algún parámetro del constructor no era válido
            return -1;
        }

        return 0;                                                           // El cliente fue instanciado correctamente
    }

    /**
     * Relaciona un cliente con uno de sus productos que debe aparecer com favorito a partir de la especificación
     * de su etiqueta XML. El documento XML debe ser validado con anterioridad, por lo que se presupone que las
     * relaciones incluyen referencias correctas
     *
     * @param attributes Contiene el nombre del producto y del cliente que deben ser relacionados, así como el alias
     *                   con el que se recordará el producto
     * @return 0 si se pudo añadir el producto a la lista de favoritos del cliente. -1 si ocurrió algún error
     */
    private int cargarProductoFavorito(Attributes attributes) {
        // Campos que relacionan clientes con sus productos favoritos
        String nombreProducto = attributes.getValue(XML_PRODUCTO_FAV_NOMBRE);
        String nombreCliente = attributes.getValue(XML_PRODUCTO_FAV_CLIENTE);
        String alias = attributes.getValue(XML_PRODUCTO_FAV_ALIAS);

        Producto producto = productos.get(nombreProducto);                  // Producto a relacionar
        Cliente cliente = clientes.get(nombreCliente);                      // Cliente a relacionar
        boolean cargaFavorito = cliente.agregarFavorito(producto, alias);   // El cliente agrega el producto a favoritos

        return cargaFavorito ? 0 : -1;
    }

}
