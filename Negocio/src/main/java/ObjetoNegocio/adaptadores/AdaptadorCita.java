/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjetoNegocio.adaptadores;

/**
 *
 * @author Josel
 */

import DTOS.CitaDTO;
import entities.Cita;

public class AdaptadorCita {

    public Cita toEntity(CitaDTO dto) {
        if (dto == null) return null;
        
        Cita entity = new Cita();
        // if (dto.getId() != null) entity.setId(new ObjectId(dto.getId()));
        
        entity.setFechaHora(dto.getFechaHora());
        
        // Mapeo de IDs
        // if (dto.getIdUsuario() != null) entity.setIdUsuario(new ObjectId(dto.getIdUsuario()));
        // if (dto.getIdMascota() != null) entity.setIdMascota(new ObjectId(dto.getIdMascota()));
        
        return entity;
    }

    public CitaDTO toDTO(Cita entity) {
        if (entity == null) return null;
        
        CitaDTO dto = new CitaDTO();
        // if (entity.getId() != null) dto.setId(entity.getId().toString());
        
        dto.setFechaHora(entity.getFechaHora());
        // if (entity.getIdUsuario() != null) dto.setIdUsuario(entity.getIdUsuario().toString());
        
        return dto;
    }
}
