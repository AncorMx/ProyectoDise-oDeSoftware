package ObjetoNegocio;

import DTOS.CitaDisponibleDTO;
import java.util.List;

/**
 * Interfaz para gestión de citas disponibles
 * 
 * @author Sistema
 */
public interface ICitaDisponibleBO {

    /**
     * Obtiene todas las citas disponibles
     * 
     * @return Lista de citas disponibles
     */
    List<CitaDisponibleDTO> obtenerCitasDisponibles();

    /**
     * Reserva una cita para un usuario
     * 
     * @param idCita    ID de la cita
     * @param idUsuario ID del usuario
     * @return true si se reservó exitosamente
     */
    boolean reservarCita(String idCita, String idUsuario);

    /**
     * Verifica si una cita está disponible
     * 
     * @param idCita ID de la cita
     * @return true si está disponible
     */
    boolean verificarDisponibilidad(String idCita);

    /**
     * Libera una cita
     * 
     * @param idCita ID de la cita
     * @return true si se liberó exitosamente
     */
    boolean liberarCita(String idCita);
}
