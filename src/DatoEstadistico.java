import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Clase que modela un contenedor de datos adicionales de tamaño variable sobre un objeto base. Permite recopilar datos
 * estadísticos adicionales sobre objetos de la simulación sin tener que modificar la clase del objeto base.
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class DatoEstadistico {

    /*
     * Objeto sobre el que se recopilarán datos adicionales
     */
    private Object objetoBase;

    /*
     * Colección de datos adicionales. Es escalable, heterogénea y está indexada por un alias asignado a cada dato adicional
     */
    private Map<String, Object> datos;

    /**
     * Constructor parametrizado de la clase. Inicializa el objeto base y la colección de datos sobre los que se almacenará
     * información adicional
     *
     * @param objetoBase Objeto sobre el que se van a recopilar los datos
     */
    public DatoEstadistico(Object objetoBase) {
        this.objetoBase = objetoBase;

        datos = new HashMap<>();
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
        if (alias == null || valor == null)                         // Comprueba que ningún parámetro sea nulo
            return false;

        boolean existeDato = aliasEstaRegistrado(alias);

        if (!existeDato)                                            // Comprueba si el alias ya está registrado
            datos.put(alias, valor);                                // Si no lo está, registra el nuevo dato

        return !existeDato;
    }

    /**
     * Elimina de los datos registrados un dato
     *
     * @param alias Nombre del dato a eliminar
     * @return Booleano que indica si se ha podido eliminar el dato registrado. Devuelve Falso si el dato no existía o
     * el alias es nulo, Verdadero en otro caso
     */
    public boolean eliminarDato(String alias) {
        if (alias == null)                                          // Compruena que el alias no sea nulo
            return false;

        boolean existeDato = aliasEstaRegistrado(alias);

        if (existeDato)                                             // Comprueba si el alias está registrado
            datos.remove(alias);                                    // Si lo está, elimina el dato asociado

        return existeDato;
    }

    /**
     * Actualiza el valor de un dato
     *
     * @param alias Nombre descriptivo con el que se corresponde el dato
     * @param valor Nuevo valor
     * @return Booleano indicando si se pudo actualizar el valor del dato indicado. Devuelve Falso si el dato no existía
     * o cualquiera de los parámetros es nulo; Verdadero en otro caso
     */
    public boolean setValor(String alias, Object valor) {
        if (alias == null || valor == null)                         // Comprueba que ningún parámetro sea nulo
            return false;

        boolean existeDato = aliasEstaRegistrado(alias);

        if (existeDato)                                             // Comprueba si el alias está registrado
            datos.put(alias, valor);                                // Si lo está actualiza el valor del dato asociado

        return existeDato;
    }

    /**
     * Devuelve el valor asociado a un dato concreto
     *
     * @param alias Nombre descriptivo con el que se corresponde el dato
     * @return El dato requerido o nulo si no existe o es nulo
     */
    public Object getValor(String alias) {
        return datos.get(alias);
    }

    /**
     * Método accesor del atributo {@link DatoEstadistico#objetoBase}
     *
     * @return Objeto sobre el que se está recopilando información
     */
    public Object getObjetoBase() {
        return objetoBase;
    }

    /**
     * @return Set de cadenas con los alias de los datos registrados para el objeto base
     */
    public Set<String> recuperarListaDatos() {
        return datos.keySet();
    }

    /**
     * @param alias Alias a comprobar
     * @return Si el alias está asignado a un dato
     */
    private boolean aliasEstaRegistrado(String alias) {
        return datos.containsKey(alias);
    }

    /**
     * @return Cadena formateada de información del objeto base y una lista de datos registrados
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("DATO ESTADÍSTICO :\n");

        stringBuilder.append(getObjetoBase().toString());
        stringBuilder.append("\n\t").append("DATOS REGISTRADOS : ");
        stringBuilder.append("\n").append(recuperarListaDatos().toString());

        return stringBuilder.toString() + "\n";
    }

    /**
     * @param obj Objeto con el que se ha de comparar
     * @return Devuelve verdadero si entre el objeto base {@link DatoEstadistico#objetoBase} y {@code obj} hay coincidencia
     * si el objeto a comparar no es un dato estadístico, o si ambos son datos estadísticos y contienen el mismo objeto base.
     * Facilita la búsqueda de datos estadísticos equivalentes
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        DatoEstadistico objCasteado;
        boolean esMismoObjetoBase = false;

        try {
            objCasteado = (DatoEstadistico) obj;
            esMismoObjetoBase = getObjetoBase().equals(objCasteado.getObjetoBase());
        } catch (ClassCastException ignored) {
        }

        return getObjetoBase().equals(obj) || esMismoObjetoBase;
    }

    /**
     * @return Valor hashCode único de instancia. Basado en productos de números primos
     */
    @Override
    public int hashCode() {
        int hashCode = super.hashCode();                                  // Hash base
        int primo = 37;                                                   // Operador primo

        hashCode += primo * getObjetoBase().hashCode();
        hashCode += primo * recuperarListaDatos().hashCode();

        return hashCode;
    }

}