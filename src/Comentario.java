/**
 * Clase que da soporte a comentarios sobre productos. Los comentarios contienen el nombre completo de su autor,
 * una breve reseña sobre el producto y una calificiación en el rango [1,5]
 *
 * @author : Juan Pablo García Plaza Pérez
 * @author José Ángel Concha Carrasco
 * grupo : Wild True
 * Entrega : EC1
 * Curso : 2º GIIIS (Grupo A)
 */

public class Comentario {

    private Cliente autor;                                                  // Cliente que publica el comentario
    private String texto;                                                   // Cuerpo del comentario
    private int puntuacion;                                                 // Calificación que le dio el usuario. Es un valor en el rango [1,5]

    /**
     * Constructor parametrizado de la clase. Genera un comentario a partir de un autor, un cuerpo del comentario y una puntuación.
     * La puntuación debe estar entre 1 y 5 (ambos inclusive) y el cuerpo del comentario no puede estar vacío
     *
     * @param autor      Nombre completo del cliente que publica el comentario
     * @param texto      Cuerpo del comentario
     * @param puntuacion Calificación del producto. Es un valor en el rango [1,5]
     * @throws IllegalArgumentException Si cualquierda de los parámetros son inválidos
     */
    public Comentario(Cliente autor, String texto, int puntuacion) {
        if (!esCorrecto(texto, puntuacion))
            throw new IllegalArgumentException("ERROR al publicar un comentario." +
                    " Compruebe que el cuerpo del comentario contenga texto" +
                    " y que la puntuación esté en el rango [1,5]");

        this.autor = autor;
        this.texto = texto;
        this.puntuacion = puntuacion;
    }

    /**
     * Método accesor del atributo {@link Comentario#autor}
     *
     * @return Autor del comentario
     */
    public Cliente getAutor() {
        return autor;
    }

    /**
     * Método mutador del atributo {@link Comentario#autor}
     *
     * @param autor Nuevo autor del comentario
     */
    public void setAutor(Cliente autor) {
        this.autor = autor;
    }

    /**
     * Método accesor del atributo {@link Comentario#texto}
     *
     * @return Texto del comentario
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Método accesor del atributo {@link Comentario#puntuacion}
     *
     * @return Puntuación del comentario
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * @return Cadena de texto representando la puntuación del comentario mediante estrellas '*'
     */
    public String cadenaPuntuacion() {
        return "*****".substring(0, getPuntuacion());
    }

    /**
     * Método mutador del atributo {@link Comentario#texto}
     *
     * @param texto Nuevo texto del comentario. Debe ser correcto
     * @return Si el cambio fue aceptado
     */
    public boolean setTexto(String texto) {
        boolean esCorrecto = esCorrecto(texto, getPuntuacion());

        if (esCorrecto)
            this.texto = texto;

        return esCorrecto;
    }

    /**
     * Método mutador del atributo {@link Comentario#puntuacion}
     *
     * @param puntuacion Nueva puntuación del comentario. Debe ser correcto
     * @return Si el cambio fue aceptado
     */
    public boolean setPuntuacion(int puntuacion) {
        boolean esCorrecto = esCorrecto(getTexto(), puntuacion);

        if (esCorrecto)
            this.puntuacion = puntuacion;

        return esCorrecto;
    }

    /**
     * @param texto      Cuerpo del comentario. No puede estar vacío
     * @param puntuacion Valoración del comentario. Debe ser un valor en el rango [1,5]
     * @return Si es valido el comentario
     */
    private boolean esCorrecto(String texto, int puntuacion) {
        return texto.replaceAll("\\s+", "").length() != 0 &&
                puntuacion >= 1 && puntuacion <= 5;
    }

    /**
     * @return Cadena con el formato de una entrada de registro de comentario. Contiene toda la información base
     * de un comentario
     */
    public String aRegistro() {
        StringBuilder stringBuilder = new StringBuilder("comment:");

        stringBuilder.append(" ").append(getAutor().getNombre());
        stringBuilder.append(" ").append(getTexto());
        stringBuilder.append(" ").append(cadenaPuntuacion());

        return stringBuilder.toString();
    }

    /**
     * @return Cadena con el contenido del comentario formateado
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Autor : ").append(getAutor().toString()).append("\n");
        stringBuilder.append("Calificación ").append(cadenaPuntuacion()).append("\n");
        stringBuilder.append("\tReseña :\n").append(getTexto());

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
        if (!(obj instanceof Comentario)) return false;                    // Si pertenecen a la misma clase no procede

        Comentario objCasteado = (Comentario) obj;                          // Casteado del objeto

        return getAutor().equals(objCasteado.getAutor()) &&
                getPuntuacion() == objCasteado.getPuntuacion() &&
                getTexto().equals(objCasteado.getTexto());
    }

    /**
     * @return Valor hashCode único de instancia. Basado en productos de números primos
     */
    @Override
    public int hashCode() {
        int hashCode = super.hashCode();                                    // Hash base
        int primo = 37;                                                     // Operador primo

        hashCode += primo * getAutor().hashCode();
        hashCode += primo * getPuntuacion();
        hashCode += primo * getTexto().hashCode();

        return hashCode;
    }

}