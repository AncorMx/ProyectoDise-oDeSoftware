package cuaceptarsolicitudes.negocio.BO;

import cuaceptarsolicitudes.negocio.dtos.SolicitudDTO;

/**
 * Valida las reglas de negocio para solicitudes de adopcion
 */
public class SolicitudBO implements ISolicitudBO {

    @Override
    public void validarSolicitud(SolicitudDTO solicitud) {
        // Verifico que la solicitud no sea nula
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }

        // Verifico que tenga un ID valido
        if (solicitud.getId() == null || solicitud.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID de la solicitud es obligatorio");
        }

        // Verifico que tenga un estado
        if (solicitud.getEstado() == null || solicitud.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de la solicitud es obligatorio");
        }
    }

    @Override
    public boolean puedeAceptarSolicitud(String idSolicitud) {
        if (idSolicitud == null || idSolicitud.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean puedeRechazarSolicitud(String idSolicitud) {
        if (idSolicitud == null || idSolicitud.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean puedeModificarSolicitud(String idSolicitud) {
        if (idSolicitud == null || idSolicitud.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
