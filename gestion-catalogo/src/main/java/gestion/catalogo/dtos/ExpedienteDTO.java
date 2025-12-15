package gestion.catalogo.dtos;

import java.time.LocalDateTime;

/**
 * DTO para el Expediente Médico de una mascota
 * El expediente médico tiene composición fuerte con la mascota en el módulo
 * gestion-catalogo
 * 
 * @author angel
 */
public class ExpedienteDTO {

    private String codigo; // Código único del expediente (EXP-YYYYMMDD-HHMMSS)
    private String idMascota; // ID de la mascota dueña del expediente

    // Campos coincidentes con ExpedienteMedico entidad
    private String condicion;
    private String nivelEnergia;
    private boolean vacunaRabia;
    private boolean vacunaDesparasitacionExterna;
    private boolean vacunaBordetella;
    private boolean vacunaDesparasitacionInterna;
    private boolean vacunaMultiple;
    private boolean esterilizado;
    private String alergias;
    private String condicionesEspeciales;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaUltimaActualizacion;

    public ExpedienteDTO() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    // Getters y Setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getNivelEnergia() {
        return nivelEnergia;
    }

    public void setNivelEnergia(String nivelEnergia) {
        this.nivelEnergia = nivelEnergia;
    }

    public boolean isVacunaRabia() {
        return vacunaRabia;
    }

    public void setVacunaRabia(boolean vacunaRabia) {
        this.vacunaRabia = vacunaRabia;
    }

    public boolean isVacunaDesparasitacionExterna() {
        return vacunaDesparasitacionExterna;
    }

    public void setVacunaDesparasitacionExterna(boolean vacunaDesparasitacionExterna) {
        this.vacunaDesparasitacionExterna = vacunaDesparasitacionExterna;
    }

    public boolean isVacunaBordetella() {
        return vacunaBordetella;
    }

    public void setVacunaBordetella(boolean vacunaBordetella) {
        this.vacunaBordetella = vacunaBordetella;
    }

    public boolean isVacunaDesparasitacionInterna() {
        return vacunaDesparasitacionInterna;
    }

    public void setVacunaDesparasitacionInterna(boolean vacunaDesparasitacionInterna) {
        this.vacunaDesparasitacionInterna = vacunaDesparasitacionInterna;
    }

    public boolean isVacunaMultiple() {
        return vacunaMultiple;
    }

    public void setVacunaMultiple(boolean vacunaMultiple) {
        this.vacunaMultiple = vacunaMultiple;
    }

    public boolean isEsterilizado() {
        return esterilizado;
    }

    public void setEsterilizado(boolean esterilizado) {
        this.esterilizado = esterilizado;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCondicionesEspeciales() {
        return condicionesEspeciales;
    }

    public void setCondicionesEspeciales(String condicionesEspeciales) {
        this.condicionesEspeciales = condicionesEspeciales;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(LocalDateTime fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    @Override
    public String toString() {
        return "ExpedienteDTO{" +
                "codigo='" + codigo + '\'' +
                ", idMascota='" + idMascota + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaUltimaActualizacion=" + fechaUltimaActualizacion +
                '}';
    }
}
