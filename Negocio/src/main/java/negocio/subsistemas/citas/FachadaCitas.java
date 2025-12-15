/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.subsistemas.citas;

import DTOS.CitaDTO;
import ObjetoNegocio.ICitaBO;
import ObjetoNegocio.CitaBO;
import java.util.List;

/**
 * Fachada para el subsistema de citas
 * 
 * @author System
 */
public class FachadaCitas implements ICitas {

    private ICitaBO citaBO;

    public FachadaCitas() {
        this.citaBO = new CitaBO();
    }

    @Override
    public void agendarCita(CitaDTO cita) throws Exception {
        citaBO.agendarCita(cita);
    }

    @Override
    public CitaDTO buscarCitaPorId(String id) throws Exception {
        return citaBO.buscarCitaPorId(id);
    }

    @Override
    public List<CitaDTO> buscarCitasPorUsuario(String idUsuario) throws Exception {
        return citaBO.buscarCitasPorUsuario(idUsuario);
    }
}
