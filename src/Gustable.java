/**
 * Interfaz para aquellos productos que permitan likes
 */
public interface Gustable {

    /**
     * Devuelve el número de likes de un determinado producto
     *
     * @return Número de likes que tiene el producto
     */
    int getLikes();

    /**
     * Aumenta en una unidad el número de likes de un determinado producto
     */
    void like();

    /**
     * Disminuye en una unidad el número de likes de un determinado producto
     */
    void unlike();

}