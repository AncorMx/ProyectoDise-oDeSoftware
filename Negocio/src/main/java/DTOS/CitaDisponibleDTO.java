package DTOS;

import java.util.Date;

/**
 * DTO para representar una cita disponible
 * 
 * @author Sistema
 */
public class CitaDisponibleDTO {
    private String id;
    private Date fecha;
    private String hora;
    private boolean disponible;
    private String idUsuario;

    public CitaDisponibleDTO() {
    }

    public CitaDisponibleDTO(String id, Date fecha, String hora, boolean disponible) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.disponible = disponible;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "CitaDisponibleDTO{" +
                "id='" + id + '\'' +
                ", fecha=" + fecha +
                ", hora='" + hora + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
