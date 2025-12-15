/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.adopcionesdto;

import DTOS.SolicitudAdopcionDTO;

/**
 * Control para el flujo de adopciones
 * 
 * @author System
 */
public class ControlAdopcion {

    private negocio.subsistemas.adopciones.IAdopciones fachadaAdopciones;

    public ControlAdopcion() {
        this.fachadaAdopciones = new negocio.subsistemas.adopciones.FachadaAdopciones();
    }

    public void crearSolicitud(SolicitudAdopcionDTO solicitud) throws Exception {
        if (solicitud != null && solicitud.getMascota() != null) {
            fachadaAdopciones.crearSolicitud(solicitud);
            System.out.println("Solicitud de adopción creada para mascota: " + solicitud.getMascota().getId());
        } else {
            throw new Exception("Datos de solicitud inválidos");
        }
    }

    public java.util.List<SolicitudAdopcionDTO> buscarSolicitudesPorUsuario(String idUsuario) throws Exception {
        return fachadaAdopciones.buscarSolicitudesPorUsuario(idUsuario);
    }

    public void actualizarEstadoSolicitud(String idSolicitud, String nuevoEstado) throws Exception {
        fachadaAdopciones.actualizarEstadoSolicitud(idSolicitud, nuevoEstado);
    }

    public SolicitudAdopcionDTO buscarSolicitudPorId(String idSolicitud) throws Exception {
        return fachadaAdopciones.buscarSolicitudPorId(idSolicitud);
    }
}
