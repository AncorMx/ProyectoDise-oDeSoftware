package gestion.catalogo.dtos;

/**
 * DTO para transferir información de mascotas en el módulo de gestión de
 * catálogo.
 * Contiene solo los campos necesarios para las operaciones CRUD del admin.
 */
public class CatalogoDTO {

    private String id;
    private String nombre;
    private String especie;
    private int edad;
    private String estadoSalud;
    private String personalidad;
    private String urlImagen;
    private String estado;
    private boolean disponible;
    private String expedienteMedico;
    private String color;
    private String raza;
    private double peso;
    private ExpedienteMedicoTemporal datosExpediente;

    // Constructor vacío
    public CatalogoDTO() {
    }

    // Constructor completo
    public CatalogoDTO(String id, String nombre, String especie, int edad,
            String estadoSalud, String personalidad, String urlImagen,
            String estado, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.estadoSalud = estadoSalud;
        this.personalidad = personalidad;
        this.urlImagen = urlImagen;
        this.estado = estado;
        this.disponible = disponible;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public String getPersonalidad() {
        return personalidad;
    }

    public void setPersonalidad(String personalidad) {
        this.personalidad = personalidad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getExpedienteMedico() {
        return expedienteMedico;
    }

    public void setExpedienteMedico(String expedienteMedico) {
        this.expedienteMedico = expedienteMedico;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "CatalogoDTO{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", edad=" + edad +
                ", estado='" + estado + '\'' +
                ", disponible=" + disponible +
                '}';
    }

    public ExpedienteMedicoTemporal getDatosExpediente() {
        return datosExpediente;
    }

    public void setDatosExpediente(ExpedienteMedicoTemporal datosExpediente) {
        this.datosExpediente = datosExpediente;
    }
}
