package negocioo;

import gestion.catalogo.dtos.ExpedienteDTO;
import gestion.catalogo.dtos.ExpedienteMedicoTemporal;

/**
 * Interfaz de negocio para la gestión de expedientes médicos
 * El expediente tiene composición fuerte con la mascota
 * 
 * @author angel
 */
public interface IExpedienteBO {

    /**
     * Crea un nuevo expediente médico para una mascota
     * 
     * @param idMascota ID de la mascota
     * @return ExpedienteDTO creado
     */
    ExpedienteDTO crearExpediente(String idMascota);

    /**
     * Crea un nuevo expediente médico con datos iniciales
     * 
     * @param idMascota ID de la mascota
     * @param datos     Datos iniciales del expediente
     * @return ExpedienteDTO creado
     */
    ExpedienteDTO crearExpedienteConDatos(String idMascota, ExpedienteMedicoTemporal datos);

    /**
     * Obtiene el expediente médico de una mascota
     * 
     * @param idMascota ID de la mascota
     * @return ExpedienteDTO o null
     */
    ExpedienteDTO obtenerExpediente(String idMascota);

    /**
     * Actualiza el expediente médico
     * 
     * @param expediente Expediente actualizado
     * @return true si se actualizó
     */
    boolean actualizarExpediente(ExpedienteDTO expediente);

    /**
     * Agrega entrada al historial
     * 
     * @param idMascota ID de la mascota
     * @param entrada   Nueva entrada
     * @return true si se agregó
     */
    boolean agregarEntradaHistorial(String idMascota, String entrada);

    /**
     * Elimina expediente (composición - cuando se elimina la mascota)
     * 
     * @param idMascota ID de la mascota
     * @return true si se eliminó
     */
    boolean eliminarExpediente(String idMascota);
}
