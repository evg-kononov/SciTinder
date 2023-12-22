package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.PublicationSourceDTO;
import com.example.scopusservice.model.entity.PublicationSource;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class PublicationSourceConverter {

    private final PublisherConverter publisherConverter;

    public PublicationSourceConverter(PublisherConverter publisherConverter) {
        this.publisherConverter = publisherConverter;
    }

    public PublicationSource fromPublicationSourceDTOToPublicationSource(PublicationSourceDTO publicationSourceDTO) {
        PublicationSource publicationSource = new PublicationSource();
        publicationSource.setId(publicationSourceDTO.getId());
        publicationSource.setName(publicationSourceDTO.getName());
        publicationSource.setType(publicationSourceDTO.getType());
        publicationSource.setIssn(publicationSourceDTO.getIssn());
        publicationSource.setScopusId(publicationSourceDTO.getScopusId());
        if (!isNull(publicationSourceDTO.getPublisherDTO()))
            publicationSource.setPublisher(publisherConverter.fromPublisherDTOToPublisher(publicationSourceDTO.getPublisherDTO()));
        return publicationSource;
    }

    public PublicationSourceDTO fromPublicationSourceToPublicationSourceDTO(PublicationSource publicationSource) {
        PublicationSourceDTO publicationSourceDTO = new PublicationSourceDTO();
        publicationSourceDTO.setId(publicationSource.getId());
        publicationSourceDTO.setName(publicationSource.getName());
        publicationSourceDTO.setType(publicationSource.getType());
        publicationSourceDTO.setIssn(publicationSource.getIssn());
        publicationSourceDTO.setScopusId(publicationSource.getScopusId());
        if (!isNull(publicationSource.getPublisher()))
            publicationSourceDTO.setPublisherDTO(publisherConverter.fromPublisherToPublisherDTO(publicationSource.getPublisher()));
        return publicationSourceDTO;
    }
}
