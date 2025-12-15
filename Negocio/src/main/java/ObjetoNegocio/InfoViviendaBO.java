/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjetoNegocio;

import DTOS.InfoViviendaDTO;
import DTOS.UsuarioDTO;

/**
 *
 * @author Josel
 */
public class InfoViviendaBO implements IInfoViviendaBO {

    @Override
    public void registraDescripcion(UsuarioDTO usuario, String descripcion) {
        if (usuario != null) {
            if (usuario.getInfoVivienda() == null) {
                usuario.setInfoVivienda(new InfoViviendaDTO());
            }
            usuario.getInfoVivienda().setCondicionesHogar(descripcion);
        }
    }

    @Override
    public void capturaImagen(UsuarioDTO usuario, String urlImagen) {
        // Lógica para asociar la evidencia fotográfica (simulada)
        System.out.println("Evidencia guardada en: " + urlImagen + " para usuario " + usuario.getId());
    }

    @Override
    public String obtieneNumeroInt(UsuarioDTO usuario) {
        // Implementación dummy o real de extracción de dirección
        return "N/A";
    }

    @Override
    public String obtienenNumeroE(UsuarioDTO usuario) {
        // Implementación dummy o real
        return "S/N";
    }
}
