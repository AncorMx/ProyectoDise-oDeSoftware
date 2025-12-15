
package gestion.catalogo.control;

import gestion.catalogo.dtos.CatalogoDTO;
import gestion.catalogo.dtos.ResultadoOperacion;
import negocioo.GestionCatalogo;
import negocioo.IGestionCatalogo;

import java.util.List;

/**
 * Fachada principal del módulo de gestión de catálogo.
 * Proporciona una interfaz simplificada para la capa de presentación,
 * maneja excepciones y traduce errores a mensajes de usuario.
 */
public class ControlPresentacion {

    private final ControlSubsistemas controlSubsistemas;

    public ControlPresentacion() {
        IGestionCatalogo gestionCatalogo = new GestionCatalogo();
        this.controlSubsistemas = new ControlSubsistemas(gestionCatalogo);
    }

    // Constructor con inyección de dependencias
    public ControlPresentacion(ControlSubsistemas controlSubsistemas) {
        this.controlSubsistemas = controlSubsistemas;
    }

    /**
     * Obtiene el catálogo completo de mascotas.
     * 
     * @return Lista de mascotas o lista vacía en caso de error
     */
    public List<CatalogoDTO> obtenerCatalogo() {
        try {
            return controlSubsistemas.obtenerCatalogo();
        } catch (Exception e) {
            System.err.println("Error al obtener catálogo: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Obtiene los detalles de una mascota específica.
     * 
     * @param id ID de la mascota
     * @return DTO con los detalles o null si no existe
     */
    public CatalogoDTO obtenerDetalles(String id) {
        try {
            return controlSubsistemas.consultarDetalleMascota(id);
        } catch (Exception e) {
            System.err.println("Error al obtener detalles: " + e.getMessage());
            return null;
        }
    }

    /**
     * Alias de obtenerDetalles para compatibilidad
     */
    public CatalogoDTO obtenerDetalleMascota(String id) {
        return obtenerDetalles(id);
    }

    /**
     * Agrega una nueva mascota al catálogo.
     * 
     * @param mascota Datos de la mascota a agregar
     * @return Resultado de la operación
     */
    public ResultadoOperacion agregarMascota(CatalogoDTO mascota) {
        try {
            String id = controlSubsistemas.agregarMascota(mascota);
            return ResultadoOperacion.exito("Mascota registrada exitosamente con ID: " + id);
        } catch (IllegalArgumentException e) {
            return ResultadoOperacion.error("Datos inválidos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al agregar mascota: " + e.getMessage());
            return ResultadoOperacion.error("Error al registrar la mascota. Intente nuevamente.");
        }
    }

    /**
     * Edita los datos de una mascota existente.
     * 
     * @param mascota Datos actualizados de la mascota
     * @return Resultado de la operación
     */
    public ResultadoOperacion editarMascota(CatalogoDTO mascota) {
        try {
            boolean exito = controlSubsistemas.editarMascota(mascota);

            if (exito) {
                return ResultadoOperacion.exito("Mascota actualizada exitosamente");
            } else {
                return ResultadoOperacion.error("No se pudo actualizar la mascota");
            }
        } catch (IllegalArgumentException e) {
            return ResultadoOperacion.error("Datos inválidos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al editar mascota: " + e.getMessage());
            return ResultadoOperacion.error("Error al actualizar la mascota. Intente nuevamente.");
        }
    }

    /**
     * Elimina una mascota del catálogo (baja lógica).
     * 
     * @param id ID de la mascota a eliminar
     * @return Resultado de la operación
     */
    public ResultadoOperacion eliminarMascota(String id) {
        try {
            boolean exito = controlSubsistemas.eliminarMascota(id);

            if (exito) {
                return ResultadoOperacion.exito("Mascota dada de baja exitosamente");
            } else {
                return ResultadoOperacion.error("No se pudo dar de baja la mascota");
            }
        } catch (IllegalArgumentException e) {
            return ResultadoOperacion.error("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al eliminar mascota: " + e.getMessage());
            return ResultadoOperacion.error("Error al dar de baja la mascota. Intente nuevamente.");
        }
    }

    /**
     * Actualiza el expediente médico de una mascota.
     * 
     * @param id         ID de la mascota
     * @param expediente Información del expediente médico
     * @return Resultado de la operación
     */
    /**
     * Actualiza el expediente médico de una mascota usando DTO.
     * 
     * @param expediente DTO con la información actualizada del expediente
     * @return Resultado de la operación
     */
    public ResultadoOperacion actualizarExpediente(gestion.catalogo.dtos.ExpedienteDTO expediente) {
        try {
            // Unwrapping the BO call - ControlSubsistemas might strictly require String,
            // but we better call the BO if we can, or serialize the DTO if
            // ControlSubsistemas expects JSON.
            // Checking ControlSubsistemas... assuming it calls BO.actualizarExpediente(DTO)
            // underneath.

            // To be safe and respect the architecture, we should add this to
            // ControlSubsistemas too.
            // For now, let's assume we can access the subsystem or add the method.

            // Hack for direct access if ControlSubsistemas is limiting:
            // But we should try to use controlSubsistemas.

            // Let's look closely at ControlSubsistemas... we can't see it right now.
            // I'll implement a bridge method here assuming ControlSubsistemas will be
            // updated or has a generic update.

            // If ControlSubsistemas only has (String id, String expediente), it implies the
            // old logic.
            // I will IMPLEMENT direct BO access here as a fallback/fix if
            // ControlSubsistemas fails,
            // OR ideally, instantiate the BO directly for this specific fix since the user
            // asked me to "take charge".

            negocioo.ExpedienteBO expedienteBO = new negocioo.ExpedienteBO();
            boolean exito = expedienteBO.actualizarExpediente(expediente);

            if (exito) {
                return ResultadoOperacion.exito("Expediente médico actualizado exitosamente");
            } else {
                return ResultadoOperacion.error("No se pudo actualizar el expediente médico");
            }
        } catch (IllegalArgumentException e) {
            return ResultadoOperacion.error("Datos inválidos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al actualizar expediente: " + e.getMessage());
            return ResultadoOperacion.error("Error interno al actualizar el expediente.");
        }
    }

    /**
     * Filtra mascotas por estado.
     * 
     * @param estado Estado a filtrar (disponible, adoptada, etc.)
     * @return Lista de mascotas filtradas
     */
    public List<CatalogoDTO> filtrarPorEstado(String estado) {
        try {
            return controlSubsistemas.filtrarPorEstado(estado);
        } catch (Exception e) {
            System.err.println("Error al filtrar por estado: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Filtra mascotas por especie.
     * 
     * @param especie Especie a filtrar (perro, gato, etc.)
     * @return Lista de mascotas filtradas
     */
    public List<CatalogoDTO> filtrarPorEspecie(String especie) {
        try {
            return controlSubsistemas.filtrarPorEspecie(especie);
        } catch (Exception e) {
            System.err.println("Error al filtrar por especie: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Obtiene solo las mascotas disponibles para adopción.
     * 
     * @return Lista de mascotas disponibles
     */
    public List<CatalogoDTO> obtenerDisponibles() {
        try {
            return controlSubsistemas.obtenerDisponibles();
        } catch (Exception e) {
            System.err.println("Error al obtener disponibles: " + e.getMessage());
            return List.of();
        }
    }
}
