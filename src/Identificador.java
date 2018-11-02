public class Identificador {

    // Vector de los valores asociado a cada peso del sistema de numeración en base 36
    private static final Character[] pesos = {'X','E','L','7','B','F','1','K','5','Y','9','C','3','R',
            '6','Z','Q','A','S','J','U','G','W','N','T','P','0','V','2','M','4','I','O','D','8','H'};
    private String valor;                                               // Valor envuelto en la clase

    /**
     * Constructor parametrizado de la clase. Genera un identificador a partir de un entero decimal
     *
     * @param valor Entero a convertir en identificador
     */
    public Identificador(int valor) {
        // TODO - implement Identificador.Identificador
    }

    /**
     * Consulta el valor del identificador
     *
     * @return Cadena de texto representando una secuencia de identificador
     */
    public String valorDe() {
        // TODO - implement Identificador.valorDe
    }

    /**
     * Convierte la cadena de identificador a su correspondiente entero que la generó
     *
     * @return Entero decimal del que se generó el identificador
     */
    public int aDecimal() {
        // TODO - implement Identificador.aDecimal
    }

    /**
     * Convierte un entero decimal a su equivalente valor alfanumérico en base 36 que representa un identificador
     *
     * @param numero Entero decimal a convertir
     * @return Cadena representando el identificador
     */
    private String aCadena(int numero) {
        // TODO - implement Identificador.aCadena
    }
}