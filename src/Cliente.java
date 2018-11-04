import Identificadores.GeneradorIdentificador;
import Identificadores.Identificador;

import java.util.HashMap;
import java.util.Iterator;
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
 */

public class Cliente {

    private String nombre;                                              // Nombre completo del cliente
    private Identificador identificador;                                // Identificador único del cliente
    private int edad;                                                   // Edad actual del cliente
    private String localidad;                                           // Localidad de residencia del cliente
    private GestorStock tienda;                                         // Empresa a la que el cliente compra sus productos
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
        tienda = GestorStock.recuperarInstancia();
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
        if (agregarFavorito(producto.getIdentificador().valorDe(), alias)) {
            informarUsuario("El producto fue añadido a favortios con alias \"" + alias + "\"", producto);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Añade un producto a la colección de productos favoritos del cliente. No se puede añadir el mismo producto más de una vez
     * y el nombre con el que se guarda no puede estar repetido. El producto también debe estar en el inventario de la empresa
     * asociada al GestorStock
     *
     * @param identificador Identificador del producto a añadir a la colección de favoritos
     * @param alias         Nombre con el que recordar el producto favorito
     * @return Booleano indicando si se ha realizado correctamente la operación
     */
    public boolean agregarFavorito(String identificador, String alias) {
        if (existeFavorito(alias)) {                                    // Comprueba si el alias ya está en uso
            informarUsuario("ERROR al añadir un producto favorito. El alias \"" + alias + "\" ya está en uso");
            return false;                                               // El alias está en uso
        } else {
            Producto producto = tienda.recuperarProducto(identificador);
            if (producto != null) {                                     // Comprueba que el producto a añadir a favoritos exista en la empresa
                productosFavoritos.put(alias, producto);
                return true;                                            // Existe en el inventario de la empresa asociada
            } else {
                return false;                                           // No existe en el inventario de la empresa asociada
            }
        }
    }

    /**
     * Elimina un producto de la colección de favoritos
     *
     * @param alias Alias que se le puso al producto favorito cuando se agregó a la colección
     * @return Booleano si se ha podido eliminar el producto o no. Devuelve false si el producto no existe en la colección de favoritos
     */
    public boolean eliminarFavorito(String alias) {
        if (existeFavorito(alias)) {
            productosFavoritos.remove(alias);
            informarUsuario("El producto con alias \"" + alias +
                    "\" fue eliminado correctamente de la colección de productos favoritos");
            return true;
        } else {
            informarUsuario("ERROR al eliminar un producto favorito. El alias \"" + alias + "\" no existe");
            return false;
        }
    }

    /**
     * Realiza el pedido de una cantidad arbitraria de un producto favorito
     *
     * @param alias    Alias con el que se guardó el producto favorito
     * @param cantidad Unidades que contiene el pedido del producto dado
     * @return Booleano indicando si se pudo hacer el pedido. Devuelve falso si no se encontró el producto en la colección
     *      de favoritos, o si no hay suficiente cantidad en stock del producto para satisfacer el pedido (en cuyo caso no realiza el pedido)
     */
    public boolean pedirProducto(String alias, int cantidad) {
        if (existeFavorito(alias)) {                                    // Comprueba si existe el producto favorito
            Producto producto = recuperarFavorito(alias);
            if (tienda.venderProducto(cantidad, producto)) {            // Intenta despachar el pedido
                informarUsuario("Su pedido ha sido procesado. Cantidad : " + cantidad + " ud(s).", producto);
                return true;                                            // Pedido despachado
            } else {
                return false;                                           // No se pudo despachar el pedido
            }
        } else {
            informarUsuario("ERROR al pedir un producto favorito. El alias \"" + alias + "\" no existe");
            return false;                                               // No existe el producto favorito
        }
    }

    /**
     * Realiza el pedido de una unidad de todos los productos favoritos
     *
     * @return Booleano indicando si se pudo realizar el pedido. Devuelve falso si alguno de los productos no se encuentra
     *      en stock y no se realiza el pedido de ningún producto
     */
    public boolean pedirUnidadFavoritos() {
        Iterator<Producto> it = productosFavoritos.values().iterator();
        boolean faltaProducto = false;                                  // Bandera para indicar si servir el pedido

        while (it.hasNext() && !faltaProducto) {
            if (!it.next().haySuficienteStock(1)){              // Comprueba si ha en stock una unidad de cada producto
                faltaProducto = true;                                   // Existe suficiente stock de cada producto
            }
        }

        if (faltaProducto) {                                            // Comprueba si se pudo servir el pedido
            informarUsuario("ERROR al procesar el pedido de todos los productos favoritos. " +
                    "No hay stock de alguno de los productos que desea");
            return false;
        } else {
            for (String alias : productosFavoritos.keySet()) {
                pedirProducto(alias, 1);                        // Realiza el pedido de todos los productos favoritos
            }
            return true;
        }
    }

    /**
     * Publica un comentario sobre un producto. El producto debe estar entre los productos favoritos y un cliente solo puede
     * publicar un comentario sobre un producto
     *
     * @param alias      Alias con el que se guardó el producto en la colección de favoritos
     * @param texto      Cuerpo del comentario. No puede ser vacío
     * @param puntuacion Calificación del producto. Debe estar en el rango [1,5]
     * @return Booleano indicando si se pudo publicar el comentario. Devuelve falso si el cuerpo está vacío o si la
     *      puntuación no es válida
     */
    public boolean comentarProducto(String alias, String texto, int puntuacion) {
        if (existeFavorito(alias)) {                                    // Comprueba si existe el producto favorito
            if (texto.replaceAll("\\s+","").length() > 0 && (puntuacion > 0 && puntuacion < 6)) {
                Producto producto = recuperarFavorito(alias);
                if (tienda.comentarProducto(producto, new Comentario(this, texto, puntuacion))) {
                    informarUsuario("Se ha publicado un comentario", producto);
                    return true;                                        // El comentario es válido y fue publicado
                } else {
                    return false;                                       // El comentario no fue publicado
                }
            } else {
                informarUsuario("ERROR al publicar un comentario. " +
                        "Compruebe que el cuerpo del comentario contenga texto " +
                        "y que la puntuación esté en el rango [1,5]");
                return false;                                           // El comentario no es válido
            }
        } else {
            informarUsuario("ERROR al comentar un producto favorito. El alias \"" + alias + "\" no existe");
            return false;                                               // No existe el producto favorito
        }
    }

    /**
     * Comprueba si existe un determinado alias, y por tanto un producto favorito, en la colección de productos favoritos
     *
     * @param alias Alias con el que se guardó el producto favorito
     * @return Booleano indicando si el alias pertenece a algún producto favorito
     */
    private boolean existeFavorito(String alias) {
        return productosFavoritos.containsKey(alias);
    }

    /**
     * Recupera un producto favorito de la colección de productos favoritos
     *
     * @param alias Alias con el que se guardó el producto en la colección de favoritos
     * @return Producto favorito asociado al alias. Devuelve el valor null si el producto no pertenece a la colección
     *      de productos favoritos
     */
    private Producto recuperarFavorito(String alias) {
        if (existeFavorito(alias)) {                                    // Comprueba si el alias está asociado a algún producto
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
    private void informarUsuario(String mensaje, Producto productoRelacionado) {
        String mensajeProducto = productoRelacionado == null ? "" : "\nProducto : \n\t" +
                productoRelacionado.detalles();

        informarUsuario(mensaje + mensajeProducto);
    }

    /**
     * Informa por consola al usuario sobre el resultado de una determinada acción
     *
     * @param mensaje Cadena formateada al mostrar al usuario por consola
     */
    private void informarUsuario(String mensaje) {
        System.out.println(mensaje);
    }

}