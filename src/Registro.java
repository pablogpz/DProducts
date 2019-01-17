/**
 * Clase que implementa el archivo de registro asociado a la simulación del proyecto. Genera un archivo de registro con
 * los datos que son registrados en la ruta especificada. Sigue el formato de la entrega
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class Registro {

    private static final String RUTA_REGISTRO = "registro.log";             // Ruta relativa del fichero de registro

    private StringBuilder contenido;                                        // Contenido del fichero de registro

    /**
     * Constructor por defecto. Inicializa el contenido del fichero de registro
     */
    public Registro() {
        contenido = new StringBuilder();
    }

    /**
     * Método accesor del atributo {@link Registro#contenido}
     *
     * @return {@code StringBuilder} representando el contenido del fichero de registro
     */
    public StringBuilder getContenido() {
        return contenido;
    }

}
