package cubuscarmascotaideal.negocio.subsistema;

import cubuscarmascotaideal.negocio.dtos.EncuestaDTO;
import cubuscarmascotaideal.negocio.dtos.MascotaResultadoDTO;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Punto de acceso simplificado al subsistema de búsqueda de mascotas
 */
public class FachadaBuscarMascota implements IBuscarMascota {

    private final BuscarMascota buscarMascota;

    public FachadaBuscarMascota() {
        this.buscarMascota = new BuscarMascota();
    }

    @Override
    public List<MascotaResultadoDTO> buscarMascotasCompatibles(EncuestaDTO encuesta) {
        return buscarMascota.buscarMascotasCompatibles(encuesta);
    }

    @Override
    public List<MascotaResultadoDTO> obtenerTodasConCompatibilidad(EncuestaDTO encuesta) {
        return buscarMascota.obtenerTodasConCompatibilidad(encuesta);
    }

    /**
     * Guarda los resultados de búsqueda del usuario
     */
    public void guardarResultados(ObjectId idUsuario, List<MascotaResultadoDTO> resultados) {
        buscarMascota.guardarResultados(idUsuario, resultados);
    }

    /**
     * Obtiene los resultados que el usuario guardó antes
     */
    public List<MascotaResultadoDTO> obtenerResultadosGuardados(ObjectId idUsuario) {
        return buscarMascota.obtenerResultadosGuardados(idUsuario);
    }
}
