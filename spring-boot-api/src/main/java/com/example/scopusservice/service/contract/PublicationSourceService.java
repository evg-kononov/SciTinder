package com.example.scopusservice.service.contract;

import com.example.scopusservice.model.dto.PublicationSourceDTO;
import com.example.scopusservice.util.exeption.EntityNotFoundException;

import java.util.List;

public interface PublicationSourceService {

    PublicationSourceDTO savePublicationSource(PublicationSourceDTO publicationSourceDTO);

    void deletePublicationSource(Long publicationSourceId);

    List<PublicationSourceDTO> findAllByName(String publicationSourceName) throws EntityNotFoundException;

    PublicationSourceDTO findById(Long publicationSourceId) throws EntityNotFoundException;

    List<PublicationSourceDTO> findAll();
}
