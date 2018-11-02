/**
 * TODO DESCRIPCION
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class GeneradorIdentificador {

    private static final int VALOR_BASE_GENERADOR_IDENTIFICADOR = 2085497;
    private static final int INCREMENTO_GENERADOR_IDENTIFICADOR = 1;

    private static final GeneradorIdentificador instanciaActual =
            new GeneradorIdentificador(VALOR_BASE_GENERADOR_IDENTIFICADOR, INCREMENTO_GENERADOR_IDENTIFICADOR);
    private Identificador valorBase;
    private Identificador valorActual;
    private int incremento;

    /**
     * Constructor parametrizado de la clase. Sigue el patrón de diseño Singleton
     *
     * @param valorBase  Entero decimal que representa el punto de inicio de la secuencia de identificadores
     * @param incremento Entero que será el incremento constante que generará los distintos valores de la secuencia
     */
    private GeneradorIdentificador(int valorBase, int incremento) {
        this.valorBase = new Identificador(valorBase);                          // Genera el identificador base
        this.incremento = incremento;
        valorActual = this.valorBase;                                           // Inicializa la secuencia al valor base
    }

    /**
     * Consulta la siguiente clave que se usará como identificador sin modificarla
     *
     * @return Identificador que representa el valor actual de la secuencia
     */
    public Identificador consultarIdentificador() {
        // TODO - implement GeneradorIdentificador.consultarIdentificador
        return null;
    }

    /**
     * Devuelve la única instancia de la clase que existe. Sigue el patrón de diseño Singleton
     *
     * @return Única instancia de la clase GeneradorIdentificador
     */
    public GeneradorIdentificador recuperarInstancia() {
        // TODO - implement GeneradorIdentificador.recuperarInstancia
        return null;
    }

    /**
     * Devuelve un identificador único (no se repetirá a lo largo del ciclo de ejecución del programa).
     * Sucesivas llamadas a este métedo devolverán distintos indentificadores
     *
     * @return Identificador actual de la secuencia
     */
    public Identificador generarIdentificador() {
        // TODO - implement GeneradorIdentificador.generarIdentificador
        return null;
    }

}