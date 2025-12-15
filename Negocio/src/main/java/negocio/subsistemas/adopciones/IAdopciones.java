/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio.subsistemas.adopciones;

import DTOS.SolicitudAdopcionDTO;
import java.util.List;

/**
 * Interfaz para el subsistema de adopciones
 * 
 * @author System
 */
public interface IAdopciones {

    void crearSolicitud(SolicitudAdopcionDTO solicitud) throws Exception;

    SolicitudAdopcionDTO buscarSolicitudPorId(String id) throws Exception;

    List<SolicitudAdopcionDTO> buscarSolicitudesPorUsuario(String idUsuario) throws Exception;

    void actualizarEstadoSolicitud(String id, String nuevoEstado) throws Exception;

}
