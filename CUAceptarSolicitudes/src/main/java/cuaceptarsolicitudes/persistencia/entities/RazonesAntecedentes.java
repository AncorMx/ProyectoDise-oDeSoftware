package cuaceptarsolicitudes.persistencia.entities;

/**
 * Entidad embebida que representa razones y antecedentes del usuario.
 */
public class RazonesAntecedentes {

    private String razonAdopcion;
    private boolean tuvoMascotaAntes;
    private String experienciaMascotas;

    public RazonesAntecedentes() {
    }

    // Getters y Setters
    public String getRazonAdopcion() {
        return razonAdopcion;
    }

    public void setRazonAdopcion(String razonAdopcion) {
        this.razonAdopcion = razonAdopcion;
    }

    public boolean isTuvoMascotaAntes() {
        return tuvoMascotaAntes;
    }

    public void setTuvoMascotaAntes(boolean tuvoMascotaAntes) {
        this.tuvoMascotaAntes = tuvoMascotaAntes;
    }

    public String getExperienciaMascotas() {
        return experienciaMascotas;
    }

    public void setExperienciaMascotas(String experienciaMascotas) {
        this.experienciaMascotas = experienciaMascotas;
    }
}
