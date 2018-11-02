import java.util.Stack;

/**
 * TODO DESCRIPCION
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class Identificador {

    private static final int BASE_IDENTIFICADOR = 36;                   // Base del sistema de numeración de identificadores

    // Vector de los valores asociado a cada peso del sistema de numeración en base BASE_IDENTIFICADOR
    private static final Character[] pesos = {'X', 'E', 'L', '7', 'B', 'F', '1', 'K', '5', 'Y', '9', 'C', '3', 'R',
            '6', 'Z', 'Q', 'A', 'S', 'J', 'U', 'G', 'W', 'N', 'T', 'P', '0', 'V', '2', 'M', '4', 'I', 'O', 'D', '8', 'H'};
    private String valor;                                               // Valor envuelto en la clase

    /**
     * Constructor parametrizado de la clase. Genera un identificador a partir de un entero decimal
     *
     * @param valor Entero a convertir en identificador
     */
    public Identificador(int valor) {
        this.valor = aCadena(valor);
    }

    /**
     * Consulta el valor del identificador
     *
     * @return Cadena de texto representando una secuencia de identificador
     */
    public String valorDe() {
        // TODO - implement Identificador.valorDe
        return null;
    }

    /**
     * Convierte la cadena de identificador a su correspondiente entero que la generó
     *
     * @return Entero decimal del que se generó el identificador
     */
    public int aDecimal() {
        // TODO - implement Identificador.aDecimal
        return 0;
    }

    /**
     * Convierte un entero decimal a su equivalente valor alfanumérico en base BASE_IDENTIFICADOR,
     * que representa un identificador
     *
     * @param numero Entero decimal a convertir
     * @return Cadena representando el identificador
     */
    private String aCadena(int numero) {
        Stack<Character> digitosInvertidos = new Stack<>();               // Pila para invertir los dígitos calculados
        int cociente = numero / BASE_IDENTIFICADOR;                       // Indicador de fin del bucle while
        String identificadorConvertido = "";                              // Cadena resultante de la conversión

        while (cociente >= BASE_IDENTIFICADOR) {
            digitosInvertidos.add(pesos[numero % BASE_IDENTIFICADOR]);    // Almacena los restos de cada división (nuevo dígito en base BASE_IDENTIFICADOR)
            cociente = numero / BASE_IDENTIFICADOR;
        }
        digitosInvertidos.add(pesos[cociente]);                           // Almacena el último cociente

        // Invierte los dígitos calculados y forma la cadena resultado
        while (!digitosInvertidos.empty()) {
            identificadorConvertido += (digitosInvertidos.pop());         // Concatena cada dígito de la base de los identificadores a la cadena resultado
        }

        return identificadorConvertido;
    }
}