import java.util.EnumSet;
import java.util.Iterator;

/**
 * Tipo enumerado que contiene valores estandarizados de los principales fabricantes
 * de componentes de ordenadores y periféricos.
 * <p>
 * Utilizado para determinar la instanciación de productos en la fase
 * de carga
 *
 * @author : Juan Pablo García Plaza Pérez - Jose Ángel Concha Carrasco
 * @grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public enum FABRICANTES {
    ACER,
    AMD,
    AOC,
    AMAZON,
    ASROCK,
    ASUS,
    BEHRINGER,
    BENQ,
    BQ,
    CHERRY,
    COOLERMASTER,
    CORSAIR,
    CREATIVE,
    DELL,
    DXRACER,
    EVGA,
    GSKILL,
    GIGABYTE,
    GOOGLE,
    HP,
    HUAWEI,
    HYPERX,
    INTEL,
    KENSINGTON,
    LG,
    LOGITECH,
    MICROSOFT,
    MSI,
    NEWSKILL,
    NOCTUA,
    NINTENDO,
    NVIDIA,
    NZXT,
    OWLOTECH,
    PALIT,
    PHANTEKS,
    PHILIPS,
    RACER,
    SAMSUNG,
    SANDISK,
    SAPPHIRE,
    SEAGATE,
    SEASONIC,
    SENNHEISER,
    SILVERSTONE,
    STEELSERIES,
    TACENS,
    THERMALTAKE,
    TPLINK,
    TRANSCEND,
    TRITTON,
    TRUST,
    VIEWSONIC,
    WACOM,
    WESTERNDIGITAL,
    ZOTAC,
    ZOWIE,
    DEFAULT;

    /**
     * Convierte cadenas de texto en su constante enumerada equivalente
     *
     * @param categoria Cadena a evaluar
     * @return Fabricante correspondiente o DEFAULT si no hay coincidencia
     */
    public static FABRICANTES toFabricante(String categoria) {
        EnumSet<FABRICANTES> listaFabricantes = EnumSet.allOf(FABRICANTES.class);
        Iterator<FABRICANTES> iterator = listaFabricantes.iterator();
        FABRICANTES fabricante = DEFAULT;                   // Fabricante equivalente
        boolean encontrado = false;                         // Bandera de coincidencia

        while (iterator.hasNext() && !encontrado) {         // Recorre todos los valores del tipo enumerado
            fabricante = iterator.next();
            if (categoria.equals(fabricante.toString()))
                encontrado = true;                          // Hay coincidencia
        }

        return fabricante;
    }
}