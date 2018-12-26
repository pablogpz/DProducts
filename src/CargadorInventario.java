import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
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

    // Ruta relativa al fichero esquema de validación del documento XML de datos de entrada
    private static final String FICHERO_ESQUEMA_XML = "init.xsd";

    private File ficheroDatos;                                  // Ruta al fichero XML de datos de entrada
    private SAXParser parser;                                   // Parseador SAX de documentos XML
    private ManejadorSAXParser manejadorSAXParser;              // Manejador de los eventos del SAXParser
    private COD_ERROR estado;                                   // Bandera que indica si ocurrió algún error

    /**
     * Constructor parametrizado de la clase. Inicializa el constructor de documentos, dejándolo en estado listo
     * para la carga de datos
     *
     * @param ficheroDatos Contiene la información acerca del fichero de datos de entrada
     * @throws ExcepcionCargadorEntrada Si se produce algún error al inicializar el cargador
     */
    public CargadorInventario(File ficheroDatos) throws ExcepcionCargadorEntrada {
        SAXParserFactory SAXBuilderFactory = SAXParserFactory.newInstance();
        manejadorSAXParser = new ManejadorSAXParser();
        this.ficheroDatos = ficheroDatos;
        setEstado(COD_ERROR.CARGADOR_NO_INICIALIZADO);          // Estado legal inicial, pero no inicializado
        try {
            parser = SAXBuilderFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            setEstado(COD_ERROR.CONFIGURACION_FALLIDA);
            throw new ExcepcionCargadorEntrada(this);
        }
    }

    /**
     * Lee todos los datos del fichero XML (productos, clientes y productos favoritos) de entrada e instancia sus
     * objetos correspondientes
     * <p>
     *
     * @throws ExcepcionCargadorEntrada Si se produce algún error al inicializar el cargador o al leer los datos
     */
    public void lecturaDatos() throws ExcepcionCargadorEntrada {
        if (!enEstadoValido())                                  // Comprueba que el cargador está bien inicializado
            throw new ExcepcionCargadorEntrada(this);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            Schema esquema = schemaFactory.newSchema(new File(FICHERO_ESQUEMA_XML));
            Validator validator = esquema.newValidator();       // Validador del documento XML de entrada
            validator.validate(new StreamSource(ficheroDatos)); // VALIDA EL DOCUMENTO XML DE ENTRADA

            parser.parse(ficheroDatos, manejadorSAXParser);     // Parsea el documento XML
        } catch (SAXException e) {
            setEstado(COD_ERROR.XML_INVALIDO);
            throw new ExcepcionCargadorEntrada(this);
        } catch (IOException e) {
            setEstado(COD_ERROR.FICHERO_NO_ENCONTRADO);
            throw new ExcepcionCargadorEntrada(this);
        }

        setEstado(manejadorSAXParser.getEstado());              // Actualiza la bandera de estado

        if (!enEstadoValido())                                  // Comprueba que no haya ocurrido ningún error en la lectura
            throw new ExcepcionCargadorEntrada(this);
    }

    /**
     * Carga en el inventario los datos parseados por el manejador del parser SAX
     * <p>
     *
     * @throws ExcepcionCargadorEntrada Si el cargador no ha leído datos o si se produce algún error al inicializar el cargador,
     * o al cargar los datos
     */
    public void cargarDatos() throws ExcepcionCargadorEntrada {
        // Comprueba que el cargador esté bien inicializado y se hayan leído datos
        if (!enEstadoInicializado())
            throw new ExcepcionCargadorEntrada(this);

        boolean insercionCorrecta = true;                       // Bandera que indica si hubo error en la carga

        Inventario inventario = Inventario.recuperarInstancia();
        // Carga de los productos en el inventario
        Iterator<Producto> itProductos = manejadorSAXParser.getIteradorProductosParseados();
        while (itProductos.hasNext() && insercionCorrecta) {
            insercionCorrecta = inventario.agregarProducto(itProductos.next());
        }

        if (!insercionCorrecta) {
            setEstado(COD_ERROR.CARGA_PRODUCTO_FALLIDA);        // Algún producto no se pudo añadir al inventario
            throw new ExcepcionCargadorEntrada(this);
        }

        // Carga de los clientes en el inventario
        Iterator<Cliente> itClientes = manejadorSAXParser.getIteradorClientesParseados();
        while (itClientes.hasNext() && insercionCorrecta) {
            insercionCorrecta = inventario.agregarCliente(itClientes.next());
        }

        if (!insercionCorrecta) {
            setEstado(COD_ERROR.CARGA_CLIENTE_FALLIDA);         // Algún cliente no se pudo añadir al inventario
            throw new ExcepcionCargadorEntrada(this);
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
            setEstado(COD_ERROR.CARGA_PRODUCTO_FAV_FALLIDA);    // Alguna relación falló
            throw new ExcepcionCargadorEntrada(this);
        }
    }

    /**
     * @return Si el cargador se ha encontrado con algún error que le impida operar hasta ahora
     */
    private boolean enEstadoValido() {
        return estado == COD_ERROR.CARGA_CORRECTA || estado == COD_ERROR.CARGADOR_NO_INICIALIZADO;
    }

    /**
     * @return Si el cargador ha leído algún dato hasta ahora
     */
    private boolean enEstadoInicializado() {
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
     * Método accesor del atributo {@link CargadorInventario#manejadorSAXParser}
     *
     * @return Manejador del parseador SAX
     */
    public ManejadorSAXParser getManejadorSAXParser() {
        return manejadorSAXParser;
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