package cuaceptarsolicitudes.persistencia.entities;

/**
 * Entidad embebida que representa la informacion de vivienda de un usuario.
 */
public class InfoVivienda {

    private String tipoVivienda;
    private boolean tienePatio;
    private boolean tieneOtrasMascotas;
    private String descripcionOtrasMascotas;

    public InfoVivienda() {
    }

    // Getters y Setters
    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public boolean isTienePatio() {
        return tienePatio;
    }

    public void setTienePatio(boolean tienePatio) {
        this.tienePatio = tienePatio;
    }

    public boolean isTieneOtrasMascotas() {
        return tieneOtrasMascotas;
    }

    public void setTieneOtrasMascotas(boolean tieneOtrasMascotas) {
        this.tieneOtrasMascotas = tieneOtrasMascotas;
    }

    public String getDescripcionOtrasMascotas() {
        return descripcionOtrasMascotas;
    }

    public void setDescripcionOtrasMascotas(String descripcionOtrasMascotas) {
        this.descripcionOtrasMascotas = descripcionOtrasMascotas;
    }
}
