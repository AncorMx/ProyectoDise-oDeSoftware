/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjetoNegocio;

import DTOS.UsuarioDTO;

/**
 *
 * @author Josel
 */
public class InfoPersonalBO implements IInfoPersonalBO {

    @Override
    public String obtieneNombre(UsuarioDTO usuario) {
        if (usuario != null && usuario.getInfoPersonal() != null) {
            return usuario.getInfoPersonal().getNombre();
        }
        return "";
    }

    @Override
    public double obtieneIngreso(UsuarioDTO usuario) {
        // Si este dato no está en el DTO, se retorna un valor por defecto o calculado
        return 0.0;
    }

    @Override
    public UsuarioDTO obtieneUsuario(String idUsuario) {
        // Lógica para recuperar usuario (normalmente llamaría a un DAO)
        // Por ahora retornamos una instancia vacía para cumplir el contrato
        UsuarioDTO user = new UsuarioDTO();
        user.setId(idUsuario);
        return user;
    }

    @Override
    public int obtieneEdad(UsuarioDTO usuario) {
        // Lógica para calcular edad (ej. desde CURP o fecha nacimiento)
        return 18;
    }
}
