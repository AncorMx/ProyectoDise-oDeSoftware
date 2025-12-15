package cuaceptarsolicitudes.negocio.dtos;

import java.time.LocalDateTime;

/**
 * DTO simplificado para solicitudes de adopción en el CU de aceptar
 * solicitudes.
 * Contiene la información necesaria para mostrar y procesar solicitudes.
 */
public class SolicitudDTO {

    private String id;
    private String nombreUsuario;
    private String correoUsuario;
    private String idUsuario;
    private String nombreMascota;
    private String idMascota;
    private String estado;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaCita;
    private String idCita;
    private String mensajeCorreccion;

    public SolicitudDTO() {
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getMensajeCorreccion() {
        return mensajeCorreccion;
    }

    public void setMensajeCorreccion(String mensajeCorreccion) {
        this.mensajeCorreccion = mensajeCorreccion;
    }

    @Override
    public String toString() {
        return "SolicitudDTO{" +
                "id='" + id + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", correoUsuario='" + correoUsuario + '\'' +
                ", nombreMascota='" + nombreMascota + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaSolicitud=" + fechaSolicitud +
                '}';
    }
}
