package cubuscarmascotaideal.persistencia.entities;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

/**
 * Entity para persistir las respuestas de la encuesta del usuario.
 * Guarda las preferencias seleccionadas para busqueda de mascota ideal.
 */
public class Encuesta {

    private ObjectId id;
    private ObjectId idUsuario;
    private String tamano; // pequeño, mediano, grande
    private String nivelActividad; // bajo, medio, alto
    private boolean peludo;
    private String costoMantenimiento; // bajo, medio, alto
    private boolean tieneAlergias;
    private boolean viveEnDepartamento;
    private LocalDateTime fechaRespuesta;

    public Encuesta() {
    }

    public Encuesta(ObjectId idUsuario, String tamano, String nivelActividad, boolean peludo,
            String costoMantenimiento, boolean tieneAlergias, boolean viveEnDepartamento) {
        this.idUsuario = idUsuario;
        this.tamano = tamano;
        this.nivelActividad = nivelActividad;
        this.peludo = peludo;
        this.costoMantenimiento = costoMantenimiento;
        this.tieneAlergias = tieneAlergias;
        this.viveEnDepartamento = viveEnDepartamento;
        this.fechaRespuesta = LocalDateTime.now();
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

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getNivelActividad() {
        return nivelActividad;
    }

    public void setNivelActividad(String nivelActividad) {
        this.nivelActividad = nivelActividad;
    }

    public boolean isPeludo() {
        return peludo;
    }

    public void setPeludo(boolean peludo) {
        this.peludo = peludo;
    }

    public String getCostoMantenimiento() {
        return costoMantenimiento;
    }

    public void setCostoMantenimiento(String costoMantenimiento) {
        this.costoMantenimiento = costoMantenimiento;
    }

    public boolean isTieneAlergias() {
        return tieneAlergias;
    }

    public void setTieneAlergias(boolean tieneAlergias) {
        this.tieneAlergias = tieneAlergias;
    }

    public boolean isViveEnDepartamento() {
        return viveEnDepartamento;
    }

    public void setViveEnDepartamento(boolean viveEnDepartamento) {
        this.viveEnDepartamento = viveEnDepartamento;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }
}
