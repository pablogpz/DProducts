import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
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
     * Constructor parametrizado de la clase. Crea un documento XML a partir de un fichero de entrada
     *
     * @param ficheroDatos Contiene la información acerca del fichero de datos de entrada
     */
    public CargadorInventario(File ficheroDatos) {
        // TODO - implement CargadorInventario.CargadorInventario
    }

    /**
     * Carga todos los datos leídos del fichero (productos, clientes y productos favoritos)
     * de entrada en la instancia de inventario
     *
     * @return Verdadero si el documento XML es válido y todos los datos fueron cargardos correctamente.
     * Falso en caso de que el documento XML no se pueda validar o algún dato estaba mal formado
     */
    public boolean cargarDatos() {
        // TODO - implement CargadorInventario.cargarDatos
        return false;
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

}