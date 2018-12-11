import Identificadores.GeneradorIdentificador;
import Identificadores.Identificador;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que modela el comportamiento de clientes de empresas de compra/venta de productos. Los clientes se
 * identifican por un identificador único en el ciclo de vida del programa. Pueden realizar una serie de operaciones
 * sobre sus productos favoritos: añadir y eliminar productos a su colección de favoritos, realizar un pedido de cualquier
 * número de unidades de un producto favorito, realizar un pedido de una unidad de cada producto favorito y publicar un
 * comentario (solo uno) sobre sus productos favoritos
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public abstract class Cliente {

    private String nombre;                                              // Nombre completo del cliente
    private Identificador identificador;                                // Identificador único del cliente
    private int edad;                                                   // Edad actual del cliente
    private String localidad;                                           // Localidad de residencia del cliente
    private Inventario tienda;                                          // Empresa a la que el cliente compra sus productos
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
        tienda = Inventario.recuperarInstancia();
        productosFavoritos = new HashMap<>();
    }

    /**
     * Método accesor del atributo 'nombre'
     *
     * @return Nombre del cliente
     */
    protected String getNombre() {
        return nombre;
    }

    /**
     * Método accesor del atributo 'identificador'
     *
     * @return Identificador del cliente
     */
    protected Identificador getIdentificador() {
        return identificador;
    }

    /**
     * Método accesor del atributo 'edad'
     *
     * @return Edad del cliente
     */
    protected int getEdad() {
        return edad;
    }

    /**
     * Método accesor del atributo 'localidad'
     *
     * @return Localidad del cliente
     */
    protected String getLocalidad() {
        return localidad;
    }

    /**
     * Método accesor del atributo 'tienda'
     *
     * @return Empresa a la que compra el cliente
     */
    protected Inventario getTienda() {
        return tienda;
    }

    /**
     * Añade un producto a la colección de productos favoritos del cliente. No se puede añadir el mismo producto más de una vez
     * y el nombre con el que se guarda no puede estar repetido. El producto también debe estar en el inventario de la
     * empresa asociada al Inventario y no se puede añadir un producto a favoritos más de una vez
     *
     * @param producto Producto a añadir a la colección de favoritos
     * @param alias    Nombre con el que recordar el producto favorito
     * @return Booleano indicando si se ha realizado correctamente la operación.
     */
    public boolean agregarFavorito(Producto producto, String alias) {
        // TODO - mantener orden de la colección de favoritos
        boolean fueAgregado = false;

        // Comprueba si el producto ya estaba agregado a favoritos y no sea el producto "nulo" (válido en mapas)
        if (!existeProductoFavorito(producto) && producto != null) {
            try {
                fueAgregado = agregarFavorito(producto.getIdentificador(), alias);
            } catch (IllegalArgumentException e) {
                informarUsuario(e.getMessage());
                return false;
            }
        } else {
            informarUsuario("ERROR al añadir un producto a favoritos. El producto ya está en su colección de favoritos",
                    producto);
            return false;                                               // El producto ya estaba en favoritos
        }

        if (fueAgregado)
            informarUsuario("El producto fue añadido a favortios con alias \"" + alias + "\"", producto);
        else
            return false;                                               // El producto no era válido

        return true;                                                    // Se añade el produto a favoritos
    }

    /**
     * Añade un producto a la colección de productos favoritos del cliente. No se puede añadir el mismo producto más de una vez
     * y el nombre con el que se guarda no puede estar repetido. El producto también debe estar en el inventario de la empresa
     * asociada al Inventario
     *
     * @param identificador Identificador del producto a añadir a la colección de favoritos
     * @param alias         Nombre con el que recordar el producto favorito
     * @return Booleano indicando si se ha realizado correctamente la operación
     * @throws IllegalArgumentException Si el alias es nulo
     */
    public boolean agregarFavorito(Identificador identificador, String alias) {
        if (alias == null) throw new IllegalArgumentException("Alias nulo");

        if (existeAliasFavorito(alias)) {                               // Comprueba si el alias ya está en uso
            informarUsuario("ERROR al añadir un producto favorito. El alias \"" + alias + "\" ya está en uso");
        } else {
            Producto producto = getTienda().recuperarProducto(identificador);
            if (producto != null) {                                     // Comprueba que el producto a añadir a favoritos exista en la empresa
                productosFavoritos.put(alias, producto);
            } else {
                return false;                                           // No existe en el inventario de la empresa asociada
            }

            return true;                                                // Existe en el inventario de la empresa asociada
        }

        return false;                                                   // El alias está en uso
    }

    /**
     * Elimina un producto de la colección de favoritos
     *
     * @param alias Alias que se le puso al producto favorito cuando se agregó a la colección
     * @return Booleano si se ha podido eliminar el producto o no. Devuelve false si el producto no existe en la colección de favoritos
     */
    public boolean eliminarFavorito(String alias) {
        // TODO - Mantener el orden de la colección de favoritos
        if (existeAliasFavorito(alias)) {
            productosFavoritos.remove(alias);
            informarUsuario("El producto con alias \"" + alias +
                    "\" fue eliminado correctamente de la colección de productos favoritos");
        } else {
            informarUsuario("ERROR al eliminar un producto favorito. El alias \"" + alias + "\" no existe");
            return false;
        }

        return true;
    }

    /**
     * Procesa el pedido de un cliente
     *
     * @return Booleano indicando si se pudo hacer el pedido
     */
    // TODO - mover la implementación
    public abstract boolean realizarPedido();
//        if (existeAliasFavorito(alias)) {                               // Comprueba si existe el producto favorito
//            Producto producto = recuperarFavorito(alias);
//            if (getTienda().venderProducto(producto, cantidad)) {       // Intenta despachar el pedido
//                informarUsuario("Su pedido ha sido procesado. Cantidad : " + cantidad + " ud(s).", producto);
//            } else {
//                return false;                                           // No se pudo despachar el pedido
//            }
//        } else {
//            informarUsuario("ERROR al entregar un producto favorito. El alias \"" + alias + "\" no existe");
//            return false;                                               // No existe el producto favorito
//        }
//
//        return true;                                                    // Pedido despachado

    /**
     * Publica un comentario sobre un producto. El producto debe estar entre los productos favoritos y un cliente solo puede
     * publicar un comentario sobre un producto
     *
     * @param alias      Alias con el que se guardó el producto en la colección de favoritos
     * @param texto      Cuerpo del comentario. No puede ser vacío
     * @param puntuacion Calificación del producto. Debe estar en el rango [1,5]
     * @return Booleano indicando si se pudo publicar el comentario. Devuelve falso si el cuerpo está vacío o si la
     * puntuación no es válida
     */
    // TODO - mover la implementación
    public abstract boolean comentarProducto(String alias, String texto, int puntuacion);
//        Comentario comentario;
//
//        if (existeAliasFavorito(alias)) {                               // Comprueba si existe el producto favorito
//            Producto producto = recuperarFavorito(alias);               // Recupera la instancia del producto favorito
//
//            try {                                                       // Intenta crear el comentario
//                comentario = new Comentario(this, texto, puntuacion);
//            } catch (IllegalArgumentException e) {
//                informarUsuario(e.getMessage());
//                return false;                                           // El comentario no es válido
//            }
//
//            // Intenta publicar el comentario si el producto es comentable
//            try {
//                if (((ProductoComentable) producto).comentar(comentario))
//                    informarUsuario("Se ha publicado un comentario", producto);
//                else
//                    return false;                                      // El comentario no fue publicado
//            } catch (ClassCastException e) {
//                informarUsuario("No es posible comentar este producto. Clase (" + producto.getClass() +
//                        ") No es comentable ");
//                return false;
//            }
//
//            return true;                                                // El comentario es válido y fue publicado
//        } else {
//            informarUsuario("ERROR al comentar un producto favorito. El alias \"" + alias + "\" no existe");
//            return false;                                               // No existe el producto favorito
//        }

    /**
     * Comprueba si existe un determinado alias en la colección de productos favoritos
     *
     * @param alias Alias con el que se guardó el producto favorito
     * @return Booleano indicando si el alias pertenece a algún producto favorito
     */
    protected boolean existeAliasFavorito(String alias) {
        return productosFavoritos.containsKey(alias);
    }

    /**
     * Comprueba si existe un determinado producto en la colección de productos favoritos
     *
     * @param producto Producto que consultar si está añadido como favorito
     * @return Booleano indicando si el producto está en la colección de favoritos
     */
    protected boolean existeProductoFavorito(Producto producto) {
        return productosFavoritos.containsValue(producto);
    }

    /**
     * Comprueba si la colección de productos favoritos contiene algún producto
     *
     * @return Booleano indicando si hay algún produto favorito agregado
     */
    protected boolean estaVacioFavoritos() {
        return productosFavoritos.isEmpty();
    }

    /**
     * Recupera un producto favorito de la colección de productos favoritos
     *
     * @param alias Alias con el que se guardó el producto en la colección de favoritos
     * @return Producto favorito asociado al alias. Devuelve el valor null si el producto no pertenece a la colección
     * de productos favoritos
     */
    protected Producto recuperarFavorito(String alias) {
        if (existeAliasFavorito(alias)) {                               // Comprueba si el alias está asociado a algún producto
            return productosFavoritos.get(alias);                       // Devuelve el producto favorito
        } else {
            informarUsuario("ERROR al recuperar un producto favorito. " +
                    "El alias \"" + alias + "\" no está asociado a ningún producto favorito");
            return null;                                                // No existe ningún producto para el alias dado
        }
    }

    /**
     * Informa por consola al usuario sobre el resultado de una determinada acción
     *
     * @param mensaje             Cadena formateada al mostrar al usuario por consola
     * @param productoRelacionado Producto relacionado con el mensaje
     */
    protected void informarUsuario(String mensaje, Producto productoRelacionado) {
        String mensajeProducto = productoRelacionado == null ? "" : "\nProducto : \n\t" +
                productoRelacionado.toString();

        informarUsuario(mensaje + mensajeProducto);
    }

    /**
     * Informa por consola al usuario sobre el resultado de una determinada acción
     *
     * @param mensaje Cadena formateada al mostrar al usuario por consola
     */
    protected void informarUsuario(String mensaje) {
        System.out.println(mensaje);
    }

}