package cuaceptarsolicitudes.negocio.BO;

import cuaceptarsolicitudes.negocio.dtos.SolicitudDTO;

/**
 * Interfaz para las validaciones de negocio de solicitudes de adopción.
 */
public interface ISolicitudBO {

    /**
     * Valida que una solicitud tenga los datos mínimos requeridos.
     * 
     * @param solicitud Solicitud a validar
     * @throws IllegalArgumentException si la validación falla
     */
    void validarSolicitud(SolicitudDTO solicitud);

    /**
     * Verifica si una solicitud puede ser aceptada.
     * 
     * @param idSolicitud ID de la solicitud
     * @return true si puede ser aceptada
     */
    boolean puedeAceptarSolicitud(String idSolicitud);

    /**
     * Verifica si una solicitud puede ser rechazada.
     * 
     * @param idSolicitud ID de la solicitud
     * @return true si puede ser rechazada
     */
    boolean puedeRechazarSolicitud(String idSolicitud);

    /**
     * Verifica si una solicitud puede ser modificada.
     * 
     * @param idSolicitud ID de la solicitud
     * @return true si puede ser modificada
     */
    boolean puedeModificarSolicitud(String idSolicitud);
}
