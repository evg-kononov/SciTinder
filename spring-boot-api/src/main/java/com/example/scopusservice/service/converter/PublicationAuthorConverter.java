package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.PublicationAuthorDTO;
import com.example.scopusservice.model.entity.PublicationAuthor;
import org.springframework.stereotype.Component;

@Component
public class PublicationAuthorConverter {
    public PublicationAuthor fromPublicationAuthorDTOToPublicationAuthor(PublicationAuthorDTO publicationAuthorDTO) {
        PublicationAuthor publicationAuthor = new PublicationAuthor();
        publicationAuthor.setPublicationId(publicationAuthorDTO.getPublicationId());
        publicationAuthor.setAuthorId(publicationAuthorDTO.getAuthorId());
        return publicationAuthor;
    }

    public PublicationAuthorDTO fromPublicationAuthorToPublicationAuthorDTO(PublicationAuthor publicationAuthor) {
        PublicationAuthorDTO publicationAuthorDTO = new PublicationAuthorDTO();
        publicationAuthorDTO.setPublicationId(publicationAuthor.getPublicationId());
        publicationAuthorDTO.setAuthorId(publicationAuthor.getAuthorId());
        return publicationAuthorDTO;
    }
}
