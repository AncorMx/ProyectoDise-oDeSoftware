package cuaceptarsolicitudes.persistencia.entities;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

/**
 * Entidad que representa una solicitud de adopcion en el sistema.
 */
public class SolicitudAdopcion {

    private ObjectId id;
    private ObjectId idUsuario;
    private ObjectId idMascota;
    private ObjectId idCita;
    private String estado;
    private LocalDateTime fechaSolicitud;
    private String mensajeCorreccion;

    public SolicitudAdopcion() {
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(ObjectId idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ObjectId getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(ObjectId idMascota) {
        this.idMascota = idMascota;
    }

    public ObjectId getIdCita() {
        return idCita;
    }

    public void setIdCita(ObjectId idCita) {
        this.idCita = idCita;
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

    public String getMensajeCorreccion() {
        return mensajeCorreccion;
    }

    public void setMensajeCorreccion(String mensajeCorreccion) {
        this.mensajeCorreccion = mensajeCorreccion;
    }
}
