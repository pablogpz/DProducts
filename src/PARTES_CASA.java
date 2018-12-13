/**
 * Tipo enumerado que contiene las posibles partes de la casa a la que puede
 * pertenecer un producto del hogar.
 * <p>
 * Utilizado para determinar la instanciación de productos en la fase
 * de carga
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public enum PARTES_CASA {
    SALA_ESTAR,
    COCINA,
    DORMITORIO,
    ASEO,
    JARDIN,
    DEFAULT;

    /**
     * Convierte cadenas de texto en su constante enumerada equivalente
     *
     * @param categoria Cadena a evaluar
     * @return Parte de la casa correspondiente o DEFAULT si no hay coincidencia
     */
    public static PARTES_CASA toParteCasa(String categoria) {
        switch (categoria) {
            case "SALA_ESTAR":
                return SALA_ESTAR;
            case "COCINA":
                return COCINA;
            case "DORMITORIO":
                return DORMITORIO;
            case "ASEO":
                return ASEO;
            case "JARDIN":
                return JARDIN;
            default:
                return DEFAULT;
        }
    }
}