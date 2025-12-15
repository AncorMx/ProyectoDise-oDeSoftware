package persistencia;

import entities.Mascota;
import java.util.List;

/**
 * Interfaz para operaciones de persistencia de mascotas archivadas.
 * Define las operaciones específicas para gestionar mascotas en estado "baja".
 */
public interface IMascotaArchivoDAO {

    /**
     * Obtiene todas las mascotas archivadas (estado = "baja").
     * 
     * @return Lista de mascotas archivadas
     */
    List<Mascota> obtenerMascotasArchivadas();

    /**
     * Obtiene una mascota archivada por su ID.
     * 
     * @param id ID de la mascota
     * @return Mascota encontrada o null si no existe
     */
    Mascota obtenerMascotaPorId(String id);

    /**
     * Elimina permanentemente una mascota de la base de datos.
     * 
     * @param id ID de la mascota a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    boolean eliminarPermanente(String id);

    /**
     * Reactiva una mascota archivada (cambia estado a "disponible").
     * 
     * @param id ID de la mascota a reactivar
     * @return true si se reactivó correctamente, false en caso contrario
     */
    boolean reactivarMascota(String id);

    /**
     * Busca mascotas archivadas por especie.
     * 
     * @param especie Especie a filtrar
     * @return Lista de mascotas archivadas de la especie especificada
     */
    List<Mascota> buscarPorEspecie(String especie);
}
