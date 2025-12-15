/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjetoNegocio.adaptadores;

/**
 *
 * @author Josel
 */

import DTOS.UsuarioDTO;
import entities.Usuario;
// import org.bson.types.ObjectId;

public class AdaptadorUsuario {

    // Composición de adaptadores
    private AdaptadorInfoPersonal adpPersonal = new AdaptadorInfoPersonal();
    private AdaptadorInfoVivienda adpVivienda = new AdaptadorInfoVivienda();

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;
        
        Usuario entity = new Usuario();
        // ID Mapping...
        entity.setContrasena(dto.getContrasena());

        // Delegamos la conversión a los adaptadores específicos
        entity.setInfoPersonal(adpPersonal.toEntity(dto.getInfoPersonal()));
        entity.setInfoVivienda(adpVivienda.toEntity(dto.getInfoVivienda()));
        
        return entity;
    }

    public UsuarioDTO toDTO(Usuario entity) {
        if (entity == null) return null;
        
        UsuarioDTO dto = new UsuarioDTO();
        // ID Mapping...
        dto.setContrasena(entity.getContrasena());

        // Delegamos la conversión inversa
        dto.setInfoPersonal(adpPersonal.toDTO(entity.getInfoPersonal()));
        dto.setInfoVivienda(adpVivienda.toDTO(entity.getInfoVivienda()));
        
        return dto;
    }
}
