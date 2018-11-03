import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO DESCRIPCION
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

public class Cliente {

    private String nombre;                                              // Nombre completo del cliente
    private Identificador identificador;                                // Identificador único del cliente
    private int edad;                                                   // Edad actual del cliente
    private String localidad;                                           // Localidad de residencia del cliente
    private GestorStock empresaAsociada;                                // Empresa a la que el cliente compra sus productos
    private Map<String, Producto> productosFavoritos;                   // Colección de productos favoritos del cliente

    /**
     * Constructor parametrizado de la clase. Genera un cliente a partir de un nombre, una edad y una localidad de residencia
     *
     * @param nombre    Nombre completo del cliente
     * @param edad      Edad actual del cliente
     * @param localidad Nombre de la localidad de residencia del cliente
     */
    public Cliente(String nombre, int edad, String localidad) {
        this.nombre = nombre;
        this.edad = edad;
        this.localidad = localidad;

        identificador = GeneradorIdentificador.recuperarInstancia().generarIdentificador();
        empresaAsociada = GestorStock.recuperarInstancia();
        productosFavoritos = new HashMap<>();
    }

    /**
     * Método accesor del atributo 'nombre'
     *
     * @return Nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método accesor del atributo 'identificador'
     *
     * @return Identificador del cliente
     */
    public Identificador getIdentificador() {
        return identificador;
    }

    /**
     * Método accesor del atributo 'edad'
     *
     * @return Edad del cliente
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Método accesor del atributo 'localidad'
     *
     * @return Localidad del cliente
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Añade un producto a la colección de productos favoritos del cliente. No se puede añadir el mismo producto más de una vez
     * y el nombre con el que se guarda no puede estar repetido. El producto también debe estar en el inventario de la
     * empresa asociada al GestorStock
     *
     * @param producto Producto a añadir a la colección de favoritos
     * @param alias    Nombre con el que recordar el producto favorito
     * @return Booleano indicando si se ha realizado correctamente la operación.
     */
    public boolean agregarFavorito(Producto producto, String alias) {
        // TODO - implement Cliente.agregarFavorito
        return false;
    }

    /**
     * Añade un producto a la colección de productos favoritos del cliente. No se puede añadir el mismo producto más de una vez
     * y el nombre con el que se guarda no puede estar repetido. El producto también debe estar en el inventario de la empresa
     * asociada al GestorStock
     *
     * @param identificador Identificador del producto a añadir a la colección de favoritos
     * @param alias         Nombre con el que recordar el producto favorito
     * @return Booleano indicando si se ha realizado correctamente la operación.
     */
    public boolean agregarFavorito(String identificador, String alias) {
        // TODO - implement Cliente.agregarFavorito
        return false;
    }

    /**
     * Elimina un producto de la colección de favoritos
     *
     * @param alias Alias que se le puso al producto favorito cuando se agregó a la colección
     * @return Booleano si se ha podido eliminar el producto o no. Devuelve false si el producto no existe en la colección de favoritos
     */
    public boolean eliminarFavorito(String alias) {
        // TODO - implement Cliente.eliminarFavorito
        return false;
    }

    /**
     * Realiza el pedido de una cantidad arbitraria de un producto favorito
     *
     * @param alias Alias con el que se guardó el producto favorito
     * @return Booleano indicando si se pudo hacer el pedido. Devuelve falso si no se encontró el producto en la colección
     *      de favoritos, o si no hay suficiente cantidad en stock del producto para satisfacer el pedido (en cuyo caso no realiza el pedido)
     */
    public boolean pedirProducto(String alias) {
        // TODO - implement Cliente.pedirProducto
        return false;
    }

    /**
     * Realiza el pedido de una unidad de todos los productos favoritos
     *
     * @return Booleano indicando si se pudo realizar el pedido. Devuelve falso si alguno de los productos no se encuentra
     *      en stock y no se realiza el pedido de ningún producto
     */
    public boolean pedirUnidadFavoritos() {
        // TODO - implement Cliente.pedirUnidadFavoritos
        return false;
    }

    /**
     * Publica un comentario sobre un producto. El producto debe estar entre los productos favoritos y un cliente solo puede
     * publicar un comentario sobre un producto
     *
     * @param alias Alias con el que se guardó el producto en la colección de favoritos
     * @param texto Cuerpo del comentario. No puede ser vacío
     * @param puntuacion Calificación del producto. Debe estar en el rango [1,5]
     * @return Booleano indicando si se pudo publicar el comentario. Devuelve falso si el cuerpo está vacío o si la
     *      puntuación no es válida
     */
    public boolean comentarProducto(String alias, String texto, int puntuacion) {
        // TODO - implement Cliente.comentarProducto
        return false;
    }

    /**
     * Recupera un producto favorito de la colección de productos favoritos
     *
     * @param alias Alias con el que se guardó el producto en la colección de favoritos
     * @return Producto favorito asociado al alias. Devuelve el valor null si el producto no pertenece a la colección
     *      de productos favoritos
     */
    @Nullable
    private Producto recuperarFavorito(String alias) {
        // TODO - implement Cliente.recuperarFavorito
        return null;
    }

    /**
     * Informa por consola al usuario sobre el resultado de una determinada acción
     *
     * @param mensaje Cadena formateada al mostrar al usuario por consola
     */
    private void informarUsuario(String mensaje) {
        // TODO - implement Cliente.informarUsuario
    }

}