package Identificadores;

/**
 * Clase que modeliza un generador de identificadores en secuencia consecutiva. Permite la generación de identificadores
 * únicos en el ámbito de ejecución del programa. Para garantizar la unicidad de los identificadores se ha implementado
 * según el patrón de diseño Singleton para que solo exista una única instancia generando identificadores.
 * <p>
 * Por ejemplo, para un identificador de longitud 5, existen 36^5 = 60.466.176 identificadores distintos
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class GeneradorIdentificador {

    private static final int VALOR_BASE_GENERADOR_IDENTIFICADOR = 2085497;      // Valor base estándar
    private static final int INCREMENTO_GENERADOR_IDENTIFICADOR = 1;            // Incremento estándar

    // Instancia Singleton de la clase
    private static GeneradorIdentificador instanciaActual = null;

    private Identificador valorBase;                                            // Identificador inicial de la secuencia
    private Identificador valorActual;                                          // Identificador actual de la secuancia sin utilizar
    private int incremento;                                                     // Incremento constante para generar el siguiente identificador

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
     * Devuelve la única instancia de la clase que existe. Sigue el patrón de diseño Singleton
     *
     * @return Única instancia de la clase {@code GeneradorIdentificador}
     */
    public static GeneradorIdentificador recuperarInstancia() {
        if (instanciaActual == null)
            instanciaActual = new GeneradorIdentificador(VALOR_BASE_GENERADOR_IDENTIFICADOR, INCREMENTO_GENERADOR_IDENTIFICADOR);

        return instanciaActual;
    }

    /**
     * Consulta la siguiente clave que se usará como identificador sin modificarla
     *
     * @return Identificador que representa el valor actual de la secuencia
     */
    public Identificador consultarIdentificador() {
        return valorActual;
    }

    /**
     * Devuelve un identificador único (no se repetirá a lo largo del ciclo de ejecución del programa).
     * Sucesivas llamadas a este métedo devolverán distintos indentificadores
     *
     * @return Identificador actual de la secuencia
     */
    public Identificador generarIdentificador() {
        Identificador sigIdentificador = new Identificador(valorActual);        // Identificador actual de la secuencia
        valorActual.incrementar(incremento);                                    // Nuevo identificador de la secuencia

        return sigIdentificador;
    }

}