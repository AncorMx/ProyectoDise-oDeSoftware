/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjetoNegocio.adaptadores;

import DTOS.InfoViviendaDTO;
import entities.InfoVivienda;

/**
 *
 * @author Josel
 */
public class AdaptadorInfoVivienda {

    // Convierte de DTO (Negocio) a Entidad (Base de Datos)
    public InfoVivienda toEntity(InfoViviendaDTO dto) {
        if (dto == null) return null;
        
        InfoVivienda entity = new InfoVivienda();
        
        // Asumo que estos son tus campos. Si alguno te marca rojo,
        // solo cambia el nombre (ej. setCalle por setDireccion)
        entity.setCondicionesHogar(dto.getCondicionesHogar());
        entity.setTiempoDisponibilidad(dto.getTiempoDisponibilidad());
        entity.setTieneNinos(dto.isTieneNinos());
        entity.setTieneOtrasMascotas(dto.isTieneOtrasMascotas());
        entity.setTipoVivienda(dto.getTipoVivienda());
        
        return entity;
    }

    // Convierte de Entidad (Base de Datos) a DTO (Negocio)
    public InfoViviendaDTO toDTO(InfoVivienda entity) {
        if (entity == null) return null;
        
        InfoViviendaDTO dto = new InfoViviendaDTO();
        
        dto.setCondicionesHogar(entity.getCondicionesHogar());
        dto.setTiempoDisponibilidad(entity.getTiempoDisponibilidad());
        dto.setTieneNinos(entity.isTieneNinos());
        dto.setTieneOtrasMascotas(dto.isTieneOtrasMascotas());
        dto.setTipoVivienda(dto.getTipoVivienda());
        
        return dto;
    }
}