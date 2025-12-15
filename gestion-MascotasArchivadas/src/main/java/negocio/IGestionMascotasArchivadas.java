package negocio;

import gestion.mascotasarchivadas.dtos.MascotaArchivoDTO;
import java.util.List;

/**
 * Interfaz para la lógica de negocio de gestión de mascotas archivadas.
 * Define las operaciones disponibles para el manejo de mascotas en estado
 * "baja".
 */
public interface IGestionMascotasArchivadas {

    /**
     * Obtiene todas las mascotas archivadas.
     * 
     * @return Lista de mascotas archivadas
     */
    List<MascotaArchivoDTO> obtenerMascotasArchivadas();

    /**
     * Obtiene los detalles de una mascota archivada específica.
     * 
     * @param id ID de la mascota
     * @return DTO con los detalles de la mascota o null si no existe
     */
    MascotaArchivoDTO obtenerDetalleMascota(String id);

    /**
     * Elimina permanentemente una mascota de la base de datos.
     * Esta operación es irreversible.
     * 
     * @param id ID de la mascota a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    boolean eliminarPermanente(String id);

    /**
     * Reactiva una mascota archivada, devolviéndola al catálogo.
     * Cambia el estado de "baja" a "disponible".
     * 
     * @param id ID de la mascota a reactivar
     * @return true si se reactivó correctamente, false en caso contrario
     */
    boolean reactivarMascota(String id);

    /**
     * Filtra mascotas archivadas por especie.
     * 
     * @param especie Especie a filtrar (perro, gato, etc.)
     * @return Lista de mascotas archivadas de la especie especificada
     */
    List<MascotaArchivoDTO> filtrarPorEspecie(String especie);
}
