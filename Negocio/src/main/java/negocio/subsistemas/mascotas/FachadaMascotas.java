/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.subsistemas.mascotas;

import DTOS.MascotaDTO;
import ObjetoNegocio.IMascotaBO;
import ObjetoNegocio.MascotaBO;
import java.util.List;

/**
 * Fachada para el subsistema de mascotas
 * 
 * @author System
 */
public class FachadaMascotas implements IMascotas {

    private IMascotaBO mascotaBO;

    public FachadaMascotas() {
        this.mascotaBO = new MascotaBO();
    }

    @Override
    public void registrarMascota(MascotaDTO mascota) throws Exception {
        mascotaBO.registrarMascota(mascota);
    }

    @Override
    public MascotaDTO buscarMascotaPorId(String id) throws Exception {
        return mascotaBO.buscarMascotaPorId(id);
    }

    @Override
    public List<MascotaDTO> buscarTodasLasMascotas() throws Exception {
        return mascotaBO.buscarTodasLasMascotas();
    }

    @Override
    public List<MascotaDTO> buscarMascotasDisponibles() throws Exception {
        return mascotaBO.buscarMascotasDisponibles();
    }

    @Override
    public List<MascotaDTO> buscarMascotasDisponiblesPorEspecie(String especie) throws Exception {
        return mascotaBO.buscarMascotasDisponiblesPorEspecie(especie);
    }

    @Override
    public void actualizarMascota(MascotaDTO mascota) throws Exception {
        mascotaBO.actualizarMascota(mascota);
    }
}
