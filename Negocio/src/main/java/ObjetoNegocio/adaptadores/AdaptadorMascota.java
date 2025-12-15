/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjetoNegocio.adaptadores;

/**
 *
 * @author Josel
 */

import DTOS.MascotaDTO;
import entities.Mascota;

public class AdaptadorMascota {

    public Mascota toEntity(MascotaDTO dto) {
        if (dto == null) return null;
        
        Mascota entity = new Mascota();
        // if (dto.getId() != null) entity.setId(new ObjectId(dto.getId()));
        
        entity.setNombre(dto.getNombre());
        entity.setEspecie(dto.getEspecie());
        entity.setEstadoSalud(dto.getEstadoSalud());
        entity.setPersonalidad(dto.getPersonalidad());
        entity.setUrlImagen(dto.getUrlImagen());
        entity.setEdad(dto.getEdad());
        entity.setDisponible(dto.isDisponible());
        
        return entity;
    }

    public MascotaDTO toDTO(Mascota entity) {
        if (entity == null) return null;
        
        MascotaDTO dto = new MascotaDTO();
        // if (entity.getId() != null) dto.setId(entity.getId().toString());
        
        dto.setNombre(entity.getNombre());
        dto.setEspecie(entity.getEspecie());
        dto.setEstadoSalud(entity.getEstadoSalud());
        dto.setPersonalidad(entity.getPersonalidad());
        dto.setUrlImagen(entity.getUrlImagen());
        dto.setEdad(entity.getEdad());
        dto.setDisponible(entity.isDisponible());
        
        return dto;
    }
}
