package cubuscarmascotaideal.negocio.BO;

import cubuscarmascotaideal.negocio.dtos.EncuestaDTO;

import java.util.Arrays;
import java.util.List;

/**
 * Valida que las respuestas de la encuesta sean correctas
 */
public class EncuestaBO implements IEncuestaBO {

    // Los valores validos para cada campo
    private static final List<String> TAMANOS_VALIDOS = Arrays.asList("pequeño", "mediano", "grande");
    private static final List<String> NIVELES_ACTIVIDAD_VALIDOS = Arrays.asList("bajo", "medio", "alto");
    private static final List<String> COSTOS_VALIDOS = Arrays.asList("bajo", "medio", "alto");

    @Override
    public boolean validarEncuesta(EncuestaDTO encuesta) {
        // Si no hay encuesta, no es valida
        if (encuesta == null) {
            return false;
        }

        // Verifico que todos los campos sean correctos
        return esTamanoValido(encuesta.getTamano()) &&
                esNivelActividadValido(encuesta.getNivelActividad()) &&
                esCostoMantenimientoValido(encuesta.getCostoMantenimiento());
    }

    @Override
    public boolean esTamanoValido(String tamano) {
        return tamano != null && TAMANOS_VALIDOS.contains(tamano.toLowerCase());
    }

    @Override
    public boolean esNivelActividadValido(String nivelActividad) {
        return nivelActividad != null && NIVELES_ACTIVIDAD_VALIDOS.contains(nivelActividad.toLowerCase());
    }

    @Override
    public boolean esCostoMantenimientoValido(String costo) {
        return costo != null && COSTOS_VALIDOS.contains(costo.toLowerCase());
    }
}
