package negocio;

import gestion.mascotasarchivadas.dtos.MascotaArchivoDTO;

/**
 * Clase con reglas de negocio para la gestión de mascotas archivadas.
 * Centraliza validaciones y lógica de negocio específica del dominio de
 * archivos.
 */
public class ObjetoNegocio {

    /**
     * Constructor por defecto.
     */
    public ObjetoNegocio() {
    }

    /**
     * Verifica si una mascota puede ser eliminada permanentemente.
     * Solo se pueden eliminar mascotas que estén en estado "baja".
     * 
     * @param idMascota ID de la mascota a verificar
     * @return true si puede eliminarse, false en caso contrario
     */
    public boolean puedeEliminarPermanente(String idMascota) {
        // Validaciones básicas
        if (idMascota == null || idMascota.trim().isEmpty()) {
            System.err.println("Validación falló: ID de mascota es nulo o vacío");
            return false;
        }

        // La lógica de verificación de estado se hace en el DAO
        // Aquí solo validamos condiciones previas
        return true;
    }

    /**
     * Verifica si una mascota puede ser reactivada.
     * Solo se pueden reactivar mascotas archivadas (estado que comience con
     * "baja").
     * 
     * @param mascota DTO de la mascota a verificar
     * @return true si puede reactivarse, false en caso contrario
     */
    public boolean puedeReactivar(MascotaArchivoDTO mascota) {
        if (mascota == null) {
            System.err.println("Validación falló: mascota es nula");
            return false;
        }

        if (mascota.getId() == null || mascota.getId().trim().isEmpty()) {
            System.err.println("Validación falló: ID de mascota es nulo o vacío");
            return false;
        }

        // Verificar que esté archivada (estado comienza con "baja")
        String estado = mascota.getEstado() != null ? mascota.getEstado().toLowerCase() : "";
        if (!estado.startsWith("baja")) {
            System.err.println("Validación falló: mascota no está archivada (estado: " + mascota.getEstado() + ")");
            return false;
        }

        return true;
    }

    /**
     * Prepara una mascota para reactivación, estableciendo valores correctos.
     * 
     * @param mascota DTO de la mascota a preparar
     */
    public void prepararParaReactivacion(MascotaArchivoDTO mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }

        // Establecer estado como disponible
        mascota.setEstado("disponible");
        mascota.setDisponible(true);

        System.out.println("Mascota preparada para reactivación: " + mascota.getNombre());
    }

    /**
     * Valida que una mascota esté efectivamente archivada.
     * 
     * @param mascota DTO de la mascota a validar
     * @return true si está archivada, false en caso contrario
     */
    public boolean estaArchivada(MascotaArchivoDTO mascota) {
        if (mascota == null) {
            return false;
        }

        return "baja".equalsIgnoreCase(mascota.getEstado()) && !mascota.isDisponible();
    }

    /**
     * Valida que los datos básicos de una mascota sean correctos.
     * Útil para verificación antes de operaciones.
     * 
     * @param mascota DTO de la mascota a validar
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public void validarDatosMascota(MascotaArchivoDTO mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("Los datos de la mascota no pueden ser nulos");
        }

        if (mascota.getId() == null || mascota.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID de la mascota es obligatorio");
        }

        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la mascota es obligatorio");
        }
    }
}
