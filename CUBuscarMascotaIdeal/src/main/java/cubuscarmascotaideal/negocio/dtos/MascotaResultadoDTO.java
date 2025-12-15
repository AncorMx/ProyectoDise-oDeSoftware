package cubuscarmascotaideal.negocio.dtos;

/**
 * DTO que representa una mascota como resultado de búsqueda.
 * Incluye datos de la mascota y su porcentaje de compatibilidad.
 */
public class MascotaResultadoDTO {

    private String id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String tamano;
    private String nivelActividad;
    private boolean peludo;
    private String costoMantenimiento;
    private String descripcion;
    private String urlImagen;
    private double porcentajeCompatibilidad; // 0.0 - 100.0

    public MascotaResultadoDTO() {
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public double getPorcentajeCompatibilidad() {
        return porcentajeCompatibilidad;
    }

    public void setPorcentajeCompatibilidad(double porcentajeCompatibilidad) {
        this.porcentajeCompatibilidad = porcentajeCompatibilidad;
    }

    @Override
    public String toString() {
        return "MascotaResultadoDTO{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", porcentajeCompatibilidad=" + porcentajeCompatibilidad +
                '}';
    }
}
