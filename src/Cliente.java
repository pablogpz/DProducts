import Identificadores.GeneradorIdentificador;
import Identificadores.Identificador;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Clase que modela el comportamiento base de clientes de empresas de compra/venta de productos. Los clientes se
 * identifican por un identificador único en el ciclo de vida del programa. Pueden realizar una serie de operaciones
 * sobre sus productos favoritos: añadir y eliminar productos a su colección de favoritos, realizar un pedido de cualquier
 * número de unidades de un producto favorito, realizar un pedido de una unidad de cada producto favorito y publicar un
 * comentario (solo uno) sobre sus productos favoritos
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC2
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
     * @param nombre    Nombre completo del cliente. No puede ser vacío
     * @param edad      Edad actual del cliente. Debe ser un natural
     * @param localidad Nombre de la localidad de residencia del cliente. No puede ser vacío
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     */
    public Cliente(String nombre, int edad, String localidad) {
        if (!esCorrecto(nombre, edad, localidad))
            throw new IllegalArgumentException("ERROR al crear un cliente. Compruebe que el ni el nombre ni la localidad sean" +
                    "vacíos, y que la edad sea un natural");

        this.nombre = nombre;
        this.edad = edad;
        this.localidad = localidad;

        identificador = GeneradorIdentificador.recuperarInstancia().generarIdentificador();
        tienda = Inventario.recuperarInstancia();
        productosFavoritos = new HashMap<>();
    }

    /**
     * Método accesor del atributo {@link Cliente#nombre}
     *
     * @return Nombre del cliente
     */
    protected String getNombre() {
        return nombre;
    }

    /**
     * Método accesor del atributo {@link Cliente#identificador}
     *
     * @return Identificador del cliente
     */
    protected Identificador getIdentificador() {
        return identificador;
    }

    /**
     * Método accesor del atributo {@link Cliente#edad}
     *
     * @return Edad del cliente
     */
    protected int getEdad() {
        return edad;
    }

    /**
     * Método accesor del atributo {@link Cliente#localidad}
     *
     * @return Localidad del cliente
     */
    protected String getLocalidad() {
        return localidad;
    }

    /**
     * Método accesor del atributo {@link Cliente#tienda}
     *
     * @return Empresa a la que compra el cliente
     */
    protected Inventario getTienda() {
        return tienda;
    }

    /**
     * @return Colección de productos favoritos
     */
    protected Collection<Producto> getProductosFavoritos() {
        return productosFavoritos.values();
    }

    /**
     * Método mutador del atributo {@link Cliente#nombre}
     *
     * @param nombre Nuevo nombre del cliente. Debe ser válido
     * @return Si se aceptaron los cambios
     */
    protected boolean setNombre(String nombre) {
        boolean esCorrecto = esCorrecto(nombre, getEdad(), getLocalidad());
        if (esCorrecto) {
            this.nombre = nombre;
        }
        return esCorrecto;
    }

    /**
     * Método mutador del atributo {@link Cliente#edad}
     *
     * @param edad Nueva edad del cliente. Debe ser válida
     */
    protected boolean setEdad(int edad) {
        boolean esCorrecto = esCorrecto(getNombre(), edad, getLocalidad());
        if (esCorrecto) {
            this.edad = edad;
        }
        return esCorrecto;
    }

    /**
     * Método mutador del atributo {@link Cliente#localidad}
     *
     * @param localidad Nueva residencia del cliente. Debe ser válida
     * @return Si se aceptaron los cambios
     */
    protected boolean setLocalidad(String localidad) {
        boolean esCorrecto = esCorrecto(getNombre(), getEdad(), localidad);
        if (esCorrecto) {
            this.localidad = localidad;
        }
        return esCorrecto;
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
     * @return Booleano si se ha podido eliminar el producto o no. Devuelve false si el producto no existe en la colección
     * de favoritos
     */
    public boolean eliminarFavorito(String alias) {
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
     * @param prodRepuestos Set de productos repuestos durante la venta
     * @return Booleano indicando el estado de la venta. Verdadero si el pedido pudo ser despachado y falso si
     * ocurrió algún error
     */
    public abstract boolean realizarPedido(Set<Producto> prodRepuestos);

    /**
     * Publica un comentario sobre un producto. El producto debe estar entre los productos favoritos y un cliente solo puede
     * publicar un comentario sobre un producto
     *
     * @param producto Producto favorito a comentar
     * @return Booleano indicando si se pudo publicar el comentario. Devuelve falso si el cuerpo está vacío o si la
     * puntuación no es válida
     */
    public abstract boolean comentarProducto(Producto producto);

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
        if (!existeAliasFavorito(alias)) {                              // Comprueba si el alias está asociado a algún producto
            informarUsuario("ERROR al recuperar un producto favorito. " +
                    "El alias \"" + alias + "\" no está asociado a ningún producto favorito");
            return null;                                                // No existe ningún producto para el alias dado
        }
        return productosFavoritos.get(alias);                           // Devuelve el producto favorito
    }

    /**
     * @param nombre    Nombre del cliente. No puede estar vacío
     * @param edad      Edad del cliente. Debe ser un natural
     * @param localidad Residencia del cliente. No puede estar vacío
     * @return Si los campos del cliente son válidos
     */
    private boolean esCorrecto(String nombre, int edad, String localidad) {
        return nombre.replaceAll("\\s+", "").length() != 0 &&
                edad > 0 &&
                localidad.replaceAll("\\s+", "").length() != 0;
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

    /**
     * @return Cadena con formato de entrada de registro de cliente
     */
    public String aRegistro() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(" ").append(getIdentificador());
        stringBuilder.append(" ").append(getNombre());
        stringBuilder.append(" ").append(getEdad());
        stringBuilder.append(" ").append(getLocalidad());

        return stringBuilder.toString();
    }

    /**
     * @return Cadena con el contenido base del cliente formateado (nombre, identificador, edad y localidad)
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("CLIENTE\t").append(getNombre());
        stringBuilder.append("\n\t").append("Identificador : ").append(getIdentificador().toString());
        stringBuilder.append("\n\t").append("Edad : ").append(getEdad());
        stringBuilder.append("\n\t").append("Localidad : ").append(getLocalidad());

        return stringBuilder.toString() + "\n";
    }

    /**
     * @param obj Objeto con el que comparar
     * @return Devuelve verdadero si entre esta instancia y {@code obj} hay coincidencia entre todos los atributos
     * y pertenecen a la misma clase
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                                       // Comprueba si es la misma instancia
        if (!(obj instanceof Cliente))
            return false;                       // si no pertenecen a la misma clase no procede

        Cliente objCasteado = (Cliente) obj;                                // Casteado del objeto

        return getNombre().equals(objCasteado.getNombre()) &&
                getIdentificador() == objCasteado.getIdentificador() &&
                getEdad() == objCasteado.getEdad() &&
                getLocalidad().equals(objCasteado.getLocalidad()) &&
                getTienda().equals(objCasteado.getTienda()) &&
                productosFavoritos.equals(objCasteado.productosFavoritos);
    }

    /**
     * @return Valor hashCode único de instancia. Basado en productos de números primos
     */
    @Override
    public int hashCode() {
        int hashCode = super.hashCode();                                    // HashBase
        int primo = 37;                                                     // Operador primo

        hashCode += primo * getNombre().hashCode();
        hashCode += primo * getIdentificador().hashCode();
        hashCode += primo * getEdad();
        hashCode += primo * getLocalidad().hashCode();
        hashCode += primo * getTienda().hashCode();
        hashCode += primo * productosFavoritos.hashCode();

        return hashCode;
    }

}