import java.util.Map;
import java.util.Set;

/**
 * Clase que modela un contenedor de datos adicionales de tamaño variable sobre un objeto base. Permite recopilar datos
 * estadísticos adicionales sobre objetos de la simulación sin tener que modificar la clase del objeto base.
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2? GIIIS (Grupo A)
 */
public class DatoEstadistico {

    /*
     * Objeto sobre el que se recopilarán datos adicionales
     */
    private Object objetoBase;

    /*
     * Colección de datos adicionales. Es escalable, heterog?nea y est? indexada por un alias asignado a cada dato adicional
     */
    private Map<String, Object> datos;

    /**
     * Constructor parametrizado de la clase. Inicializa el objeto base y la colección de datos sobre los que se almacenará
     * información adicional
     *
     * @param objBase Objeto sobre el que se van a recopilar los datos
     */
    public DatoEstadistico(Object objBase) {
        // TODO - implement DatoEstadistico.DatoEstadistico
    }

    /**
     * Registra un nuevo dato sobre el objeto base
     *
     * @param alias Nombre descriptivo con el que identificar el dato
     * @param valor Valor del dato
     * @return Booleano indicando si se ha podido registrar el nuevo dato. Devuelve Falso si el dato ya está registrado o
     * cualquiera de los parámetros es nulo, Verdadero en otro caso
     */
    public boolean registrarDato(String alias, Object valor) {
        // TODO - implement DatoEstadistico.registrarDato
        return false;
    }

    /**
     * Elimina de los datos registrados un dato
     *
     * @param alias Nombre del dato a eliminar
     * @return Booleano que indica si se ha podido eliminar el dato registrado. Devuelve Falso si el dato no existía o
     * el alias es nulo, Verdadero en otro caso
     */
    public boolean eliminarDato(String alias) {
        // TODO - implement DatoEstadistico.eliminarDato
        return false;
    }

    /**
     * Actualiza el valor de un dato
     *
     * @param alias Nombre descriptivo con el que se corresponde el dato
     * @param valor Nuevo valor
     * @return Booleano indicando si se pudo actualizar el valor del dato indicado. Devuelve Falso si el dato no existía,
     * los tipos no son compatibles o cualquiera de los parámetros es nulo; Verdadero en otro caso
     */
    public boolean setValor(String alias, Object valor) {
        // TODO - implement DatoEstadistico.setValor
        return false;

    }

    /**
     * Devuelve el valor asociado a un dato concreto
     *
     * @param alias Nombre descriptivo con el que se corresponde el dato
     * @return El dato requerido o nulo si no existe o es nulo
     */
    public Object getValor(String alias) {
        // TODO - implement DatoEstadistico.getValor
        return null;
    }

    /**
     * Método accesor del atributo {@link DatoEstadistico#objetoBase}
     *
     * @return Objeto sobre el que se está recopilando información
     */
    public Object getObjetoBase() {
        // TODO - implement DatoEstadistico.getObjetoBase
        return null;
    }

    /**
     * @return Set de cadenas con los alias de los datos registrados para el objeto base
     */
    public Set<String> recuperarListaDatos() {
        // TODO - implement DatoEstadistico.recuperarListaDatos
        return null;
    }

}