package gestion.catalogo.dtos;

/**
 * DTO para comunicar el resultado de operaciones CRUD.
 * Permite enviar mensajes estructurados desde la capa de control hacia la
 * presentación.
 */
public class ResultadoOperacion {

    private boolean exitoso;
    private String mensaje;
    private TipoMensaje tipo;
    private String idGenerado; // ID generado para entidades creadas

    public enum TipoMensaje {
        EXITO,
        ERROR,
        ADVERTENCIA,
        INFO
    }

    // Constructor vacío
    public ResultadoOperacion() {
    }

    // Constructor completo
    public ResultadoOperacion(boolean exitoso, String mensaje, TipoMensaje tipo) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    // Métodos de fábrica para crear resultados comunes
    public static ResultadoOperacion exito(String mensaje) {
        return new ResultadoOperacion(true, mensaje, TipoMensaje.EXITO);
    }

    public static ResultadoOperacion exitoConId(String mensaje, String idGenerado) {
        ResultadoOperacion resultado = new ResultadoOperacion(true, mensaje, TipoMensaje.EXITO);
        resultado.idGenerado = idGenerado;
        return resultado;
    }

    public static ResultadoOperacion error(String mensaje) {
        return new ResultadoOperacion(false, mensaje, TipoMensaje.ERROR);
    }

    public static ResultadoOperacion advertencia(String mensaje) {
        return new ResultadoOperacion(true, mensaje, TipoMensaje.ADVERTENCIA);
    }

    // Getters y Setters
    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public TipoMensaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoMensaje tipo) {
        this.tipo = tipo;
    }

    public String getIdGenerado() {
        return idGenerado;
    }

    public void setIdGenerado(String idGenerado) {
        this.idGenerado = idGenerado;
    }

    @Override
    public String toString() {
        return "ResultadoOperacion{" +
                "exitoso=" + exitoso +
                ", mensaje='" + mensaje + '\'' +
                ", tipo=" + tipo +
                ", idGenerado='" + idGenerado + '\'' +
                '}';
    }
}
