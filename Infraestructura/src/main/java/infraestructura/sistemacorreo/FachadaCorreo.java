/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infraestructura.sistemacorreo;

import infraestructura.dto.CorreoDTO;

/**
 * Fachada para el subsistema de correo.
 * 
 * @author Josel
 */
public class FachadaCorreo implements ISistemaCorreo {

    private ControlSistemaCorreo controlCorreo;

    public FachadaCorreo() {
        this.controlCorreo = new ControlSistemaCorreo();
    }

    @Override
    public void enviarCorreo(CorreoDTO correo) throws Exception {
        controlCorreo.enviarCorreo(correo);
    }
}
