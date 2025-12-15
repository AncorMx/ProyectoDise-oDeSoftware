package ObjetoNegocio;

import DTOS.MascotaDTO;
import daos.MascotaDAO;
import conexion.ConexionMongoDB;
import entities.Mascota;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la logica de negocio relacionada con Mascotas.
 * Proporciona operaciones CRUD y conversion entre DTOs y entidades.
 */
public class MascotaBO implements IMascotaBO {

    private MascotaDAO mascotaDAO;

    public MascotaBO() {
        this.mascotaDAO = new MascotaDAO(ConexionMongoDB.getInstancia().getDatabase());
    }

    /**
     * Registra una nueva mascota en el sistema
     */
    @Override
    public void registrarMascota(MascotaDTO mascotaDTO) {
        if (mascotaDTO != null) {
            Mascota mascota = convertirAEntidad(mascotaDTO);
            ObjectId id = mascotaDAO.guardar(mascota);
            mascotaDTO.setId(id.toHexString());
            System.out.println("Mascota registrada con ID: " + id);
        }
    }

    /**
     * Busca una mascota por su ID
     */
    @Override
    public MascotaDTO buscarMascotaPorId(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            Mascota mascota = mascotaDAO.buscarPorId(objectId);
            return convertirADTO(mascota);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: ID de mascota invalido: " + id);
            return null;
        } catch (Exception e) {
            System.err.println("Error al buscar mascota: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca mascota usando ObjectId de MongoDB
     */
    public MascotaDTO buscarMascotaPorIdMongo(String idHex) {
        Mascota mascota = mascotaDAO.buscarPorId(new ObjectId(idHex));
        return convertirADTO(mascota);
    }

    /**
     * Obtiene todas las mascotas registradas
     */
    @Override
    public List<MascotaDTO> buscarTodasLasMascotas() {
        List<Mascota> mascotas = mascotaDAO.buscarTodas();
        List<MascotaDTO> dtos = new ArrayList<>();
        for (Mascota m : mascotas) {
            dtos.add(convertirADTO(m));
        }
        return dtos;
    }

    /**
     * Obtiene todas las mascotas disponibles para adopcion
     */
    @Override
    public List<MascotaDTO> buscarMascotasDisponibles() {
        List<Mascota> mascotas = mascotaDAO.buscarDisponibles();
        List<MascotaDTO> dtos = new ArrayList<>();
        for (Mascota m : mascotas) {
            dtos.add(convertirADTO(m));
        }
        return dtos;
    }

    /**
     * Obtiene mascotas disponibles filtradas por especie
     */
    @Override
    public List<MascotaDTO> buscarMascotasDisponiblesPorEspecie(String especie) {
        if (especie == null || especie.isEmpty() || "Todas".equalsIgnoreCase(especie)) {
            return buscarMascotasDisponibles();
        }

        List<Mascota> mascotas = mascotaDAO.buscarDisponiblesPorEspecie(especie);
        List<MascotaDTO> dtos = new ArrayList<>();
        for (Mascota m : mascotas) {
            dtos.add(convertirADTO(m));
        }
        return dtos;
    }

    /**
     * Actualiza los datos de una mascota existente
     */
    @Override
    public void actualizarMascota(MascotaDTO mascotaDTO) {
        if (mascotaDTO != null) {
            Mascota mascota = convertirAEntidad(mascotaDTO);
            mascotaDAO.actualizar(mascota);
            System.out.println("Mascota actualizada: " + mascota.getId());
        }
    }

    /**
     * Actualiza el estado de salud de una mascota
     */
    @Override
    public void actualizaEstadoSalud(MascotaDTO mascota, String nuevoEstado) {
        if (mascota != null) {
            mascota.setEstadoSalud(nuevoEstado);
            actualizarMascota(mascota);
        }
    }

    /**
     * Registra el dueno de una mascota, marcandola como no disponible
     */
    @Override
    public void registraDueño(MascotaDTO mascota, Long idDueño) {
        if (mascota != null) {
            mascota.setDisponible(false);
            actualizarMascota(mascota);
        }
    }

    /**
     * Elimina una mascota del catalogo (soft delete)
     * Marca la mascota como no disponible en lugar de borrarla fisicamente
     */
    @Override
    public boolean eliminarMascota(String id) {
        try {
            // Busco la mascota
            MascotaDTO mascota = buscarMascotaPorId(id);

            if (mascota == null) {
                System.err.println("No se encontro mascota con ID: " + id);
                return false;
            }

            // Soft delete: marcar como no disponible
            mascota.setDisponible(false);
            mascota.setEstado("Eliminada del catalogo");

            // Actualizar en BD
            actualizarMascota(mascota);

            System.out.println("Mascota eliminada del catalogo (soft delete): " + id);
            return true;

        } catch (Exception e) {
            System.err.println("Error al eliminar mascota: " + e.getMessage());
            return false;
        }
    }

    /**
     * Convierte una entidad Mascota a un DTO
     */
    private MascotaDTO convertirADTO(Mascota entidad) {
        if (entidad == null)
            return null;
        MascotaDTO dto = new MascotaDTO();
        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toHexString());
        }
        dto.setNombre(entidad.getNombre());
        dto.setEspecie(entidad.getEspecie());
        dto.setEstadoSalud(entidad.getEstadoSalud());
        dto.setPersonalidad(entidad.getPersonalidad());
        dto.setUrlImagen(entidad.getUrlImagen());
        dto.setEdad(entidad.getEdad());
        dto.setDisponible(entidad.isDisponible());
        dto.setEstado(entidad.getEstado());
        dto.setColor(entidad.getColor());
        dto.setRaza(entidad.getRaza());
        dto.setPeso(entidad.getPeso());

        // Campos para búsqueda de mascota ideal
        dto.setTamano(entidad.getTamano());
        dto.setNivelActividad(entidad.getNivelActividad());
        dto.setPeludo(entidad.isPeludo());
        dto.setCostoMantenimiento(entidad.getCostoMantenimiento());
        dto.setDescripcion(entidad.getDescripcion());

        return dto;
    }

    /**
     * Convierte un DTO de Mascota a una entidad
     */
    private Mascota convertirAEntidad(MascotaDTO dto) {
        if (dto == null)
            return null;
        Mascota entidad = new Mascota();
        // Si el DTO tiene un ID (actualización), convertirlo a ObjectId
        if (dto.getId() != null && !dto.getId().isEmpty()) {
            try {
                entidad.setId(new ObjectId(dto.getId()));
            } catch (IllegalArgumentException e) {
                System.err.println("ID inválido en DTO, se generará uno nuevo: " + dto.getId());
            }
        }
        entidad.setNombre(dto.getNombre());
        entidad.setEspecie(dto.getEspecie());
        entidad.setEstadoSalud(dto.getEstadoSalud());
        entidad.setPersonalidad(dto.getPersonalidad());
        entidad.setUrlImagen(dto.getUrlImagen());
        entidad.setEdad(dto.getEdad());
        entidad.setDisponible(dto.isDisponible());
        entidad.setEstado(dto.getEstado());

        // Nuevos campos
        entidad.setColor(dto.getColor());
        entidad.setRaza(dto.getRaza());
        entidad.setPeso(dto.getPeso());

        // Campos para búsqueda de mascota ideal
        entidad.setTamano(dto.getTamano());
        entidad.setNivelActividad(dto.getNivelActividad());
        entidad.setPeludo(dto.isPeludo());
        entidad.setCostoMantenimiento(dto.getCostoMantenimiento());
        entidad.setDescripcion(dto.getDescripcion());

        return entidad;
    }
}
