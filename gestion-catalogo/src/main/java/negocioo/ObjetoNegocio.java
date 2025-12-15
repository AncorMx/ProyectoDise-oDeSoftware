package negocioo;

import gestion.catalogo.dtos.CatalogoDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase con reglas de negocio para la gestión del catálogo.
 * Centraliza validaciones y lógica de negocio específica del dominio.
 */
public class ObjetoNegocio {

    /**
     * Valida que los datos de una mascota sean completos y válidos.
     * 
     * @param mascota DTO con los datos a validar
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public void validarDatosMascota(CatalogoDTO mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("Los datos de la mascota no pueden ser nulos");
        }

        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la mascota es obligatorio");
        }

        if (mascota.getEspecie() == null || mascota.getEspecie().trim().isEmpty()) {
            throw new IllegalArgumentException("La especie de la mascota es obligatoria");
        }

        if (mascota.getEdad() < 0) {
            throw new IllegalArgumentException("La edad de la mascota no puede ser negativa");
        }

        if (mascota.getEdad() > 30) {
            throw new IllegalArgumentException("La edad de la mascota parece incorrecta (máximo 30 años)");
        }

        if (mascota.getEstadoSalud() == null || mascota.getEstadoSalud().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de salud es obligatorio");
        }

        if (mascota.getPersonalidad() == null || mascota.getPersonalidad().trim().isEmpty()) {
            throw new IllegalArgumentException("La personalidad de la mascota es obligatoria");
        }
    }

    /**
     * Verifica si una mascota puede ser eliminada del sistema.
     * En este caso, todas las mascotas pueden darse de baja (eliminación lógica).
     * 
     * @param idMascota ID de la mascota a verificar
     * @return true si puede eliminarse, false en caso contrario
     */
    public boolean puedeEliminarMascota(String idMascota) {
        // En este sistema, todas las mascotas pueden darse de baja (eliminación lógica)
        // Aquí podrían agregarse reglas más complejas, como verificar si tiene
        // adopciones pendientes
        return idMascota != null && !idMascota.trim().isEmpty();
    }

    /**
     * Genera un código único para el expediente médico.
     * Formato: EXP-YYYYMMDD-HHMMSS
     * 
     * @return Código de expediente generado
     */
    public String generarCodigoExpediente() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return "EXP-" + now.format(formatter);
    }

    /**
     * Valida que el estado de una mascota sea válido.
     * Estados permitidos: disponible, adoptada, en_tratamiento, baja
     * 
     * @param estado Estado a validar
     * @return true si es válido, false en caso contrario
     */
    public boolean esEstadoValido(String estado) {
        if (estado == null) {
            return false;
        }

        String estadoLower = estado.toLowerCase();
        return estadoLower.equals("disponible") ||
                estadoLower.equals("adoptada") ||
                estadoLower.equals("en_tratamiento") ||
                estadoLower.equals("baja");
    }

    /**
     * Prepara una mascota para registro, estableciendo valores por defecto.
     * 
     * @param mascota DTO de la mascota a preparar
     */
    public void prepararParaRegistro(CatalogoDTO mascota) {
        // Establecer estado inicial si no está definido
        if (mascota.getEstado() == null || mascota.getEstado().trim().isEmpty()) {
            mascota.setEstado("disponible");
        }

        // Por defecto, las nuevas mascotas están disponibles
        mascota.setDisponible(true);

        // Generar código de expediente médico si no tiene
        if (mascota.getExpedienteMedico() == null || mascota.getExpedienteMedico().trim().isEmpty()) {
            mascota.setExpedienteMedico(generarCodigoExpediente());
        }
    }

    /**
     * Verifica si una mascota está disponible para adopción.
     * 
     * @param mascota DTO de la mascota a verificar
     * @return true si está disponible, false en caso contrario
     */
    public boolean estaDisponibleParaAdopcion(CatalogoDTO mascota) {
        if (mascota == null) {
            return false;
        }

        return mascota.isDisponible() &&
                "disponible".equalsIgnoreCase(mascota.getEstado());
    }
}
