package entities;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 * Entidad que representa una cita disponible en el sistema
 * 
 * @author Sistema
 */
public class CitaDisponible {
    private ObjectId id;
    private Date fecha;
    private String hora;
    private boolean disponible;
    private ObjectId idUsuario; // Usuario que reservó la cita

    public CitaDisponible() {
        this.disponible = true;
    }

    public CitaDisponible(Date fecha, String hora) {
        this.fecha = fecha;
        this.hora = hora;
        this.disponible = true;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public ObjectId getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(ObjectId idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "CitaDisponible{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", hora='" + hora + '\'' +
                ", disponible=" + disponible +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
