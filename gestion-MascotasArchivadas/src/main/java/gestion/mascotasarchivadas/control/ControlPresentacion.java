package gestion.mascotasarchivadas.control;

import gestion.mascotasarchivadas.dtos.MascotaArchivoDTO;
import gestion.mascotasarchivadas.dtos.ResultadoOperacion;
import negocio.GestionMascotasArchivadas;
import negocio.IGestionMascotasArchivadas;

import java.util.ArrayList;
import java.util.List;

/**
 * Fachada principal del módulo de gestión de mascotas archivadas.
 * Proporciona una interfaz simplificada para la capa de presentación,
 * maneja excepciones y traduce errores a mensajes de usuario.
 */
public class ControlPresentacion {

    private final ControlSubsistemas controlSubsistemas;
    private final IGestionMascotasArchivadas gestionMascotasArchivadas;

    /**
     * Constructor por defecto.
     * Inicializa las dependencias internas.
     */
    public ControlPresentacion() {
        this.gestionMascotasArchivadas = new GestionMascotasArchivadas();
        this.controlSubsistemas = new ControlSubsistemas(gestionMascotasArchivadas);
    }

    // Constructor con inyección de dependencias para testing
    /**
     * Constructor con inyección de dependencias para testing.
     * 
     * @param gestionMascotasArchivadas Implementación de la gestión de mascotas
     */
    public ControlPresentacion(IGestionMascotasArchivadas gestionMascotasArchivadas) {
        this.gestionMascotasArchivadas = gestionMascotasArchivadas;
        this.controlSubsistemas = new ControlSubsistemas(gestionMascotasArchivadas);
    }

    /**
     * Obtiene el catálogo de mascotas archivadas.
     * 
     * @return Lista de mascotas archivadas o lista vacía en caso de error
     */
    public List<MascotaArchivoDTO> obtenerCatalogo() {
        try {
            return controlSubsistemas.obtenerCatalogo();
        } catch (Exception e) {
            System.err.println("Error al obtener catálogo de mascotas archivadas: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Alias de obtenerCatalogo para mayor claridad semántica.
     * 
     * @return Lista de mascotas archivadas
     */
    public List<MascotaArchivoDTO> obtenerMascotasArchivadas() {
        return obtenerCatalogo();
    }

    /**
     * Obtiene los detalles de una mascota archivada específica.
     * 
     * @param id ID de la mascota
     * @return DTO con los detalles o null si no existe
     */
    public MascotaArchivoDTO obtenerDetalles(String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                System.err.println("ID de mascota inválido");
                return null;
            }

            return controlSubsistemas.consultarDetalleMascota(id);
        } catch (Exception e) {
            System.err.println("Error al obtener detalles de mascota: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Alias de obtenerDetalles para compatibilidad.
     */
    /**
     * Alias de obtenerDetalles para compatibilidad.
     * 
     * @param id Identificador de la mascota.
     * @return DTO con los detalles de la mascota.
     */
    public MascotaArchivoDTO obtenerDetalleMascota(String id) {
        return obtenerDetalles(id);
    }

    /**
     * Elimina permanentemente una mascota de la base de datos.
     * Esta operación es irreversible.
     * 
     * @param id ID de la mascota a eliminar
     * @return Resultado de la operación con mensaje para el usuario
     */
    public ResultadoOperacion eliminarPermanente(String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                return ResultadoOperacion.error("ID de mascota inválido");
            }

            // Verificar que la mascota existe
            MascotaArchivoDTO mascota = controlSubsistemas.consultarDetalleMascota(id);
            if (mascota == null) {
                return ResultadoOperacion.error("Mascota no encontrada");
            }

            boolean resultado = controlSubsistemas.eliminarPermanente(id);

            if (resultado) {
                return ResultadoOperacion.exito(
                        "La mascota '" + mascota.getNombre() + "' ha sido eliminada permanentemente del sistema");
            } else {
                return ResultadoOperacion.error(
                        "No se pudo eliminar la mascota. Por favor, intente nuevamente");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar permanentemente mascota: " + e.getMessage());
            e.printStackTrace();
            return ResultadoOperacion.error(
                    "Error del sistema al eliminar la mascota: " + e.getMessage());
        }
    }

    /**
     * Reactiva una mascota archivada, devolviéndola al catálogo.
     * 
     * @param id ID de la mascota a reactivar
     * @return Resultado de la operación con mensaje para el usuario
     */
    public ResultadoOperacion reactivarMascota(String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                return ResultadoOperacion.error("ID de mascota inválido");
            }

            // Verificar que la mascota existe
            MascotaArchivoDTO mascota = controlSubsistemas.consultarDetalleMascota(id);
            if (mascota == null) {
                return ResultadoOperacion.error("Mascota no encontrada en archivos");
            }

            boolean resultado = controlSubsistemas.reactivarMascota(id);

            if (resultado) {
                return ResultadoOperacion.exito(
                        "La mascota '" + mascota.getNombre()
                                + "' ha sido reactivada y devuelta al catálogo exitosamente");
            } else {
                return ResultadoOperacion.error(
                        "No se pudo reactivar la mascota. Verifique que esté archivada");
            }
        } catch (Exception e) {
            System.err.println("Error al reactivar mascota: " + e.getMessage());
            e.printStackTrace();
            return ResultadoOperacion.error(
                    "Error del sistema al reactivar la mascota: " + e.getMessage());
        }
    }

    /**
     * Filtra mascotas archivadas por especie.
     * 
     * @param especie Especie a filtrar (perro, gato, etc.)
     * @return Lista de mascotas filtradas
     */
    public List<MascotaArchivoDTO> filtrarPorEspecie(String especie) {
        try {
            if (especie == null || especie.trim().isEmpty()) {
                return obtenerCatalogo();
            }

            return controlSubsistemas.filtrarPorEspecie(especie);
        } catch (Exception e) {
            System.err.println("Error al filtrar por especie: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
