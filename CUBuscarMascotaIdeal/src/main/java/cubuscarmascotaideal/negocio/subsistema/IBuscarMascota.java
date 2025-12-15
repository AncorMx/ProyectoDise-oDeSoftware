package cubuscarmascotaideal.negocio.subsistema;

import cubuscarmascotaideal.negocio.dtos.EncuestaDTO;
import cubuscarmascotaideal.negocio.dtos.MascotaResultadoDTO;

import java.util.List;

/**
 * Interfaz del subsistema de búsqueda de mascotas compatibles.
 */
public interface IBuscarMascota {

    /**
     * Busca mascotas compatibles según las preferencias de la encuesta
     * 
     * @param encuesta Preferencias del usuario
     * @return Lista de mascotas ordenadas por compatibilidad (mayor a menor)
     */
    List<MascotaResultadoDTO> buscarMascotasCompatibles(EncuestaDTO encuesta);

    /**
     * Obtiene todas las mascotas disponibles con sus porcentajes
     * 
     * @param encuesta Preferencias para calcular compatibilidad
     * @return Lista de todas las mascotas disponibles con score
     */
    List<MascotaResultadoDTO> obtenerTodasConCompatibilidad(EncuestaDTO encuesta);
}
