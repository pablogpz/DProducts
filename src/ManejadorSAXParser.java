import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador del parseador SAX personalizado para atender a los eventos generador por éste parser.
 * Implementa el parseado de los objetos definidos en el fichero xml de datos de entrada
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

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(XML_TAG_PRODUCTO))
            estado += cargarProducto(attributes);
        else if (qName.equalsIgnoreCase(XML_TAG_CLIENTE))
            estado += cargarCliente(attributes);
        else if (qName.equalsIgnoreCase(XML_TAG_PRODUCTO_FAVORITO))
            estado += cargarProductoFavorito(attributes);
    }


    private int cargarProducto(Attributes attributes) {
        // Campos comunes a todos los productos
        String nombre = attributes.getValue("name");
        int cantidad = Integer.parseInt(attributes.getValue("quantity"));
        float precio = Float.parseFloat(attributes.getValue("price"));
        int stockMinimo = Integer.parseInt(attributes.getValue("minimum_stock"));
        FABRICANTES fabricante = FABRICANTES.valueOf(attributes.getValue("manufacturer"));
        PRIORIDAD_PRODUCTO prioridad = PRIORIDAD_PRODUCTO.valueOf(attributes.getValue("priority"));
        TIPOS_PRODUCTO tipo = TIPOS_PRODUCTO.valueOf(attributes.getValue("category"));

        // Determina el tipo de producto e intenta instanciarlo. Puede contener argumentos ilegales
        try {
            switch (tipo) {
                case OCIO:
                    productos.put(nombre, new ProductoOcio(nombre, cantidad, precio, stockMinimo, fabricante, prioridad));
                    break;
                case HOGAR:
                    PARTES_CASA habitacion = PARTES_CASA.valueOf(attributes.getValue("room"));
                    productos.put(nombre, new ProductoHogar(nombre, cantidad, precio, stockMinimo, fabricante, prioridad, habitacion));
                    break;
                case ALIMENTACION:
                    Month mesCaducidad = Month.valueOf(attributes.getValue("expiration"));
                    productos.put(nombre, new ProductoAlimentacion(nombre, cantidad, precio, stockMinimo, fabricante, prioridad, mesCaducidad));
            }
        } catch (IllegalArgumentException e) {
            return -1;
        }

        return 0;
    }

    private int cargarCliente(Attributes attributes) {
        return -1;
    }

    private int cargarProductoFavorito(Attributes attributes) {
        return -1;
    }

}
