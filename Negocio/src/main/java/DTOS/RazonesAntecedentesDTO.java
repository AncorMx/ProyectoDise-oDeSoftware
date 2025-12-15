/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

public class RazonesAntecedentesDTO {

    private String motivoAdopcion;
    private String antecedentesMascotas; // Historial previo
    private boolean aceptaSeguimiento;

    public RazonesAntecedentesDTO() {
    }

    // Getters y Setters
    public String getMotivoAdopcion() {
        return motivoAdopcion;
    }

    public void setMotivoAdopcion(String motivoAdopcion) {
        this.motivoAdopcion = motivoAdopcion;
    }

    public String getAntecedentesMascotas() {
        return antecedentesMascotas;
    }

    public void setAntecedentesMascotas(String antecedentesMascotas) {
        this.antecedentesMascotas = antecedentesMascotas;
    }

    public boolean isAceptaSeguimiento() {
        return aceptaSeguimiento;
    }

    public void setAceptaSeguimiento(boolean aceptaSeguimiento) {
        this.aceptaSeguimiento = aceptaSeguimiento;
    }
}
