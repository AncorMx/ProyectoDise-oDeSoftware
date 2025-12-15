/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import org.bson.types.ObjectId;

/**
 *
 * @author Josel
 */
public class Usuario {

    private ObjectId id;
    private String contrasena;

    private InfoPersonal infoPersonal;
    private InfoVivienda infoVivienda;

    public Usuario() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
}
