import java.io.File;
import java.util.*;

/**
 * Clase que implementa el proceso de simulación del caso de uso del proyecto. Realiza 10 turnos de pedidos tras la
 * inicialización del inventario y documenta el proceso mediante el fichero de registro
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
 * Curso : 2º GIIIS (Grupo A)
 */

public class Simulacion {

    private static final String RUTA_FICHERO_ENTRADA = "init.xml";          // Fichero XML de datos de entrada
    private static final int NUMERO_TURNOS = 10;                            // Número de turnos de la simulación

    private List<Cliente> clientesTurnos;                                   // Colección de clientes empleados en la simulacion

    /**
     * Constructor por defecto de la clase. Inicializa las estructuras de datos auxiliares
     */
    public Simulacion() {
        clientesTurnos = new ArrayList<>();
    }

    /**
     * PUNTO DE ENTRADA. Método principal del programa. Encargado de manejar el flujo de control del programa
     */
    public static void main(String[] args) {
        Simulacion simulacion = new Simulacion();                           // Instancia de la simulación

        try {
            simulacion.inicializarSimulacion();
            ejecutarTurnos(simulacion);
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
    private void inicializarSimulacion() throws ExcepcionCargaEntrada {
        // Carga los datos de entrada en el inventario
        CargadorInventario cargadorInventario = new CargadorInventario(new File(RUTA_FICHERO_ENTRADA));
        cargadorInventario.lecturaDatos();
        cargadorInventario.cargarDatos();

        // Recupera todos los clientes cargados y hasta NUM_TURNOS
        Iterator<Cliente> itClientes = cargadorInventario.getManejadorSAXParser().getIteradorClientesParseados();
        int clientesCargados = 0;
        while (itClientes.hasNext() && clientesCargados <= NUMERO_TURNOS) {
            clientesTurnos.add(itClientes.next());
            clientesCargados++;
        }

        // Si no había suficentes clientes, se insertan repetidos hasta NUM_TURNOS
        if (clientesCargados < NUMERO_TURNOS) {
            for (; clientesCargados < NUMERO_TURNOS; clientesCargados++) {
                clientesTurnos.add(clientesTurnos.get(NUMERO_TURNOS - clientesCargados));
            }
        }
    }

    /**
     * Lleva a cabo los turnos definidos por la simulación y generá el fichero de registro asociado
     *
     * @param simulacion Simulación que ejecutar
     */
    private static void ejecutarTurnos(Simulacion simulacion) {
        Set<Producto> prodRepuestos;                                    // Productos repuestos en cada turno
        for (Cliente turno : simulacion.clientesTurnos) {
            prodRepuestos = new HashSet<>();                            // Vacía los productos previos
            turno.realizarPedido(prodRepuestos);
        }
    }

}
