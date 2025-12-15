package negocioo;

import entities.Mascota;
import gestion.catalogo.dtos.CatalogoDTO;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de adaptadores para convertir entre entidades y DTOs.
 * Facilita la comunicación entre capas sin exponer entidades de persistencia.
 */
public class Adaptadores {

    /**
     * Convierte una entidad Mascota a CatalogoDTO.
     * 
     * @param mascota Entidad de dominio
     * @return DTO para la capa de presentación
     */
    public static CatalogoDTO entidadADTO(Mascota mascota) {
        if (mascota == null) {
            return null;
        }

        CatalogoDTO dto = new CatalogoDTO();

        // Convertir ObjectId a String
        if (mascota.getId() != null) {
            dto.setId(mascota.getId().toString());
        }

        dto.setNombre(mascota.getNombre());
        dto.setEspecie(mascota.getEspecie());
        dto.setEdad(mascota.getEdad());
        dto.setEstadoSalud(mascota.getEstadoSalud());
        dto.setPersonalidad(mascota.getPersonalidad());
        dto.setUrlImagen(mascota.getUrlImagen());
        dto.setEstado(mascota.getEstado());
        dto.setDisponible(mascota.isDisponible());
        dto.setColor(mascota.getColor());
        dto.setRaza(mascota.getRaza());
        dto.setPeso(mascota.getPeso());

        return dto;
    }

    /**
     * Convierte un CatalogoDTO a entidad Mascota.
     * 
     * @param dto DTO de la capa de presentación
     * @return Entidad de dominio
     */
    public static Mascota dtoAEntidad(CatalogoDTO dto) {
        if (dto == null) {
            return null;
        }

        Mascota mascota = new Mascota();

        // Convertir String a ObjectId si existe
        if (dto.getId() != null && !dto.getId().isEmpty()) {
            try {
                mascota.setId(new ObjectId(dto.getId()));
            } catch (IllegalArgumentException e) {
                // Si el ID no es válido, dejarlo null (nueva mascota)
                mascota.setId(null);
            }
        }

        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());
        mascota.setEdad(dto.getEdad());
        mascota.setEstadoSalud(dto.getEstadoSalud());
        mascota.setPersonalidad(dto.getPersonalidad());
        mascota.setUrlImagen(dto.getUrlImagen());
        mascota.setEstado(dto.getEstado() != null ? dto.getEstado() : "disponible");
        mascota.setDisponible(dto.isDisponible());
        mascota.setColor(dto.getColor());
        mascota.setRaza(dto.getRaza());
        mascota.setPeso(dto.getPeso());

        return mascota;
    }

    /**
     * Convierte una lista de entidades Mascota a lista de DTOs.
     * 
     * @param mascotas Lista de entidades
     * @return Lista de DTOs
     */
    public static List<CatalogoDTO> listEntidadADTO(List<Mascota> mascotas) {
        if (mascotas == null) {
            return new ArrayList<>();
        }

        List<CatalogoDTO> dtos = new ArrayList<>();
        for (Mascota mascota : mascotas) {
            dtos.add(entidadADTO(mascota));
        }

        return dtos;
    }

    /**
     * Convierte una lista de DTOs a lista de entidades Mascota.
     * 
     * @param dtos Lista de DTOs
     * @return Lista de entidades
     */
    public static List<Mascota> listDTOAEntidad(List<CatalogoDTO> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }

        List<Mascota> mascotas = new ArrayList<>();
        for (CatalogoDTO dto : dtos) {
            mascotas.add(dtoAEntidad(dto));
        }

        return mascotas;
    }
}
