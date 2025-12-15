/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package infraestructura.sistemacorreo;

import infraestructura.dto.CorreoDTO;

/**
 * Interfaz para el subsistema de envío de correos.
 * 
 * @author Josel
 */
public interface ISistemaCorreo {
    /**
     * Envía un correo electrónico.
     * 
     * @param correo DTO con la información del correo.
     * @throws Exception Si ocurre un error en el envío.
     */
    void enviarCorreo(CorreoDTO correo) throws Exception;
}
