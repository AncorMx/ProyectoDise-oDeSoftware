package cuaceptarsolicitudes.negocio.adaptadores;

import cuaceptarsolicitudes.negocio.dtos.SolicitudDTO;
import entities.SolicitudAdopcion;
import entities.Usuario;
import entities.Mascota;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de adaptadores para convertir entre entidades y DTOs de solicitudes.
 * Facilita la comunicación entre capas sin exponer entidades de persistencia.
 */
public class AdaptadorSolicitud {

    /**
     * Convierte una entidad SolicitudAdopcion a SolicitudDTO.
     * 
     * @param solicitud Entidad de dominio
     * @param usuario   Usuario asociado (puede ser null)
     * @param mascota   Mascota asociada (puede ser null)
     * @return DTO para la capa de presentación
     */
    public static SolicitudDTO entidadADTO(SolicitudAdopcion solicitud, Usuario usuario, Mascota mascota) {
        if (solicitud == null) {
            return null;
        }

        SolicitudDTO dto = new SolicitudDTO();

        // Convertir ObjectId a String
        if (solicitud.getId() != null) {
            dto.setId(solicitud.getId().toString());
        }

        // Datos de usuario
        if (usuario != null) {
            if (usuario.getInfoPersonal() != null) {
                dto.setNombreUsuario(usuario.getInfoPersonal().getNombre());
                dto.setCorreoUsuario(usuario.getInfoPersonal().getCorreo());
            }
            if (usuario.getId() != null) {
                dto.setIdUsuario(usuario.getId().toString());
            }
        } else if (solicitud.getIdUsuario() != null) {
            dto.setIdUsuario(solicitud.getIdUsuario().toString());
        }

        // Datos de mascota
        if (mascota != null) {
            dto.setNombreMascota(mascota.getNombre());
            if (mascota.getId() != null) {
                dto.setIdMascota(mascota.getId().toString());
            }
        } else if (solicitud.getIdMascota() != null) {
            dto.setIdMascota(solicitud.getIdMascota().toString());
        }

        dto.setEstado(solicitud.getEstado());
        dto.setFechaSolicitud(solicitud.getFechaSolicitud());

        // Mapear idCita
        if (solicitud.getIdCita() != null) {
            dto.setIdCita(solicitud.getIdCita().toString());
        }

        // Mapear mensaje de corrección
        dto.setMensajeCorreccion(solicitud.getMensajeCorreccion());

        return dto;
    }

    /**
     * Convierte una entidad SolicitudAdopcion a SolicitudDTO (sin usuario ni
     * mascota).
     * 
     * @param solicitud Entidad de dominio
     * @return DTO para la capa de presentación
     */
    public static SolicitudDTO entidadADTO(SolicitudAdopcion solicitud) {
        return entidadADTO(solicitud, null, null);
    }

    /**
     * Convierte un SolicitudDTO a entidad SolicitudAdopcion.
     * 
     * @param dto DTO de la capa de presentación
     * @return Entidad de dominio
     */
    public static SolicitudAdopcion dtoAEntidad(SolicitudDTO dto) {
        if (dto == null) {
            return null;
        }

        SolicitudAdopcion solicitud = new SolicitudAdopcion();

        // Convertir String a ObjectId si existe
        if (dto.getId() != null && !dto.getId().isEmpty()) {
            try {
                solicitud.setId(new ObjectId(dto.getId()));
            } catch (IllegalArgumentException e) {
                solicitud.setId(null);
            }
        }

        if (dto.getIdUsuario() != null && !dto.getIdUsuario().isEmpty()) {
            try {
                solicitud.setIdUsuario(new ObjectId(dto.getIdUsuario()));
            } catch (IllegalArgumentException e) {
                // Ignorar ID inválido
            }
        }

        if (dto.getIdMascota() != null && !dto.getIdMascota().isEmpty()) {
            try {
                solicitud.setIdMascota(new ObjectId(dto.getIdMascota()));
            } catch (IllegalArgumentException e) {
                // Ignorar ID inválido
            }
        }

        solicitud.setEstado(dto.getEstado());
        solicitud.setFechaSolicitud(dto.getFechaSolicitud());

        return solicitud;
    }

    /**
     * Convierte una lista de entidades SolicitudAdopcion a lista de DTOs.
     * 
     * @param solicitudes Lista de entidades
     * @return Lista de DTOs
     */
    public static List<SolicitudDTO> listEntidadADTO(List<SolicitudAdopcion> solicitudes) {
        if (solicitudes == null) {
            return new ArrayList<>();
        }

        List<SolicitudDTO> dtos = new ArrayList<>();
        for (SolicitudAdopcion solicitud : solicitudes) {
            dtos.add(entidadADTO(solicitud));
        }

        return dtos;
    }

    /**
     * Convierte una lista de DTOs a lista de entidades SolicitudAdopcion.
     * 
     * @param dtos Lista de DTOs
     * @return Lista de entidades
     */
    public static List<SolicitudAdopcion> listDTOAEntidad(List<SolicitudDTO> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }

        List<SolicitudAdopcion> solicitudes = new ArrayList<>();
        for (SolicitudDTO dto : dtos) {
            solicitudes.add(dtoAEntidad(dto));
        }

        return solicitudes;
    }
}
