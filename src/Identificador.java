import java.util.Arrays;
import java.util.Stack;

/**
 * Clase que representa un nuevo sistema de numeración en base BASE_IDENTIFICADOR (es una biyección entre el sistema decimal y el nuevo sistema).
 * Su propósito es generar cadenas alfanuméricas aparentemente aleatorias que asignar como identificadores a las clases Cliente y Producto.
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class Identificador {

    private static final int BASE_IDENTIFICADOR = 36;                   // Base del sistema de numeración de identificadores

    // Vector de los valores asociado a cada peso del sistema de numeración en base BASE_IDENTIFICADOR
    private static final Character[] pesos = {'E', 'X', 'L', '7', 'B', 'F', '1', 'K', '5', 'Y', '9', 'C', '3', 'R',
            '6', 'Z', 'Q', 'A', 'S', 'J', 'U', 'G', 'W', 'N', 'T', 'P', '0', 'V', '2', 'M', '4', 'I', 'O', 'D', '8', 'H'};
    private String valor;                                               // Valor envuelto en la clase

    /**
     * Constructor parametrizado de la clase. Genera un identificador a partir de un entero decimal
     *
     * @param valor Entero a convertir en identificador
     */
    public Identificador(int valor) {
        this.valor = aCadena(valor);                                    // Calcula la cadena de identificador asociada al decimal
    }

    /**
     * Consulta el valor del identificador
     *
     * @return Cadena de texto representando una secuencia de identificador
     */
    public String valorDe() {
        return valor;
    }

    /**
     * Convierte la cadena de identificador a su correspondiente entero que la generó
     *
     * @return Entero decimal del que se generó el identificador
     */
    public int aDecimal() {
        char[] arrValor = valor.toCharArray();                          // Vector de caracteres que representa al identificador
        // Conversión del vector de pesos a cadena para poder hallar el valor numérico de base asociado a cada caracter
        String cadenaPesos = Arrays.toString(pesos).replaceAll(",\\s", "").substring(1, BASE_IDENTIFICADOR + 1);
        int contadorPeso = 0;                                           // Contador del exponente de la base, peso de cada dígito en base BASE_IDENTIFICADOR
        int resultado = 0;                                              // Resultado de la conversión

        // Realiza la conversión de identificador a su decimal asociado empezando por el dígito de menor peso
        for (int i = arrValor.length - 1; i >= 0; i--) {
            resultado += cadenaPesos.indexOf(arrValor[i]) * Math.pow(BASE_IDENTIFICADOR, contadorPeso);
            contadorPeso++;
        }

        return resultado;
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
        StringBuilder identificadorConvertido = new StringBuilder();      // Cadena resultante de la conversión

        while (numero >= BASE_IDENTIFICADOR) {
            digitosInvertidos.add(pesos[numero % BASE_IDENTIFICADOR]);    // Almacena los restos de cada división (nuevo dígito en base BASE_IDENTIFICADOR)
            numero /= BASE_IDENTIFICADOR;
        }
        digitosInvertidos.add(pesos[numero]);                             // Almacena el último cociente

        // Invierte los dígitos calculados y forma la cadena resultado
        while (!digitosInvertidos.empty()) {
            identificadorConvertido.append(digitosInvertidos.pop());      // Concatena cada dígito de la base de los identificadores a la cadena resultado
        }

        return identificadorConvertido.toString();
    }
}