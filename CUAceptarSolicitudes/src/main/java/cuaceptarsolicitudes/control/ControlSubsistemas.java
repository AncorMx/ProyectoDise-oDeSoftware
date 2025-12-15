package cuaceptarsolicitudes.control;

import cuaceptarsolicitudes.negocio.dtos.SolicitudDTO;
import cuaceptarsolicitudes.negocio.subsistema.ISeleccionarOpcion;
import cuaceptarsolicitudes.negocio.subsistema.FachadaSeleccionarOpcion;

import java.util.List;

/**
 * Controlador de subsistemas para el módulo de aceptar solicitudes.
 * Coordina la lógica de negocio y gestiona instancias de subsistemas.
 */
public class ControlSubsistemas {

    private final ISeleccionarOpcion seleccionarOpcion;

    public ControlSubsistemas() {
        this.seleccionarOpcion = new FachadaSeleccionarOpcion();
    }

    public ControlSubsistemas(ISeleccionarOpcion seleccionarOpcion) {
        this.seleccionarOpcion = seleccionarOpcion;
    }

    /**
     * Obtiene todas las solicitudes de adopción del sistema.
     * 
     * @return Lista de solicitudes
     */
    public List<SolicitudDTO> obtenerSolicitudes() {
        return seleccionarOpcion.obtenerTodasLasSolicitudes();
    }

    /**
     * Acepta una solicitud de adopción.
     * 
     * @param idSolicitud ID de la solicitud
     * @return true si se aceptó exitosamente
     * @throws Exception si ocurre un error
     */
    public boolean aceptarSolicitud(String idSolicitud, String idAdmin) throws Exception {
        return seleccionarOpcion.aceptarSolicitud(idSolicitud, idAdmin);
    }

    /**
     * Rechaza una solicitud de adopción.
     * 
     * @param idSolicitud ID de la solicitud
     * @return true si se rechazó exitosamente
     * @throws Exception si ocurre un error
     */
    public boolean rechazarSolicitud(String idSolicitud, String idAdmin) throws Exception {
        return seleccionarOpcion.rechazarSolicitud(idSolicitud, idAdmin);
    }

    /**
     * Marca una solicitud como pendiente de modificación.
     * 
     * @param idSolicitud       ID de la solicitud
     * @param razonModificacion Razón de la modificación
     * @return true si se marcó exitosamente
     * @throws Exception si ocurre un error
     */
    public boolean modificarSolicitud(String idSolicitud, String idAdmin, String razonModificacion) throws Exception {
        return seleccionarOpcion.modificarSolicitud(idSolicitud, idAdmin, razonModificacion);
    }
}
