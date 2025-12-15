/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.adopcionesdto;

import DTOS.CitaDTO;

/**
 * Control para el flujo de citas
 * @author System
 */
public class ControlCita {
    
    private negocio.subsistemas.citas.ICitas fachadaCitas;
    
    public ControlCita() {
        this.fachadaCitas = new negocio.subsistemas.citas.FachadaCitas();
    }
    
    public void agendarCita(CitaDTO cita, String correoUsuario) throws Exception {
        if (cita != null && correoUsuario != null) {
            fachadaCitas.agendarCita(cita);
            // Aquí se podría integrar un servicio de notificaciones
            System.out.println("Cita agendada para: " + correoUsuario);
            System.out.println("Fecha: " + cita.getFechaHora());
        } else {
            throw new Exception("Datos de cita inválidos");
        }
    }
}
