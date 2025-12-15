package gestion.mascotasarchivadas.dtos;

/**
 * DTO para transferir información de mascotas archivadas.
 * Contiene los mismos campos que CatalogoDTO, adaptado para el contexto
 * de mascotas archivadas (estado = "baja").
 */
public class MascotaArchivoDTO {

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
    private String alergias;
    private String condicionesEspeciales;
    private String nivelEnergia;

    // Constructor vacío
    /**
     * Constructor por defecto.
     */
    public MascotaArchivoDTO() {
    }

    // Constructor completo
    /**
     * Constructor completo con todos los atributos principales.
     * 
     * @param id           Identificador único de la mascota.
     * @param nombre       Nombre de la mascota.
     * @param especie      Especie de la mascota.
     * @param edad         Edad de la mascota.
     * @param estadoSalud  Estado de salud.
     * @param personalidad Personalidad.
     * @param urlImagen    URL de la imagen.
     * @param estado       Estado actual (e.g., Baja, Adoptado).
     * @param disponible   Disponibilidad.
     */
    public MascotaArchivoDTO(String id, String nombre, String especie, int edad,
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
    /**
     * Obtiene el identificador de la mascota.
     * 
     * @return El identificador.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador de la mascota.
     * 
     * @param id El identificador a establecer.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la mascota.
     * 
     * @return El nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la mascota.
     * 
     * @param nombre El nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la especie de la mascota.
     * 
     * @return La especie.
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Establece la especie de la mascota.
     * 
     * @param especie La especie a establecer.
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * Obtiene la edad de la mascota.
     * 
     * @return La edad.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad de la mascota.
     * 
     * @param edad La edad a establecer.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el estado de salud de la mascota.
     * 
     * @return El estado de salud.
     */
    public String getEstadoSalud() {
        return estadoSalud;
    }

    /**
     * Establece el estado de salud de la mascota.
     * 
     * @param estadoSalud El estado de salud a establecer.
     */
    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    /**
     * Obtiene la personalidad de la mascota.
     * 
     * @return La personalidad.
     */
    public String getPersonalidad() {
        return personalidad;
    }

    /**
     * Establece la personalidad de la mascota.
     * 
     * @param personalidad La personalidad a establecer.
     */
    public void setPersonalidad(String personalidad) {
        this.personalidad = personalidad;
    }

    /**
     * Obtiene la URL de la imagen de la mascota.
     * 
     * @return La URL de la imagen.
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * Establece la URL de la imagen de la mascota.
     * 
     * @param urlImagen La URL de la imagen a establecer.
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    /**
     * Obtiene el estado actual de la mascota.
     * 
     * @return El estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la mascota.
     * 
     * @param estado El estado a establecer.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Indica si la mascota está disponible.
     * 
     * @return true si está disponible, false en caso contrario.
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Establece la disponibilidad de la mascota.
     * 
     * @param disponible true para disponible, false en caso contrario.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene el expediente médico de la mascota.
     * 
     * @return El expediente médico.
     */
    public String getExpedienteMedico() {
        return expedienteMedico;
    }

    /**
     * Establece el expediente médico de la mascota.
     * 
     * @param expedienteMedico El expediente médico a establecer.
     */
    public void setExpedienteMedico(String expedienteMedico) {
        this.expedienteMedico = expedienteMedico;
    }

    /**
     * Obtiene el color de la mascota.
     * 
     * @return El color.
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el color de la mascota.
     * 
     * @param color El color a establecer.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtiene la raza de la mascota.
     * 
     * @return La raza.
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Establece la raza de la mascota.
     * 
     * @param raza La raza a establecer.
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * Obtiene el peso de la mascota.
     * 
     * @return El peso.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Establece el peso de la mascota.
     * 
     * @param peso El peso a establecer.
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Obtiene las alergias de la mascota.
     * 
     * @return Las alergias.
     */
    public String getAlergias() {
        return alergias;
    }

    /**
     * Establece las alergias de la mascota.
     * 
     * @param alergias Las alergias a establecer.
     */
    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    /**
     * Obtiene las condiciones especiales de la mascota.
     * 
     * @return Las condiciones especiales.
     */
    public String getCondicionesEspeciales() {
        return condicionesEspeciales;
    }

    /**
     * Establece las condiciones especiales de la mascota.
     * 
     * @param condicionesEspeciales Las condiciones especiales a establecer.
     */
    public void setCondicionesEspeciales(String condicionesEspeciales) {
        this.condicionesEspeciales = condicionesEspeciales;
    }

    /**
     * Obtiene el nivel de energía de la mascota.
     * 
     * @return El nivel de energía.
     */
    public String getNivelEnergia() {
        return nivelEnergia;
    }

    /**
     * Establece el nivel de energía de la mascota.
     * 
     * @param nivelEnergia El nivel de energía a establecer.
     */
    public void setNivelEnergia(String nivelEnergia) {
        this.nivelEnergia = nivelEnergia;
    }

    @Override
    public String toString() {
        return "MascotaArchivoDTO{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", edad=" + edad +
                ", estado='" + estado + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
