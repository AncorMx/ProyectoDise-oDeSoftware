package ObjetoNegocio;

import DTOS.CitaDTO;
import daos.CitaDAO;
import conexion.ConexionMongoDB;
import entities.Cita;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la logica de negocio de las citas
 */
public class CitaBO implements ICitaBO {

    private CitaDAO citaDAO;

    public CitaBO() {
        this.citaDAO = new CitaDAO(ConexionMongoDB.getInstancia().getDatabase());
    }

    @Override
    public void agendarCita(CitaDTO citaDTO) {
        if (citaDTO != null) {
            Cita cita = convertirAEntidad(citaDTO);
            citaDAO.guardar(cita);
        }
    }

    @Override
    public CitaDTO buscarCitaPorId(String id) {
        return null; // TODO: Implementar busqueda por ID
    }

    @Override
    public List<CitaDTO> buscarCitasPorUsuario(String idUsuario) {
        return new ArrayList<>(); // TODO: Implementar busqueda por usuario
    }

    @Override
    public LocalDateTime obtieneFechaHora(CitaDTO cita) {
        return (cita != null) ? cita.getFechaHora() : null;
    }

    @Override
    public void asociaUsuario(CitaDTO cita, String idUsuario) {
        if (cita != null) {
            cita.setIdUsuario(idUsuario);
        }
    }

    private Cita convertirAEntidad(CitaDTO dto) {
        if (dto == null)
            return null;

        Cita entidad = new Cita();
        entidad.setFechaHora(dto.getFechaHora());

        if (dto.getIdUsuario() != null) {
            entidad.setIdUsuario(new org.bson.types.ObjectId(dto.getIdUsuario()));
        }
        if (dto.getIdMascota() != null) {
            entidad.setIdMascota(new org.bson.types.ObjectId(dto.getIdMascota()));
        }

        return entidad;
    }
}
