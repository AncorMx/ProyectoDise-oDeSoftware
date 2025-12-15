/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ObjetoNegocio;

import DTOS.UsuarioDTO;

/**
 *
 * @author Josel
 */
public interface IInfoViviendaBO {

    void registraDescripcion(UsuarioDTO usuario, String descripcion);

    void capturaImagen(UsuarioDTO usuario, String urlImagen);

    String obtieneNumeroInt(UsuarioDTO usuario);

    String obtienenNumeroE(UsuarioDTO usuario);
}
