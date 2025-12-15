package cubuscarmascotaideal.negocio.adaptadores;

import cubuscarmascotaideal.negocio.dtos.MascotaResultadoDTO;
import entities.Mascota;

/**
 * Convierte una Mascota de la base de datos a un DTO con su porcentaje de
 * compatibilidad
 */
public class AdaptadorMascotaResultado {

    /**
     * Convierte una Mascota a MascotaResultadoDTO agregando el porcentaje de
     * compatibilidad
     */
    public static MascotaResultadoDTO entidadADTO(Mascota mascota, double porcentajeCompatibilidad) {
        // Si no hay mascota, no puedo convertir
        if (mascota == null) {
            return null;
        }

        // Creo el DTO y copio todos los datos de la mascota
        MascotaResultadoDTO dto = new MascotaResultadoDTO();
        dto.setId(mascota.getId() != null ? mascota.getId().toString() : null);
        dto.setNombre(mascota.getNombre());
        dto.setEspecie(mascota.getEspecie());
        dto.setRaza(mascota.getRaza());
        dto.setEdad(mascota.getEdad());
        dto.setTamano(mascota.getTamano());
        dto.setNivelActividad(mascota.getNivelActividad());
        dto.setPeludo(mascota.isPeludo());
        dto.setCostoMantenimiento(mascota.getCostoMantenimiento());
        dto.setDescripcion(mascota.getDescripcion());
        dto.setUrlImagen(mascota.getUrlImagen());

        // Agrego el porcentaje de compatibilidad calculado
        dto.setPorcentajeCompatibilidad(porcentajeCompatibilidad);

        return dto;
    }
}
