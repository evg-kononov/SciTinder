package com.example.scopusservice.service.contract;

import com.example.scopusservice.model.dto.PublicationDTO;
import com.example.scopusservice.util.exeption.EntityNotFoundException;

import java.util.List;

public interface PublicationService {

    PublicationDTO savePublication(PublicationDTO publicationDTO);

    void deletePublication(Long publicationId);

    List<PublicationDTO> findAllByName(String publicationName) throws EntityNotFoundException;

    PublicationDTO findById(Long publicationId) throws EntityNotFoundException;

    List<PublicationDTO> findAll();

}
