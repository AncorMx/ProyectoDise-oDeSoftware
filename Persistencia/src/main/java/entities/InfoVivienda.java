/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Josel
 */
public class InfoVivienda {

    private String tipoVivienda;
    private String condicionesHogar;
    private boolean tieneOtrasMascotas;
    private boolean tieneNinos;
    private String tiempoDisponibilidad;
    private String urlImagenVivienda;

    public InfoVivienda() {
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String t) {
        this.tipoVivienda = t;
    }

    public String getCondicionesHogar() {
        return condicionesHogar;
    }

    public void setCondicionesHogar(String c) {
        this.condicionesHogar = c;
    }

    public boolean isTieneOtrasMascotas() {
        return tieneOtrasMascotas;
    }

    public void setTieneOtrasMascotas(boolean t) {
        this.tieneOtrasMascotas = t;
    }

    public boolean isTieneNinos() {
        return tieneNinos;
    }

    public void setTieneNinos(boolean t) {
        this.tieneNinos = t;
    }

    public String getTiempoDisponibilidad() {
        return tiempoDisponibilidad;
    }

    public void setTiempoDisponibilidad(String t) {
        this.tiempoDisponibilidad = t;
    }

    public String getUrlImagenVivienda() {
        return urlImagenVivienda;
    }

    public void setUrlImagenVivienda(String urlImagenVivienda) {
        this.urlImagenVivienda = urlImagenVivienda;
    }
}
