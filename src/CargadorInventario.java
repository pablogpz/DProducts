import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
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

    /**
     * Instancia que representa el documento XML de entrada
     */
    private Document datosEntrada;

    /**
     * Elemento con la raíz de la que cuelga el DOM del archivo XML con los datos de entrada
     */
    private Element raizDatos;

    /**
     * Colección de productos parseados. Son indexados por nombre para asociarlos a los clientes
     * que lo han incluido como favorito
     */
    private Map<String, Producto> productos;

    /**
     * Colección de clientes parseados. Son indexados por nombre para asociarlos a sus productos favoritos
     */
    private Map<String, Cliente> clientes;

    /**
     * Bandera que indica si ocurrió algún error de configuración inicial
     */
    private boolean estadoIlegal;

    /**
     * Constructor parametrizado de la clase. Crea un documento XML a partir de un fichero de entrada
     *
     * @param ficheroDatos Contiene la información acerca del fichero de datos de entrada
     */
    public CargadorInventario(File ficheroDatos) {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        estadoIlegal = false;                                   // Estado legal inicial
        try {
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            datosEntrada = docBuilder.parse(ficheroDatos);      // Inicialización del fichero XML de entrada
            raizDatos = datosEntrada.getDocumentElement();      // Inicialización de la raíz del DOM
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

        boolean cargaCorrecta;                                  // Bandera para indicar su ocurrió algún error en la carga

        cargaCorrecta = cargarProductos();                      // Intenta cargar los productos en el inventario
        cargaCorrecta = cargarClientes();                       // Intenta cargar los clientes en el inventario
        cargaCorrecta = cargarProductosFavoritos();             // Intenta relacionar los favoritos con sus clientes

        if (!cargaCorrecta) {                                   // Comprueba que no haya ocurrido ningún error hasta ahora
            reportarError("ERROR. Algo fue mal en la carga de datos");
            return false;
        }

        Inventario inventario = Inventario.recuperarInstancia();
        // Carga de los productos en el inventario
        for (Producto producto : productos.values()) {
            cargaCorrecta = inventario.agregarProducto(producto);
        }
        if (!cargaCorrecta) {
            reportarError("ERROR. No se pudieron añadir todos los productos al inventario");
            return false;                                      // Algún producto no se pudo añadir al inventario
        }

        // Carga de los clientes en el inventario
        for (Cliente cliente : clientes.values()) {
            cargaCorrecta = inventario.agregarCliente(cliente);
        }
        if (!cargaCorrecta) {
            reportarError("ERROR. No se pudieron añadir todos los clientes al inventario");
            return false;                                      // Algún cliente no se pudo añadir al inventario
        }

        return true;                                           // Todos los datos se cargaron correctamente
    }

    /**
     * Parsea la colección de productos contenida en el documento XML de datos de entrada
     *
     * @return Verdadero si todos los productos pudieron ser correctamente parseados. Falso en otro caso
     */
    private boolean cargarProductos() {
        // TODO - implement CargadorInventario.cargarProductos
        return false;
    }

    /**
     * Parsea la colección de clientes contenida en el documento XML de datos de entrada
     *
     * @return Verdadero si todos los clientes fueron parseados correctamente. Falso en otro caso
     */
    private boolean cargarClientes() {
        // TODO - implement CargadorInventario.cargarClientes
        return false;
    }

    /**
     * Realiza las relaciones entre clientes y sus productos favoritos indicadas en el documento XML de entrada
     *
     * @return Verdadero si todas las relaciones tienen referencias correctas y el alias
     * no se repite en el mismo cliente. Falso en otro caso
     */
    private boolean cargarProductosFavoritos() {
        // TODO - implement CargadorInventario.cargarProductosFavoritos
        return false;
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