package cuaceptarsolicitudes.negocio.subsistema;

import cuaceptarsolicitudes.negocio.dtos.SolicitudDTO;

import java.util.List;

/**
 * Fachada del subsistema de selección de opciones para solicitudes.
 * Proporciona acceso controlado al subsistema sin exponer su implementación.
 * Patrón Facade para simplificar el acceso desde la capa de control.
 */
public class FachadaSeleccionarOpcion implements ISeleccionarOpcion {

    private final ISeleccionarOpcion seleccionarOpcion;

    public FachadaSeleccionarOpcion() {
        this.seleccionarOpcion = new SeleccionarOpcion();
    }

    // Constructor con inyección de dependencias (para testing)
    public FachadaSeleccionarOpcion(ISeleccionarOpcion seleccionarOpcion) {
        this.seleccionarOpcion = seleccionarOpcion;
    }

    @Override
    public List<SolicitudDTO> obtenerTodasLasSolicitudes() {
        return seleccionarOpcion.obtenerTodasLasSolicitudes();
    }

    @Override
    public boolean aceptarSolicitud(String idSolicitud, String idAdmin) throws Exception {
        return seleccionarOpcion.aceptarSolicitud(idSolicitud, idAdmin);
    }

    @Override
    public boolean rechazarSolicitud(String idSolicitud, String idAdmin) throws Exception {
        return seleccionarOpcion.rechazarSolicitud(idSolicitud, idAdmin);
    }

    @Override
    public boolean modificarSolicitud(String idSolicitud, String idAdmin, String razonModificacion) throws Exception {
        return seleccionarOpcion.modificarSolicitud(idSolicitud, idAdmin, razonModificacion);
    }
}
