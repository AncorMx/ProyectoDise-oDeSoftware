package negocioo;

import gestion.catalogo.dtos.CatalogoDTO;
import java.util.List;

/**
 * Interfaz de negocio para la gestión del catálogo de mascotas.
 * Define las operaciones de negocio disponibles para el administrador.
 */
public interface IGestionCatalogo {

    /**
     * Obtiene el catálogo completo de mascotas registradas.
     * 
     * @return Lista de todas las mascotas en formato DTO
     */
    List<CatalogoDTO> obtenerCatalogoCompleto();

    /**
     * Obtiene los detalles de una mascota específica.
     * 
     * @param id Identificador de la mascota
     * @return DTO con los detalles de la mascota o null si no existe
     */
    CatalogoDTO obtenerDetalleMascota(String id);

    /**
     * Registra una nueva mascota en el catálogo.
     * 
     * @param mascota Datos de la mascota a registrar
     * @return ID de la mascota registrada
     * @throws IllegalArgumentException si los datos son inválidos
     */
    String registrarNuevaMascota(CatalogoDTO mascota);

    /**
     * Modifica los datos de una mascota existente.
     * 
     * @param mascota Datos actualizados de la mascota (debe incluir ID)
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws IllegalArgumentException si los datos son inválidos
     */
    boolean modificarMascota(CatalogoDTO mascota);

    /**
     * Da de baja una mascota del catálogo (eliminación lógica).
     * 
     * @param id Identificador de la mascota a dar de baja
     * @return true si se dio de baja correctamente, false en caso contrario
     */
    boolean darBajaMascota(String id);

    /**
     * Actualiza el expediente médico de una mascota.
     * 
     * @param id         Identificador de la mascota
     * @param expediente Información del expediente médico
     * @return true si se actualizó correctamente, false en caso contrario
     */
    boolean actualizarExpedienteMedico(String id, String expediente);

    /**
     * Filtra mascotas por su estado.
     * 
     * @param estado Estado a filtrar (disponible, adoptada, en_tratamiento, baja)
     * @return Lista de mascotas con el estado especificado
     */
    List<CatalogoDTO> filtrarPorEstado(String estado);

    /**
     * Filtra mascotas por especie.
     * 
     * @param especie Especie a filtrar (perro, gato, etc.)
     * @return Lista de mascotas de la especie especificada
     */
    List<CatalogoDTO> filtrarPorEspecie(String especie);

    /**
     * Obtiene solo las mascotas disponibles para adopción.
     * 
     * @return Lista de mascotas disponibles
     */
    List<CatalogoDTO> obtenerMascotasDisponibles();
}
