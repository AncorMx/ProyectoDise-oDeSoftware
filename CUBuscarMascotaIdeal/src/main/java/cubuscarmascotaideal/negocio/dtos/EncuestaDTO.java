package cubuscarmascotaideal.negocio.dtos;

/**
 * DTO para las preferencias de encuesta del usuario.
 * Captura los criterios de búsqueda para encontrar mascotas compatibles.
 */
public class EncuestaDTO {

    private String tamano; // pequeño, mediano, grande
    private String nivelActividad; // bajo, medio, alto
    private boolean peludo;
    private String costoMantenimiento; // bajo, medio, alto
    private boolean tieneAlergias; // si tiene alergias
    private boolean viveEnDepartamento; // true = departamento, false = casa

    public EncuestaDTO() {
    }

    public EncuestaDTO(String tamano, String nivelActividad, boolean peludo, String costoMantenimiento,
            boolean tieneAlergias, boolean viveEnDepartamento) {
        this.tamano = tamano;
        this.nivelActividad = nivelActividad;
        this.peludo = peludo;
        this.costoMantenimiento = costoMantenimiento;
        this.tieneAlergias = tieneAlergias;
        this.viveEnDepartamento = viveEnDepartamento;
    }

    // Getters y Setters
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

    @Override
    public String toString() {
        return "EncuestaDTO{" +
                "tamano='" + tamano + '\'' +
                ", nivelActividad='" + nivelActividad + '\'' +
                ", peludo=" + peludo +
                ", costoMantenimiento='" + costoMantenimiento + '\'' +
                ", tieneAlergias=" + tieneAlergias +
                ", viveEnDepartamento=" + viveEnDepartamento +
                '}';
    }
}
