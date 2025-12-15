/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio.subsistemas.mascotas;

import DTOS.MascotaDTO;
import java.util.List;

/**
 * Interfaz para el subsistema de mascotas
 * 
 * @author System
 */
public interface IMascotas {

    void registrarMascota(MascotaDTO mascota) throws Exception;

    MascotaDTO buscarMascotaPorId(String id) throws Exception;

    List<MascotaDTO> buscarTodasLasMascotas() throws Exception;

    List<MascotaDTO> buscarMascotasDisponibles() throws Exception;

    List<MascotaDTO> buscarMascotasDisponiblesPorEspecie(String especie) throws Exception;

    void actualizarMascota(MascotaDTO mascota) throws Exception;

}
