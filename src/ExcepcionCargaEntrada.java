/**
 * Excepción comprobada personalizada para manejar los distintos errores que se pueden producir al cargar
 * los datos de entrada del programa. Genera distintos comportamientos dependiendo del código de error que la
 * ocasionó
 * <p>
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 * @see COD_ERROR
 */

public class ExcepcionCargaEntrada extends Exception {

    // MENSAJES DE ERROR
    private static final String MSJ_CARGADOR_NO_INICIALIZADO = "ERROR al cargar los datos. No se ha leído de ninguna entrada";
    private static final String MSJ_CONFIGURACION_FALLIDA = "ERROR al construir el parseador XML";
    private static final String MSJ_FICHERO_NO_ENCONTRADO = "ERROR al abrir el fichero de datos de entrda. Compruebe la ruta el archivo y sus permisos";
    private static final String MSJ_XML_INVALIDO = "ERROR al validar el documento XML";
    private static final String MSJ_CARGA_PRODUCTO_FALLIDA = "ERROR. No se pudieron añadir todos los productos al inventario";
    private static final String MSJ_CARGA_CLIENTE_FALLIDA = "ERROR. No se pudieron añadir todos los clientes al inventario";
    private static final String MSJ_CARGA_PRODUCTO_FAV_FALLIDA = "ERROR. No se pudieron relacionar todos los productos favoritos con sus clientes";
    private static final String MSJ_PRODUCTO_MALFORMADO = "ERROR. No se pudo instanciar algún producto leído";
    private static final String MSJ_CLIENTE_MALFORMADO = "ERROR. No se pudo instanciar algún cliente leído";

    private COD_ERROR codigoError;                                  // Código de error que generó la excepción
    private String mensajeError;                                    // Mensaje de error que se le mostrará al usuario

    /**
     * Constructor parametrizado de la clase. Genera distintos mensajes de error según el códgo de error de
     * programa con el que se instancie
     *
     * @param error Código de error del evento que generó la excepción
     */
    public ExcepcionCargaEntrada(COD_ERROR error) {
        codigoError = error;
        switch (codigoError) {                                      // Determina el mensaje de error
            case CARGADOR_NO_INICIALIZADO:
                mensajeError = MSJ_CARGADOR_NO_INICIALIZADO;
                break;
            case CONFIGURACION_FALLIDA:
                mensajeError = MSJ_CONFIGURACION_FALLIDA;
                break;
            case FICHERO_NO_ENCONTRADO:
                mensajeError = MSJ_FICHERO_NO_ENCONTRADO;
                break;
            case XML_INVALIDO:
                mensajeError = MSJ_XML_INVALIDO;
                break;
            case CARGA_PRODUCTO_FALLIDA:
                mensajeError = MSJ_CARGA_PRODUCTO_FALLIDA;
                break;
            case CARGA_CLIENTE_FALLIDA:
                mensajeError = MSJ_CARGA_CLIENTE_FALLIDA;
                break;
            case CARGA_PRODUCTO_FAV_FALLIDA:
                mensajeError = MSJ_CARGA_PRODUCTO_FAV_FALLIDA;
                break;
            case PRODUCTO_MALFORMADO:
                mensajeError = MSJ_PRODUCTO_MALFORMADO;
                break;
            case CLIENTE_MALFORMADO:
                mensajeError = MSJ_CLIENTE_MALFORMADO;
                break;
        }
    }

    /**
     * Método accesor del atributo {@link ExcepcionCargaEntrada#codigoError}
     *
     * @return Código de error que generó la excepción
     */
    public COD_ERROR getCodigoError() {
        return codigoError;
    }

    /**
     * Método accesor del atributo {@link ExcepcionCargaEntrada#mensajeError}
     *
     * @return Mensaje de error adecuado al evento que lo ocasionó
     */
    public String getMensajeError() {
        return mensajeError + ". (Código de error : " + getCodigoError() + ")";
    }

}
