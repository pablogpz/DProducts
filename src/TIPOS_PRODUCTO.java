/**
 * Tipo enumerado que contiene las subclases de productos.
 * Utilizado para determinar la instanciación de productos en la fase
 * de carga
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public enum TIPOS_PRODUCTO {
    ALIMENTACION,
    HOGAR,
    OCIO,
    DEFAULT;            // Tipo por defecto

    /**
     * Convierte cadenas de texto en su constante enumerada equivalente
     *
     * @param categoria Cadena a evaluar
     * @return Tipo producto correspondiente o DEFAULT si no hay coincidencia
     */
    public static TIPOS_PRODUCTO toTipoProducto(String categoria) {
        switch (categoria) {
            case "ALIMENTACION":
                return ALIMENTACION;
            case "HOGAR":
                return HOGAR;
            case "OCIO":
                return OCIO;
            default:
                return DEFAULT;
        }
    }

}
