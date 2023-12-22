package com.example.scopusservice.service.implementation;

import com.example.scopusservice.model.dto.PublicationDTO;
import com.example.scopusservice.model.entity.Publication;
import com.example.scopusservice.repository.PublicationRepository;
import com.example.scopusservice.service.contract.PublicationService;
import com.example.scopusservice.service.converter.PublicationConverter;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultPublicationService implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final PublicationConverter publicationConverter;

    public DefaultPublicationService(PublicationRepository publicationRepository, PublicationConverter publicationConverter) {
        this.publicationRepository = publicationRepository;
        this.publicationConverter = publicationConverter;
    }

    @Override
    public PublicationDTO savePublication(PublicationDTO publicationDTO) {
        Publication savedPublication = publicationRepository.save(publicationConverter.fromPublicationDTOToPublication(publicationDTO));
        return publicationConverter.fromPublicationToPublicationDTO(savedPublication);
    }

    @Override
    public void deletePublication(Long publicationId) {
        publicationRepository.deleteById(publicationId);
    }

    @Override
    public List<PublicationDTO> findAllByName(String publicationName) throws EntityNotFoundException {
        List<Publication> publications = publicationRepository.findAllByName(publicationName);
        if (publications.isEmpty()) {
            throw new EntityNotFoundException(Publication.class, "name", publicationName);
        }
        return publications
                .stream()
                .map(publicationConverter::fromPublicationToPublicationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PublicationDTO findById(Long publicationId) throws EntityNotFoundException {
        Publication publication = publicationRepository.findById(publicationId).orElseThrow(() -> new EntityNotFoundException(
                Publication.class, "id", publicationId.toString()
        ));
        return publicationConverter.fromPublicationToPublicationDTO(publication);
    }

    @Override
    public List<PublicationDTO> findAll() {
        List<Publication> publications = publicationRepository.findAll();
        if (publications.isEmpty()) {
            throw new jakarta.persistence.EntityNotFoundException("Table Publication is empty!");
        }
        return publications
                .stream()
                .map(publicationConverter::fromPublicationToPublicationDTO)
                .collect(Collectors.toList());
    }
}
