/**
 * Tipo enumerado que contiene los diferentes códigos de error utilizados por el programa
 *
 * SUMARIO DE CÓDIGOS DE ERROR
 * <ul>
 * <li>{@code CARGA_CORRECTA} indica una carga correcta</li>
 * <li>{@code CONFIGURACION_FALLIDA} indica que ocurrió un error al configurar el parseador</li>
 * <li>{@code PARSEADO_FALLIDO} indica que no se pudo parsear el fichero de entrada</li>
 * <li>{@code FICHERO_NO_ENCONTRADO} indica que no se pudo encontrar el fichero de entrada</li>
 * <li>{@code XML_INVALIDO} indica que el fichero XML de entrada no cumple la validación</li>
 * <li>{@code CARGA_PRODUCTO_FALLIDA} indica que ocurrió un error al parsear un producto</li>
 * <li>{@code CARGA_CLIENTE_FALLIDA} indica que ocurrió un error al parsear un cliente</li>
 * <li>{@code CARGA_PRODUCTO_FAV_FALLIDA} indica que ocurrió al cargar un producto en el inventario</li>
 * <li>{@code PRODUCTO_MALFORMADO} indica que ocurrió al cargar un cliente en el inventario</li>
 * <li>{@code CLIENTE_MALFORMADO} indica que ocurrió al añadir un producto favorito a un cliente</li>
 * </ul>
 *
 * @author : Juan Pablo García Plaza Pérez
@author Jose Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public enum COD_ERROR {
    CARGA_CORRECTA,
    CONFIGURACION_FALLIDA,
    PARSEADO_FALLIDO,
    FICHERO_NO_ENCONTRADO,
    XML_INVALIDO,
    CARGA_PRODUCTO_FALLIDA,
    CARGA_CLIENTE_FALLIDA,
    CARGA_PRODUCTO_FAV_FALLIDA,
    PRODUCTO_MALFORMADO,
    CLIENTE_MALFORMADO
}
