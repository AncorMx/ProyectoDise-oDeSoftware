/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ObjetoNegocio;

import DTOS.CitaDTO;
import java.time.LocalDateTime;

/**
 *
 * @author Josel
 */
public interface ICitaBO {

    void agendarCita(CitaDTO cita);

    CitaDTO buscarCitaPorId(String id);

    java.util.List<CitaDTO> buscarCitasPorUsuario(String idUsuario);

    LocalDateTime obtieneFechaHora(CitaDTO cita);

    void asociaUsuario(CitaDTO cita, String idUsuario);
}
