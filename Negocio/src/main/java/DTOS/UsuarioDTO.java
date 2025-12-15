/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

public class UsuarioDTO {

    private String id;
    private String contrasena; // Solo para login/registro

    // Composición: El usuario "TIENE" info personal y de vivienda
    private InfoPersonalDTO infoPersonal;
    private InfoViviendaDTO infoVivienda;

    public UsuarioDTO() {
        this.infoPersonal = new InfoPersonalDTO();
        this.infoVivienda = new InfoViviendaDTO();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public InfoPersonalDTO getInfoPersonal() {
        return infoPersonal;
    }

    public void setInfoPersonal(InfoPersonalDTO infoPersonal) {
        this.infoPersonal = infoPersonal;
    }

    public InfoViviendaDTO getInfoVivienda() {
        return infoVivienda;
    }

    public void setInfoVivienda(InfoViviendaDTO infoVivienda) {
        this.infoVivienda = infoVivienda;
    }
}
