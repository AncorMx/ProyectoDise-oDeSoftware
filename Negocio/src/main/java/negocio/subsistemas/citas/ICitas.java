/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio.subsistemas.citas;

import DTOS.CitaDTO;
import java.util.List;

/**
 * Interfaz para el subsistema de citas
 * 
 * @author System
 */
public interface ICitas {

    void agendarCita(CitaDTO cita) throws Exception;

    CitaDTO buscarCitaPorId(String id) throws Exception;

    List<CitaDTO> buscarCitasPorUsuario(String idUsuario) throws Exception;

}
