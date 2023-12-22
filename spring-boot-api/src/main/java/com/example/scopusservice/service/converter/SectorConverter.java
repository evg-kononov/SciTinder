package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.SectorDTO;
import com.example.scopusservice.model.entity.Sector;
import org.springframework.stereotype.Component;

@Component
public class SectorConverter {
    public Sector fromSectorDTOToSector(SectorDTO sectorDTO) {
        Sector sector = new Sector();
        sector.setId(sectorDTO.getId());
        sector.setName(sectorDTO.getName());
        return sector;
    }

    public SectorDTO fromSectorToSectorDTO(Sector sector) {
        SectorDTO sectorDTO = new SectorDTO();
        sectorDTO.setId(sector.getId());
        sectorDTO.setName(sector.getName());
        return sectorDTO;
    }
}
