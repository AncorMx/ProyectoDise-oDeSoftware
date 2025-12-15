/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ObjetoNegocio;

import DTOS.MascotaDTO;

/**
 *
 * @author Josel
 */
public interface IMascotaBO {

    void registrarMascota(MascotaDTO mascota);

    MascotaDTO buscarMascotaPorId(String id);

    java.util.List<MascotaDTO> buscarTodasLasMascotas();

    java.util.List<MascotaDTO> buscarMascotasDisponibles();

    java.util.List<MascotaDTO> buscarMascotasDisponiblesPorEspecie(String especie);

    void actualizarMascota(MascotaDTO mascota);

    void actualizaEstadoSalud(MascotaDTO mascota, String nuevoEstado);

    void registraDueño(MascotaDTO mascota, Long idDueño);

    /**
     * Elimina una mascota y su expediente médico (composición)
     * 
     * @param id ID de la mascota a eliminar
     * @return true si se eliminó correctamente
     */
    boolean eliminarMascota(String id);
}
