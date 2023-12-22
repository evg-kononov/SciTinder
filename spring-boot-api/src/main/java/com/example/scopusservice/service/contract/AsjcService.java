package com.example.scopusservice.service.contract;

import com.example.scopusservice.model.dto.AsjcDTO;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AsjcService {

    AsjcDTO saveAsjc(AsjcDTO asjcDTO);

    void deleteAsjc(Long asjcCode);

    AsjcDTO findByCode(Long asjcCode) throws EntityNotFoundException;

    List<AsjcDTO> findAll();
}
