package cuaceptarsolicitudes.persistencia.entities;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

/**
 * Entidad que representa una cita en el sistema.
 */
public class Cita {

    private ObjectId id;
    private ObjectId idUsuario;
    private ObjectId idMascota;
    private LocalDateTime fechaHora;
    private String estado;

    public Cita() {
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
