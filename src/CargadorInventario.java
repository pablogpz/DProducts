import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Clase encargada de cargar desde un fichero xml colecciones de productos y clientes en el inventario. El documento
 * debe estar bien formado y ser validado contra su esquema de validación para que sea procesado.
 * <p>
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class CargadorInventario {

    private File ficheroDatos;                                  // Ruta al fichero XML de datos de entrada
    private SAXParser parser;                                   // Parseador SAX de documentos XML
    private ManejadorSAXParser manejadorSAXParser;              // Manejador de los eventos del SAXParser
    private COD_ERROR estado;                                   // Bandera que indica si ocurrió algún error

    /**
     * Constructor parametrizado de la clase. Inicializa el constructor de documentos, dejándolo en estado listo
     * para la carga de datos
     *
     * @param ficheroDatos Contiene la información acerca del fichero de datos de entrada
     */
    public CargadorInventario(File ficheroDatos) {
        SAXParserFactory SAXBuilderFactory = SAXParserFactory.newInstance();
        manejadorSAXParser = new ManejadorSAXParser();
        this.ficheroDatos = ficheroDatos;
        setEstado(COD_ERROR.CARGA_CORRECTA);                    // Estado legal inicial
        try {
            parser = SAXBuilderFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
//            reportarError("ERROR al construir el parseador XML\n" + e.getMessage());
            setEstado(COD_ERROR.CONFIGURACION_FALLIDA);
        }
    }

    /**
     * Lee todos los datos del fichero XML (productos, clientes y productos favoritos) de entrada e instancia sus
     * objetos correspondientes
     * <p>
     * // TODO - Reemplazar por excepción personalizada
     *
     * @throws IllegalStateException Si el cargador no fue correctamente incializado
     */
    public void lecturaDatos() {
        if (!enEstadoValido())                                  // Comprueba que el cargador está bien inicializado
            // TODO - Reemplazar por excepción personalizada
            throw new IllegalStateException("Ocurrió un problema al inicializar el cargador del inventario");

        try {
            parser.parse(ficheroDatos, manejadorSAXParser);     // Parsea el documento XML
        } catch (SAXException e) {
            // TODO - Moverlo a excepción personalizada
//            reportarError("ERROR parsando el documento XML\n" + e.getMessage());
            setEstado(COD_ERROR.PARSEADO_FALLIDO);
        } catch (IOException e) {
            // TODO - Moverlo a excepción personalizada
//            reportarError("ERROR al abrir el fichero de datos de entrda. Compruebe la ruta el archivo y sus permisos\n" +
//                    e.getMessage());
            setEstado(COD_ERROR.FICHERO_NO_ENCONTRADO);
        }

        setEstado(manejadorSAXParser.getEstado());              // Actualiza la bandera de estado

        if (!enEstadoValido()) {                                // Comprueba que no haya ocurrido ningún error en la carga
            // TODO - Moverlo a excepción personalizada
//            reportarError("ERROR. Algo fue mal en la carga de datos");
        }
    }

    /**
     * Carga en el inventario los datos parseados por el manejador del parser SAX
     * <p>
     * // TODO - Reemplazar por excepción personalizada
     *
     * @throws IllegalStateException Si el cargador no fue correctamente incializado
     */
    public void cargarDatos() {
        if (!enEstadoValido())                                  // Comprueba que el cargador está bien inicializado
            // TODO - Reemplazar por excepción personalizada
            throw new IllegalStateException("Ocurrió un problema al inicializar el cargador del inventario");

        boolean insercionCorrecta = true;                       // Bandera que indica si hubo error en la carga

        Inventario inventario = Inventario.recuperarInstancia();
        // Carga de los productos en el inventario
        Iterator<Producto> itProductos = manejadorSAXParser.getIteradorProductosParseados();
        while (itProductos.hasNext() && insercionCorrecta) {
            insercionCorrecta = inventario.agregarProducto(itProductos.next());
        }

        if (!insercionCorrecta) {
            // TODO - Reemplazar por excepción personalizada
            setEstado(COD_ERROR.CARGA_PRODUCTO_FALLIDA);        // Algún producto no se pudo añadir al inventario
//            reportarError("ERROR. No se pudieron añadir todos los productos al inventario");
        }

        // Carga de los clientes en el inventario
        Iterator<Cliente> itClientes = manejadorSAXParser.getIteradorClientesParseados();
        while (itClientes.hasNext() && insercionCorrecta) {
            insercionCorrecta = inventario.agregarCliente(itClientes.next());
        }

        if (!insercionCorrecta) {
            // TODO - Reemplazar por excepción personalizada
            setEstado(COD_ERROR.CARGA_CLIENTE_FALLIDA);         // Algún cliente no se pudo añadir al inventario
//            reportarError("ERROR. No se pudieron añadir todos los clientes al inventario");
        }

        // Relaciona los clientes con sus productos favoritos
        Iterator<Object[]> itProductosFav = manejadorSAXParser.getIteradorProductosFavParseados();
        Object[] datosRelacion;                                 // Datos del cliente, producto favorito y alias
        while (itProductosFav.hasNext() && insercionCorrecta) {
            datosRelacion = itProductosFav.next();

            Producto producto = (Producto) datosRelacion[0];
            Cliente cliente = (Cliente) datosRelacion[1];
            String alias = (String) datosRelacion[2];

            // Intenta añadir el producto favorito a su cliente con el alias dado
            insercionCorrecta = cliente.agregarFavorito(producto, alias);
        }

        if (!insercionCorrecta) {
            // TODO - Reemplazar por excepción personalizada
            setEstado(COD_ERROR.CARGA_PRODUCTO_FAV_FALLIDA);    // Alguna relación falló
//            reportarError("ERROR. No se pudieron relacionar todos los productos favoritos con sus clientes");
        }
    }

    /**
     * @return Si el cargador se ha encontrado con algún error hasta ahora
     */
    private boolean enEstadoValido() {
        return estado == COD_ERROR.CARGA_CORRECTA;
    }

    /**
     * Método accesor del atributo {@link CargadorInventario#estado}
     *
     * @return Estado en el que se encuentra el cargador
     * @see COD_ERROR Para obtener información acerca de los códigos de error
     */
    public COD_ERROR getEstado() {
        return estado;
    }

    /**
     * Método mutador del atributo {@link CargadorInventario#estado}
     *
     * @param estado Nuevo estado
     */
    private void setEstado(COD_ERROR estado) {
        this.estado = estado;
    }
}