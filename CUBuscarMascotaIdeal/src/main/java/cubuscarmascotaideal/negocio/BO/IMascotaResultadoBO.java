package cubuscarmascotaideal.negocio.BO;

import cubuscarmascotaideal.negocio.dtos.MascotaResultadoDTO;

/**
 * Interfaz para validación de resultados de búsqueda.
 */
public interface IMascotaResultadoBO {

    /**
     * Valida que el resultado sea válido
     * 
     * @param resultado Resultado a validar
     * @return true si es válido
     */
    boolean validarResultado(MascotaResultadoDTO resultado);

    /**
     * Verifica que el porcentaje de compatibilidad sea válido (0-100)
     */
    boolean esPorcentajeValido(double porcentaje);

    /**
     * Verifica que los datos de la mascota sean completos
     */
    boolean tieneDatosCompletos(MascotaResultadoDTO resultado);
}
