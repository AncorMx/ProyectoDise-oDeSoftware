/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class Cita {

    private ObjectId id;
    private LocalDateTime fechaHora;
    private ObjectId idUsuario;
    private ObjectId idMascota;

    public Cita() {
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime f) {
        this.fechaHora = f;
    }

    public ObjectId getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(ObjectId id) {
        this.idUsuario = id;
    }

    public ObjectId getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(ObjectId id) {
        this.idMascota = id;
    }
}
