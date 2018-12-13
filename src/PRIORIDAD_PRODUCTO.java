/**
 * Tipo enumerado que contiene valores estandarizados de las distintas prioridades
 * de reabastecimiento de los productos. Se orgranizan en 3 valores, que son asignados
 * por constantes en la clase Producto.
 * <p>
 * Utilizado para determinar la instanciación de productos en la fase
 * de carga
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public enum PRIORIDAD_PRODUCTO {
    BAJA,
    MEDIA,
    ALTA,
    DEFAULT;

    /**
     * Convierte cadenas de texto en su constante enumerada equivalente
     *
     * @param categoria Cadena a evaluar
     * @return Prioridad correspondiente o DEFAULT si no hay coincidencia
     */
    public static PRIORIDAD_PRODUCTO toPrioridadProducto(String categoria) {
        switch (categoria) {
            case "BAJA":
                return BAJA;
            case "MEDIA":
                return MEDIA;
            case "ALTA":
                return ALTA;
            default:
                return DEFAULT;
        }
    }
}