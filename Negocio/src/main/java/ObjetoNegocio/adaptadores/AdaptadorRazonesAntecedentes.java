/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjetoNegocio.adaptadores;

/**
 *
 * @author Josel
 */

import DTOS.RazonesAntecedentesDTO;
import entities.RazonesAntecedentes;

public class AdaptadorRazonesAntecedentes {

    public RazonesAntecedentes toEntity(RazonesAntecedentesDTO dto) {
        if (dto == null) return null;
        
        RazonesAntecedentes entity = new RazonesAntecedentes();
        entity.setMotivoAdopcion(dto.getMotivoAdopcion());
        entity.setAntecedentesMascotas(dto.getAntecedentesMascotas());
        entity.setAceptaSeguimiento(dto.isAceptaSeguimiento());
        
        return entity;
    }

    public RazonesAntecedentesDTO toDTO(RazonesAntecedentes entity) {
        if (entity == null) return null;
        
        RazonesAntecedentesDTO dto = new RazonesAntecedentesDTO();
        dto.setMotivoAdopcion(entity.getMotivoAdopcion());
        dto.setAntecedentesMascotas(entity.getAntecedentesMascotas());
        dto.setAceptaSeguimiento(entity.isAceptaSeguimiento());
        
        return dto;
    }
}
