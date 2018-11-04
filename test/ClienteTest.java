import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * CLASE DE TESTEO de la clase GestorStock.
 * <p>
 * Realiza las pruebas de todos los métodos públicos de la clase para todas
 * sus posibles entradas y estados
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

    // Fixture de objetos Cliente de prueba
    private static Cliente cliente;

    // Instancia Singleton del GestorStock
    private static GestorStock gestorStock;

    // Fixture de produtos de prueba
    private static Producto productoFavorito;
    private static Producto productoFavorito1;
    private static Producto productoNoFavorito;

    @BeforeClass
    public static void setUp() {
        cliente = new Cliente("Nombre", 18, "Localidad");
        gestorStock = GestorStock.recuperarInstancia();
    }

    @Test
    public void getNombre() {
    }

    @Test
    public void getIdentificador() {
    }

    @Test
    public void getEdad() {
    }

    @Test
    public void getLocalidad() {
    }

    @Test
    public void agregarFavorito() {
    }

    @Test
    public void eliminarFavorito() {
    }

    @Test
    public void pedirProducto() {
    }

    @Test
    public void pedirUnidadFavoritos() {
    }

    @Test
    public void comentarProducto() {
    }
}