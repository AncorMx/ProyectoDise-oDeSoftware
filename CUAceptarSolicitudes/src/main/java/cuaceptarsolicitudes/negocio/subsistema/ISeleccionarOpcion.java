package cuaceptarsolicitudes.negocio.subsistema;

import cuaceptarsolicitudes.negocio.dtos.SolicitudDTO;

import java.util.List;

/**
 * Interfaz del subsistema de selección de opciones para solicitudes de
 * adopción.
 * Define las operaciones principales: aceptar, rechazar y modificar
 * solicitudes.
 */
public interface ISeleccionarOpcion {

    /**
     * Obtiene todas las solicitudes de adopción del sistema.
     * 
     * @return Lista de todas las solicitudes
     */
    List<SolicitudDTO> obtenerTodasLasSolicitudes();

    /**
     * Acepta una solicitud de adopción.
     * Acepta la cita, marca mascota como adoptada, libera usuario, envía correo.
     * 
     * @param idSolicitud ID de la solicitud a aceptar
     * @param idAdmin     ID del administrador que acepta la solicitud
     * @return true si se aceptó exitosamente
     * @throws Exception si ocurre un error en el proceso
     */
    boolean aceptarSolicitud(String idSolicitud, String idAdmin) throws Exception;

    /**
     * Rechaza una solicitud de adopción.
     * Cancela solicitud, libera mascota y usuario, envía correo.
     * 
     * @param idSolicitud ID de la solicitud a rechazar
     * @param idAdmin     ID del administrador que rechaza la solicitud
     * @return true si se rechazó exitosamente
     * @throws Exception si ocurre un error en el proceso
     */
    boolean rechazarSolicitud(String idSolicitud, String idAdmin) throws Exception;

    /**
     * Pone una solicitud en estado pendiente de modificación.
     * El usuario debe editar la solicitud, se envía correo notificando.
     * 
     * @param idSolicitud       ID de la solicitud a modificar
     * @param idAdmin           ID del administrador que solicita la modificación
     * @param razonModificacion Razón por la cual se requiere modificación
     * @return true si se marcó como pendiente exitosamente
     * @throws Exception si ocurre un error en el proceso
     */
    boolean modificarSolicitud(String idSolicitud, String idAdmin, String razonModificacion) throws Exception;
}
