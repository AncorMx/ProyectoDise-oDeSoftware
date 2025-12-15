package cubuscarmascotaideal.control;

import cubuscarmascotaideal.negocio.dtos.EncuestaDTO;
import cubuscarmascotaideal.negocio.dtos.MascotaResultadoDTO;
import cubuscarmascotaideal.negocio.subsistema.FachadaBuscarMascota;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Controla las llamadas al subsistema de busqueda de mascotas
 */
public class ControlSubsistemas {

    private final FachadaBuscarMascota buscarMascota;

    public ControlSubsistemas() {
        this.buscarMascota = new FachadaBuscarMascota();
    }

    /**
     * Busca mascotas compatibles segun las preferencias de la encuesta
     */
    public List<MascotaResultadoDTO> buscarMascotasCompatibles(EncuestaDTO encuesta) throws Exception {
        return buscarMascota.buscarMascotasCompatibles(encuesta);
    }

    /**
     * Obtiene todas las mascotas con su porcentaje de compatibilidad
     */
    public List<MascotaResultadoDTO> obtenerTodasConCompatibilidad(EncuestaDTO encuesta) throws Exception {
        return buscarMascota.obtenerTodasConCompatibilidad(encuesta);
    }

    /**
     * Guarda los resultados de busqueda del usuario
     */
    public void guardarResultados(ObjectId idUsuario, List<MascotaResultadoDTO> resultados) {
        buscarMascota.guardarResultados(idUsuario, resultados);
    }

    /**
     * Obtiene los resultados que el usuario guardo antes
     */
    public List<MascotaResultadoDTO> obtenerResultadosGuardados(ObjectId idUsuario) {
        return buscarMascota.obtenerResultadosGuardados(idUsuario);
    }
}
