package com.example.scopusservice.service.implementation;

import com.example.scopusservice.model.dto.PublicationSourceDTO;
import com.example.scopusservice.model.entity.PublicationSource;
import com.example.scopusservice.repository.PublicationSourceRepository;
import com.example.scopusservice.service.contract.PublicationSourceService;
import com.example.scopusservice.service.converter.PublicationSourceConverter;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultPublicationSourceService implements PublicationSourceService {

    private final PublicationSourceRepository publicationSourceRepository;
    private final PublicationSourceConverter publicationSourceConverter;

    public DefaultPublicationSourceService(PublicationSourceRepository publicationSourceRepository, PublicationSourceConverter publicationSourceConverter) {
        this.publicationSourceRepository = publicationSourceRepository;
        this.publicationSourceConverter = publicationSourceConverter;
    }

    @Override
    public PublicationSourceDTO savePublicationSource(PublicationSourceDTO publicationSourceDTO) {
        PublicationSource savedPublicationSource = publicationSourceRepository.save(publicationSourceConverter.fromPublicationSourceDTOToPublicationSource(publicationSourceDTO));
        return publicationSourceConverter.fromPublicationSourceToPublicationSourceDTO(savedPublicationSource);
    }

    @Override
    public void deletePublicationSource(Long publicationSourceId) {
        publicationSourceRepository.deleteById(publicationSourceId);
    }

    @Override
    public List<PublicationSourceDTO> findAllByName(String publicationSourceName) throws EntityNotFoundException {
        List<PublicationSource> publicationSources = publicationSourceRepository.findAllByName(publicationSourceName);
        if (publicationSources.isEmpty()) {
            throw new EntityNotFoundException(PublicationSource.class, "name", publicationSourceName);
        }
        return publicationSources
                .stream()
                .map(publicationSourceConverter::fromPublicationSourceToPublicationSourceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PublicationSourceDTO findById(Long publicationSourceId) throws EntityNotFoundException {
        PublicationSource publicationSource = publicationSourceRepository.findById(publicationSourceId).orElseThrow(
                () -> new EntityNotFoundException(PublicationSource.class, "id", publicationSourceId.toString())
        );
        return publicationSourceConverter.fromPublicationSourceToPublicationSourceDTO(publicationSource);
    }

    @Override
    public List<PublicationSourceDTO> findAll() {
        List<PublicationSource> publicationSources = publicationSourceRepository.findAll();
        if (publicationSources.isEmpty()) {
            throw new jakarta.persistence.EntityNotFoundException("Table PublicationSource is empty!");
        }
        return publicationSources
                .stream()
                .map(publicationSourceConverter::fromPublicationSourceToPublicationSourceDTO)
                .collect(Collectors.toList());
    }
}
