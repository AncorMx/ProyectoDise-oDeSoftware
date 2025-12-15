package cubuscarmascotaideal.negocio.BO;

import cubuscarmascotaideal.negocio.dtos.MascotaResultadoDTO;

/**
 * Valida que los resultados de busqueda sean correctos
 */
public class MascotaResultadoBO implements IMascotaResultadoBO {

    @Override
    public boolean validarResultado(MascotaResultadoDTO resultado) {
        if (resultado == null)
            return false;

        // Verifico porcentaje y datos basicos
        return esPorcentajeValido(resultado.getPorcentajeCompatibilidad()) &&
                resultado.getId() != null && !resultado.getId().trim().isEmpty() &&
                resultado.getNombre() != null && !resultado.getNombre().trim().isEmpty() &&
                resultado.getEspecie() != null && !resultado.getEspecie().trim().isEmpty();
    }

    @Override
    public boolean esPorcentajeValido(double porcentaje) {
        return porcentaje >= 0.0 && porcentaje <= 100.0;
    }

    @Override
    public boolean tieneDatosCompletos(MascotaResultadoDTO resultado) {
        // Llamo a validarResultado que ya tiene esta logica
        return validarResultado(resultado);
    }
}
