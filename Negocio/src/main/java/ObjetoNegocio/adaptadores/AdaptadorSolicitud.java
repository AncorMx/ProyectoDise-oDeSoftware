/*
 * AdaptadorSolicitud.java
 */
package ObjetoNegocio.adaptadores;

import DTOS.SolicitudAdopcionDTO;
import entities.SolicitudAdopcion;
import entities.Usuario;

public class AdaptadorSolicitud {
    
    private AdaptadorUsuario adpUsuario = new AdaptadorUsuario();
    private AdaptadorMascota adpMascota = new AdaptadorMascota();
    private AdaptadorRazonesAntecedentes adpRazones = new AdaptadorRazonesAntecedentes();

    public SolicitudAdopcion toEntity(SolicitudAdopcionDTO dto) {
        if (dto == null) return null;
        
        SolicitudAdopcion entity = new SolicitudAdopcion();
        
        Usuario usuarioEntity = adpUsuario.toEntity(dto.getUsuario());
        if (usuarioEntity != null) {
            entity.setIdUsuario(usuarioEntity.getId());
        }

        entity.setFechaSolicitud(dto.getFechaSolicitud());
        entity.setEstado(dto.getEstado());

        entity.setRazones(adpRazones.toEntity(dto.getRazones()));
        
        return entity;
    }

    public SolicitudAdopcionDTO toDTO(SolicitudAdopcion entity) {
        if (entity == null) return null;
        
        SolicitudAdopcionDTO dto = new SolicitudAdopcionDTO();
 
        dto.setFechaSolicitud(entity.getFechaSolicitud());
        dto.setEstado(entity.getEstado());
        
        dto.setRazones(adpRazones.toDTO(entity.getRazones()));
        
        return dto;
    }
}