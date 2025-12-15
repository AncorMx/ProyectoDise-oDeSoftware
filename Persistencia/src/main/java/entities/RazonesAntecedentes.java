/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Josel
 */
public class RazonesAntecedentes {

    private String motivoAdopcion;
    private String antecedentesMascotas;
    private boolean aceptaSeguimiento;

    public RazonesAntecedentes() {
    }

    public String getMotivoAdopcion() {
        return motivoAdopcion;
    }

    public void setMotivoAdopcion(String m) {
        this.motivoAdopcion = m;
    }

    public String getAntecedentesMascotas() {
        return antecedentesMascotas;
    }

    public void setAntecedentesMascotas(String a) {
        this.antecedentesMascotas = a;
    }

    public boolean isAceptaSeguimiento() {
        return aceptaSeguimiento;
    }

    public void setAceptaSeguimiento(boolean a) {
        this.aceptaSeguimiento = a;
    }
}
