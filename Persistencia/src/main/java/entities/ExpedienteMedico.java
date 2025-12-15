
package entities;

import org.bson.types.ObjectId;

/**
 * Entidad para representar un expediente médico de una mascota
 * Almacenado en la colección 'expedientes_medicos' de MongoDB
 * 
 * @author angel
 */
public class ExpedienteMedico {

    private ObjectId id;
    private ObjectId mascotaId;
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

    public ExpedienteMedico() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(ObjectId mascotaId) {
        this.mascotaId = mascotaId;
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
}