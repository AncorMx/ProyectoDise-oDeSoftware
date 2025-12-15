package persistencia;

import entities.Mascota;
import java.util.List;

/**
 * Interfaz para acceso a datos de mascotas en el módulo de gestión de catálogo.
 * Define operaciones CRUD completas para administración de mascotas.
 */
public interface IMascotaDAO {

    /**
     * Obtiene todas las mascotas registradas en el sistema.
     * 
     * @return Lista de todas las mascotas
     */
    List<Mascota> obtenerTodasMascotas();

    /**
     * Obtiene una mascota por su identificador único.
     * 
     * @param id Identificador de la mascota
     * @return Mascota encontrada o null si no existe
     */
    Mascota obtenerMascotaPorId(String id);

    /**
     * Agrega una nueva mascota al catálogo.
     * 
     * @param mascota Mascota a agregar
     * @return true si se agregó exitosamente, false en caso contrario
     */
    boolean agregarMascota(Mascota mascota);

    /**
     * Actualiza la información de una mascota existente.
     * 
     * @param mascota Mascota con datos actualizados
     * @return true si se actualizó exitosamente, false en caso contrario
     */
    boolean actualizarMascota(Mascota mascota);

    /**
     * Elimina una mascota del catálogo.
     * 
     * @param id Identificador de la mascota a eliminar
     * @return true si se eliminó exitosamente, false en caso contrario
     */
    boolean eliminarMascota(String id);

    /**
     * Busca mascotas por su estado (disponible, adoptada, etc.).
     * 
     * @param estado Estado de la mascota a buscar
     * @return Lista de mascotas con el estado especificado
     */
    List<Mascota> buscarPorEstado(String estado);

    /**
     * Busca mascotas por especie.
     * 
     * @param especie Especie a buscar (perro, gato, etc.)
     * @return Lista de mascotas de la especie especificada
     */
    List<Mascota> buscarPorEspecie(String especie);

    /**
     * Obtiene solo las mascotas disponibles para adopción.
     * 
     * @return Lista de mascotas disponibles
     */
    List<Mascota> obtenerMascotasDisponibles();
}
