package cubuscarmascotaideal.negocio.BO;

import cubuscarmascotaideal.negocio.dtos.EncuestaDTO;

/**
 * Interfaz para validación de encuestas de búsqueda.
 */
public interface IEncuestaBO {

    /**
     * Valida que los datos de la encuesta sean correctos
     * 
     * @param encuesta Datos de la encuesta
     * @return true si es válida
     */
    boolean validarEncuesta(EncuestaDTO encuesta);

    /**
     * Verifica que el tamaño sea válido
     */
    boolean esTamanoValido(String tamano);

    /**
     * Verifica que el nivel de actividad sea válido
     */
    boolean esNivelActividadValido(String nivelActividad);

    /**
     * Verifica que el costo de mantenimiento sea válido
     */
    boolean esCostoMantenimientoValido(String costo);
}
