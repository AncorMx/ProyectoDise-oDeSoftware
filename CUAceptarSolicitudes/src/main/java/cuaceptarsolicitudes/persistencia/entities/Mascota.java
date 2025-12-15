package cuaceptarsolicitudes.persistencia.entities;

import org.bson.types.ObjectId;

/**
 * Entidad que representa una mascota en el sistema.
 */
public class Mascota {

    private ObjectId id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String sexo;
    private boolean disponible;
    private String estado;
    private String tamano;
    private boolean peludo;
    private String nivelActividad;
    private String costoMantenimiento;

    public Mascota() {
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public boolean isPeludo() {
        return peludo;
    }

    public void setPeludo(boolean peludo) {
        this.peludo = peludo;
    }

    public String getNivelActividad() {
        return nivelActividad;
    }

    public void setNivelActividad(String nivelActividad) {
        this.nivelActividad = nivelActividad;
    }

    public String getCostoMantenimiento() {
        return costoMantenimiento;
    }

    public void setCostoMantenimiento(String costoMantenimiento) {
        this.costoMantenimiento = costoMantenimiento;
    }
}
