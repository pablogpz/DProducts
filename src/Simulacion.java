import java.io.File;

/**
 * Clase que implementa el proceso de simulación del caso de uso del proyecto. Realiza 10 turnos de pedidos tras la
 * inicialización del inventario y documenta el proceso mediante el fichero de registro 'registro.log'
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class Simulacion {

    private static final String RUTA_FICHERO_ENTRADA = "init.xml";          // Fichero XML de datos de entrada

    /**
     * PUNTO DE ENTRADA. Método principal del programa. Encargado de manejar el flujo de control del programa.
     */
    public static void main(String[] args) {
        try {
            inicializarSimulacion();
        } catch (ExcepcionCargaEntrada excepcionCargaEntrada) {
            System.out.println(excepcionCargaEntrada.getMensajeError());
        }
    }

    /**
     * Realiza las inicializaciones pertinentes antes de relaizar un turno. Lee los datos del fichero de entrada
     * {@link Simulacion#RUTA_FICHERO_ENTRADA} y carga en el inventario, dejándolo listo para recibir pedidos
     *
     * @throws ExcepcionCargaEntrada Si se produce algún error en la carga de datos
     */
    public static void inicializarSimulacion() throws ExcepcionCargaEntrada {
        CargadorInventario cargadorInventario = new CargadorInventario(new File(RUTA_FICHERO_ENTRADA));
        cargadorInventario.lecturaDatos();
        cargadorInventario.cargarDatos();
    }

}
