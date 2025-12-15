package cubuscarmascotaideal.persistencia.entities;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

/**
 * Entity para persistir los resultados de busqueda de mascota ideal.
 * Asocia un usuario con una mascota compatible y su porcentaje.
 */
public class ResultadoMascotaIdeal {

    private ObjectId id;
    private ObjectId idUsuario;
    private ObjectId idMascota;
    private double porcentajeCompatibilidad;
    private LocalDateTime fechaBusqueda;

    public ResultadoMascotaIdeal() {
    }

    public ResultadoMascotaIdeal(ObjectId idUsuario, ObjectId idMascota, double porcentajeCompatibilidad) {
        this.idUsuario = idUsuario;
        this.idMascota = idMascota;
        this.porcentajeCompatibilidad = porcentajeCompatibilidad;
        this.fechaBusqueda = LocalDateTime.now();
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

    public double getPorcentajeCompatibilidad() {
        return porcentajeCompatibilidad;
    }

    public void setPorcentajeCompatibilidad(double porcentajeCompatibilidad) {
        this.porcentajeCompatibilidad = porcentajeCompatibilidad;
    }

    public LocalDateTime getFechaBusqueda() {
        return fechaBusqueda;
    }

    public void setFechaBusqueda(LocalDateTime fechaBusqueda) {
        this.fechaBusqueda = fechaBusqueda;
    }
}
