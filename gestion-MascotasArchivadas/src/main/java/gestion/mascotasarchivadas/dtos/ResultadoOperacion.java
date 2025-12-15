package gestion.mascotasarchivadas.dtos;

/**
 * DTO para encapsular el resultado de operaciones del módulo.
 * Facilita la comunicación de estados de éxito/error a la capa de presentación.
 */
public class ResultadoOperacion {

    private boolean exitoso;
    private String mensaje;

    /**
     * Constructor por defecto.
     */
    public ResultadoOperacion() {
    }

    /**
     * Constructor con parámetros.
     * 
     * @param exitoso Indica si la operación fue exitosa.
     * @param mensaje Mensaje descriptivo del resultado.
     */
    public ResultadoOperacion(boolean exitoso, String mensaje) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
    }

    // Métodos de fábrica para crear resultados comunes
    /**
     * Crea un resultado de operación exitoso.
     * 
     * @param mensaje Mensaje de éxito.
     * @return Instancia de ResultadoOperacion con estado exitoso.
     */
    public static ResultadoOperacion exito(String mensaje) {
        return new ResultadoOperacion(true, mensaje);
    }

    /**
     * Crea un resultado de operación fallido.
     * 
     * @param mensaje Mensaje de error.
     * @return Instancia de ResultadoOperacion con estado fallido.
     */
    public static ResultadoOperacion error(String mensaje) {
        return new ResultadoOperacion(false, mensaje);
    }

    // Getters y Setters
    /**
     * Verifica si la operación fue exitosa.
     * 
     * @return true si fue exitosa, false en caso contrario.
     */
    public boolean isExitoso() {
        return exitoso;
    }

    /**
     * Establece el estado de éxito de la operación.
     * 
     * @param exitoso true si fue exitosa, false en caso contrario.
     */
    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    /**
     * Obtiene el mensaje del resultado.
     * 
     * @return El mensaje.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje del resultado.
     * 
     * @param mensaje El mensaje a establecer.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "ResultadoOperacion{" +
                "exitoso=" + exitoso +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
