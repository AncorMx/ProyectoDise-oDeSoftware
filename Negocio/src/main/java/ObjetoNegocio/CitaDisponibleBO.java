package ObjetoNegocio;

import daos.CitaDisponibleDAO;
import DTOS.CitaDisponibleDTO;
import entities.CitaDisponible;

import java.util.ArrayList;
import java.util.List;

/**
 * Business Object para gestionar citas disponibles
 * 
 * @author Sistema
 */
public class CitaDisponibleBO implements ICitaDisponibleBO {
    private final CitaDisponibleDAO citaDAO;

    public CitaDisponibleBO() {
        this.citaDAO = new CitaDisponibleDAO();
    }

    @Override
    public List<CitaDisponibleDTO> obtenerCitasDisponibles() {
        List<CitaDisponibleDTO> citasDTO = new ArrayList<>();

        try {
            List<CitaDisponible> citas = citaDAO.obtenerCitasDisponibles();

            for (CitaDisponible cita : citas) {
                CitaDisponibleDTO dto = entityToDTO(cita);
                citasDTO.add(dto);
            }

            System.out.println("→ " + citasDTO.size() + " citas disponibles encontradas");
        } catch (Exception e) {
            System.err.println("Error en BO al obtener citas: " + e.getMessage());
            e.printStackTrace();
        }

        return citasDTO;
    }

    @Override
    public boolean reservarCita(String idCita, String idUsuario) {
        try {
            // Primero verificar que esté disponible
            if (!verificarDisponibilidad(idCita)) {
                System.err.println("✗ La cita ya no está disponible");
                return false;
            }

            // Marcar como ocupada
            boolean resultado = citaDAO.marcarCitaOcupada(idCita, idUsuario);

            if (resultado) {
                System.out.println("✓ Cita reservada exitosamente para usuario: " + idUsuario);
            }

            return resultado;
        } catch (Exception e) {
            System.err.println("Error en BO al reservar cita: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean verificarDisponibilidad(String idCita) {
        try {
            return citaDAO.estaCitaDisponible(idCita);
        } catch (Exception e) {
            System.err.println("Error en BO al verificar disponibilidad: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean liberarCita(String idCita) {
        try {
            return citaDAO.liberarCita(idCita);
        } catch (Exception e) {
            System.err.println("Error en BO al liberar cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Convierte una entidad a DTO
     */
    private CitaDisponibleDTO entityToDTO(CitaDisponible entity) {
        CitaDisponibleDTO dto = new CitaDisponibleDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().toHexString());
        }
        dto.setFecha(entity.getFecha());
        dto.setHora(entity.getHora());
        dto.setDisponible(entity.isDisponible());
        if (entity.getIdUsuario() != null) {
            dto.setIdUsuario(entity.getIdUsuario().toHexString());
        }
        return dto;
    }
}
