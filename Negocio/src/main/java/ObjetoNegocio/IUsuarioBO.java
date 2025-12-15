/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ObjetoNegocio;

import DTOS.SolicitudAdopcionDTO;
import DTOS.UsuarioDTO;

/**
 *
 * @author Josel
 */
public interface IUsuarioBO {

    void registraUsuario(UsuarioDTO usuario);

    void actualizarUsuario(UsuarioDTO usuario);

    UsuarioDTO buscarYValidarUsuario(String correo, String password);

    void generaSolicitud(UsuarioDTO usuario, SolicitudAdopcionDTO solicitud);

    void adoptaMascota(UsuarioDTO usuario, Long idMascota);

    void registraCorreo(UsuarioDTO usuario, String correo);

    SolicitudAdopcionDTO obtieneSolicitudAdopcion(UsuarioDTO usuario);
}
