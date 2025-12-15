/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio.subsistemas.iniciosesion;

import DTOS.UsuarioDTO;

/**
 * Interfaz para el subsistema de inicio de sesión
 * 
 * @author System
 */
public interface IInicioSesion {
    UsuarioDTO iniciarSesion(String correo, String password) throws Exception;

    void registrarUsuario(UsuarioDTO usuario) throws Exception;

    void actualizarUsuario(UsuarioDTO usuario) throws Exception;
}
