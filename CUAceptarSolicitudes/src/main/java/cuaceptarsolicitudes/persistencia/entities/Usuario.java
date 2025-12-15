package cuaceptarsolicitudes.persistencia.entities;

import org.bson.types.ObjectId;

/**
 * Entidad que representa un usuario en el sistema.
 */
public class Usuario {

    private ObjectId id;
    private InfoPersonal infoPersonal;
    private InfoVivienda infoVivienda;
    private RazonesAntecedentes razonesAntecedentes;

    public Usuario() {
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public InfoPersonal getInfoPersonal() {
        return infoPersonal;
    }

    public void setInfoPersonal(InfoPersonal infoPersonal) {
        this.infoPersonal = infoPersonal;
    }

    public InfoVivienda getInfoVivienda() {
        return infoVivienda;
    }

    public void setInfoVivienda(InfoVivienda infoVivienda) {
        this.infoVivienda = infoVivienda;
    }

    public RazonesAntecedentes getRazonesAntecedentes() {
        return razonesAntecedentes;
    }

    public void setRazonesAntecedentes(RazonesAntecedentes razonesAntecedentes) {
        this.razonesAntecedentes = razonesAntecedentes;
    }
}
