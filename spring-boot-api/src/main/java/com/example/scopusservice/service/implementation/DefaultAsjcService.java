package com.example.scopusservice.service.implementation;

import com.example.scopusservice.model.dto.AsjcDTO;
import com.example.scopusservice.model.entity.Asjc;
import com.example.scopusservice.repository.AsjcRepository;
import com.example.scopusservice.service.contract.AsjcService;
import com.example.scopusservice.service.converter.AsjcConverter;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DefaultAsjcService implements AsjcService {

    private final AsjcRepository asjcRepository;
    private final AsjcConverter asjcConverter;


    public DefaultAsjcService(AsjcRepository asjcRepository, AsjcConverter asjcConverter) {
        this.asjcRepository = asjcRepository;
        this.asjcConverter = asjcConverter;
    }


    @Override
    public AsjcDTO saveAsjc(AsjcDTO asjcDTO) {
        Asjc savedAsjc = asjcRepository.save(asjcConverter.fromAsjcDTOToAsjc(asjcDTO));
        return asjcConverter.fromAsjcToAsjcDTO(savedAsjc);
    }

    @Override
    public void deleteAsjc(Long asjcCode) {
        asjcRepository.deleteById(asjcCode);
    }

    @Override
    public AsjcDTO findByCode(Long asjcCode) throws EntityNotFoundException {
        Asjc asjc = asjcRepository.findById(asjcCode).orElseThrow(() -> new EntityNotFoundException(
                Asjc.class, "code", asjcCode.toString()
        ));
        return asjcConverter.fromAsjcToAsjcDTO(asjc);
    }

    @Override
    public List<AsjcDTO> findAll() {
        List<Asjc> asjcs = asjcRepository.findAll();
        if (asjcs.isEmpty()) {
            throw new jakarta.persistence.EntityNotFoundException("Table Asjc is empty!");
        }
        return asjcs
                .stream()
                .map(asjcConverter::fromAsjcToAsjcDTO)
                .collect(Collectors.toList());
    }
}
