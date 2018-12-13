/**
 * Tipo enumerado que contiene las subclases de clientes.
 * Utilizado para determinar la instanciación de productos en la fase
 * de carga
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public enum TIPOS_CLIENTE {
    ESTANDAR,
    VIP,
    DEFAULT;            // Tipo por defecto

    /**
     * Convierte cadenas de texto en su constante enumerada equivalente
     *
     * @param categoria Cadena a evaluar
     * @return Tipo cliente correspondiente o DEFAULT si no hay coincidencia
     */
    public static TIPOS_CLIENTE toTipoCliente(String categoria) {
        switch (categoria) {
            case "ESTANDAR":
                return ESTANDAR;
            case "VIP":
                return VIP;
            default:
                return DEFAULT;
        }
    }

}
