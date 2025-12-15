package cubuscarmascotaideal.negocio.dtos;

/**
 * DTO que encapsula el resultado de una operación.
 * Indica si fue exitosa y proporciona un mensaje descriptivo.
 */
public class ResultadoOperacion {

    private boolean exitoso;
    private String mensaje;

    private ResultadoOperacion(boolean exitoso, String mensaje) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
    }

    /**
     * Crea un resultado exitoso
     */
    public static ResultadoOperacion exito(String mensaje) {
        return new ResultadoOperacion(true, mensaje);
    }

    /**
     * Crea un resultado de error
     */
    public static ResultadoOperacion error(String mensaje) {
        return new ResultadoOperacion(false, mensaje);
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return "ResultadoOperacion{" +
                "exitoso=" + exitoso +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
