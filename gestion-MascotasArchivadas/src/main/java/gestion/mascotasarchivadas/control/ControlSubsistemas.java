package gestion.mascotasarchivadas.control;

import gestion.mascotasarchivadas.dtos.MascotaArchivoDTO;
import negocio.IGestionMascotasArchivadas;

import java.util.List;

/**
 * Controlador de subsistemas para el módulo de gestión de mascotas archivadas.
 * Coordina la lógica de negocio y gestiona instancias de subsistemas.
 */
public class ControlSubsistemas {

    private final IGestionMascotasArchivadas gestionMascotasArchivadas;

    /**
     * Constructor con inyección de dependencias.
     * 
     * @param gestionMascotasArchivadas Instancia de la gestión de mascotas.
     */
    public ControlSubsistemas(IGestionMascotasArchivadas gestionMascotasArchivadas) {
        this.gestionMascotasArchivadas = gestionMascotasArchivadas;
    }

    /**
     * Obtiene el catálogo de mascotas archivadas.
     * 
     * @return Lista de mascotas archivadas
     */
    public List<MascotaArchivoDTO> obtenerCatalogo() {
        return gestionMascotasArchivadas.obtenerMascotasArchivadas();
    }

    /**
     * Obtiene los detalles de una mascota archivada específica.
     * 
     * @param id ID de la mascota
     * @return DTO con los detalles
     */
    public MascotaArchivoDTO consultarDetalleMascota(String id) {
        return gestionMascotasArchivadas.obtenerDetalleMascota(id);
    }

    /**
     * Elimina permanentemente una mascota de la base de datos.
     * 
     * @param id ID de la mascota a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean eliminarPermanente(String id) {
        return gestionMascotasArchivadas.eliminarPermanente(id);
    }

    /**
     * Reactiva una mascota archivada, devolviéndola al catálogo.
     * 
     * @param id ID de la mascota a reactivar
     * @return true si se reactivó correctamente
     */
    public boolean reactivarMascota(String id) {
        return gestionMascotasArchivadas.reactivarMascota(id);
    }

    /**
     * Filtra mascotas archivadas por especie.
     * 
     * @param especie Especie a filtrar
     * @return Lista filtrada
     */
    public List<MascotaArchivoDTO> filtrarPorEspecie(String especie) {
        return gestionMascotasArchivadas.filtrarPorEspecie(especie);
    }
}
