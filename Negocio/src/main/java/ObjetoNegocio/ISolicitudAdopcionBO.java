/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ObjetoNegocio;

import DTOS.MascotaDTO;
import DTOS.SolicitudAdopcionDTO;
import DTOS.UsuarioDTO;

/**
 *
 * @author Josel
 */
public interface ISolicitudAdopcionBO {

    void crearSolicitud(SolicitudAdopcionDTO solicitud);

    SolicitudAdopcionDTO buscarSolicitudPorId(String id);

    java.util.List<SolicitudAdopcionDTO> buscarSolicitudesPorUsuario(String idUsuario);

    void actualizarEstadoSolicitud(String id, String nuevoEstado);

    void registraRA(SolicitudAdopcionDTO solicitud, String razones, String antecedentes);

    UsuarioDTO obtieneUsuario(SolicitudAdopcionDTO solicitud);

    MascotaDTO obtieneMascota(SolicitudAdopcionDTO solicitud);
}
