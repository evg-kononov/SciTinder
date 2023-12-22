package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.AsjcDTO;
import com.example.scopusservice.model.entity.Asjc;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AsjcConverter {

    public Asjc fromAsjcDTOToAsjc(AsjcDTO asjcDTO) {
        Asjc asjc = new Asjc();
        asjc.setCode(asjcDTO.getCode());
        asjc.setField(asjcDTO.getField());
        asjc.setSubjectArea(asjcDTO.getSubjectArea());
        return asjc;
    }

    public AsjcDTO fromAsjcToAsjcDTO(Asjc asjc) {
        AsjcDTO asjcDTO = new AsjcDTO();
        asjcDTO.setCode(asjc.getCode());
        asjcDTO.setField(asjc.getField());
        asjcDTO.setSubjectArea(asjc.getSubjectArea());
        return asjcDTO;
    }
}
