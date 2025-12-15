package gestion.catalogo.control;

import gestion.catalogo.dtos.CatalogoDTO;
import negocioo.IGestionCatalogo;

import java.util.List;

/**
 * Controlador de subsistemas para el módulo de gestión de catálogo.
 * Coordina la lógica de negocio y gestiona instancias de subsistemas.
 */
public class ControlSubsistemas {

    private final IGestionCatalogo gestionCatalogo;

    public ControlSubsistemas(IGestionCatalogo gestionCatalogo) {
        this.gestionCatalogo = gestionCatalogo;
    }

    /**
     * Obtiene el catálogo completo de mascotas.
     * 
     * @return Lista de mascotas
     */
    public List<CatalogoDTO> obtenerCatalogo() {
        return gestionCatalogo.obtenerMascotasDisponibles();
    }

    /**
     * Obtiene los detalles de una mascota específica.
     * 
     * @param id ID de la mascota
     * @return DTO con los detalles
     */
    public CatalogoDTO consultarDetalleMascota(String id) {
        return gestionCatalogo.obtenerDetalleMascota(id);
    }

    /**
     * Registra una nueva mascota en el catálogo.
     * 
     * @param mascota Datos de la mascota
     * @return ID de la mascota registrada
     */
    public String agregarMascota(CatalogoDTO mascota) {
        return gestionCatalogo.registrarNuevaMascota(mascota);
    }

    /**
     * Actualiza los datos de una mascota existente.
     * 
     * @param mascota Datos actualizados
     * @return true si se actualizó correctamente
     */
    public boolean editarMascota(CatalogoDTO mascota) {
        return gestionCatalogo.modificarMascota(mascota);
    }

    /**
     * Elimina una mascota del catálogo.
     * 
     * @param id ID de la mascota
     * @return true si se eliminó correctamente
     */
    public boolean eliminarMascota(String id) {
        return gestionCatalogo.darBajaMascota(id);
    }

    /**
     * Actualiza el expediente médico de una mascota.
     * 
     * @param id         ID de la mascota
     * @param expediente Información del expediente
     * @return true si se actualizó correctamente
     */
    public boolean actualizarExpediente(String id, String expediente) {
        return gestionCatalogo.actualizarExpedienteMedico(id, expediente);
    }

    /**
     * Filtra mascotas por estado.
     * 
     * @param estado Estado a filtrar
     * @return Lista filtrada
     */
    public List<CatalogoDTO> filtrarPorEstado(String estado) {
        return gestionCatalogo.filtrarPorEstado(estado);
    }

    /**
     * Filtra mascotas por especie.
     * 
     * @param especie Especie a filtrar
     * @return Lista filtrada
     */
    public List<CatalogoDTO> filtrarPorEspecie(String especie) {
        return gestionCatalogo.filtrarPorEspecie(especie);
    }

    /**
     * Obtiene solo mascotas disponibles.
     * 
     * @return Lista de mascotas disponibles
     */
    public List<CatalogoDTO> obtenerDisponibles() {
        return gestionCatalogo.obtenerMascotasDisponibles();
    }
}
