import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.Month;
import java.util.*;

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

    // CÓDIGOS DE ERROR

    public static final int ERR_CODE_PARSEADO_CORRECTO = 0;
    public static final int ERR_CODE_PRODUCTO_MALFORMADO = -3;
    public static final int ERR_CODE_CLIENTE_MALFORMADO = -4;

    /*
     * Colección de productos parseados. Son indexados por nombre para asociarlos a los clientes
     * que lo han incluido como favorito
     */
    private Map<String, Producto> productos;

    // Colección de clientes parseados. Son indexados por nombre para asociarlos a sus productos favoritos
    private Map<String, Cliente> clientes;

    // Lista de relaciones entre clientes, sus productos favoritos y los sobrenombres con el que los recuerdan
    private List<Object[]> productosFavoritos;

    // Bandera que indica el estado en el que terminó el proceso de parseado
    private int estado;

    /**
     * Constructor por defecto de la clase. Inicializa las estructuras de datos para almacenar productos, clientes y
     * las relaciones con sus productos favoritos
     */
    public ManejadorSAXParser() {
        productos = new HashMap<>();
        clientes = new HashMap<>();
        productosFavoritos = new ArrayList<>();
        estado = ERR_CODE_PARSEADO_CORRECTO;
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
            estado = cargarProducto(attributes);
        else if (qName.equalsIgnoreCase(XML_TAG_CLIENTE))
            estado = cargarCliente(attributes);
        else if (qName.equalsIgnoreCase(XML_TAG_PRODUCTO_FAVORITO))
            cargarProductoFavorito(attributes);
    }

    /**
     * Intenta parsear un producto a partir de su etiqueta de especificación XML
     *
     * @param attributes Datos acerca del producto a instanciar
     * @return ERR_CODE_PARSEADO_CORRECTO si se pudo instanciar el producto. ERR_CODE_PRODUCTO_MALFORMADO Si ocurrió
     * algún error al instanciar el producto
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
                    productos.put(nombre, new ProductoHogar(nombre, cantidad, precio, stockMinimo, fabricante, prioridad,
                            habitacion));
                    break;
                case ALIMENTACION:
                    Month mesCaducidad = Month.valueOf(attributes.getValue(XML_PRODUCTO_MES_CADUCIDAD));
                    productos.put(nombre, new ProductoAlimentacion(nombre, cantidad, precio, stockMinimo, fabricante,
                            prioridad, mesCaducidad));
            }
        } catch (IllegalArgumentException e) {                              // Algún parámetro del constructor no era válido
            return ERR_CODE_PRODUCTO_MALFORMADO;
        }

        return ERR_CODE_PARSEADO_CORRECTO;                                  // El producto fue instanciado correctamente
    }

    /**
     * Intenta parsear un cliente a partir de su etiqueta de especificación XML
     *
     * @param attributes Datos acerca del cliente a instanciar
     * @return ERR_CODE_PARSEADO_CORRECTO si se pudo instanciar el cliente. ERR_CODE_CLIENTE_MALFORMADO
     * Si ocurrió algún error al instanciar el cliente
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
            return ERR_CODE_CLIENTE_MALFORMADO;
        }

        return ERR_CODE_PARSEADO_CORRECTO;                                  // El cliente fue instanciado correctamente
    }

    /**
     * Guarda la relación de un cliente con uno de sus productos que debe aparecer com favorito a partir de la
     * especificación de su etiqueta XML. El documento XML debe ser validado con anterioridad, por lo que se presupone
     * que las relaciones incluyen referencias correctas
     *
     * @param attributes Contiene el nombre del producto y del cliente que deben ser relacionados, así como el alias
     *                   con el que se recordará el producto
     */
    private void cargarProductoFavorito(Attributes attributes) {
        Producto producto = productos.get(attributes.getValue(XML_PRODUCTO_FAV_NOMBRE));    // Producto a relacionar
        Cliente cliente = clientes.get(attributes.getValue(XML_PRODUCTO_FAV_CLIENTE));      // Cliente a relacionar
        String alias = attributes.getValue(XML_PRODUCTO_FAV_ALIAS);                         // Alias del producto

        // Colección heterogénea de los datos necesarios para relacionar un cliente con un producto favorito
        Object[] relacionFavorito = {producto, cliente, alias};
        productosFavoritos.add(relacionFavorito);
    }

    /**
     * Método accesor del atributo 'estado'. Puede encontrarse en los siguientes estados.
     * <ul>
     * <li>0 indica un parseado correcto</li>
     * <li>-1 que ocurrió un error al parsear un producto</li>
     * <li>-2 que ocurrió un error al parsear un cliente</li>
     * <li>-3 que ocurrió un error al parsear un producto favorito</li>
     * </ul>
     *
     * @return Estado en el que se encuentra el manejador
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @return Iterador sobre la colección de productos parseados del fichero de datos de entrada
     */
    public Iterator<Producto> getIteradorProductosParseados() {
        return productos.values().iterator();
    }

    /**
     * @return Iterador sobre la colección de clientes parseados del fichero de datos de entrada
     */
    public Iterator<Cliente> getIteradorClientesParseados() {
        return clientes.values().iterator();
    }

    /**
     * @return Iterador sobre la colección de relaciones entre clientes y sus productos favoritos
     * parseados del fichero de datos de entrada
     */
    public Iterator<Object[]> getIteradorProductosFavParseados() {
        return productosFavoritos.iterator();
    }

}
