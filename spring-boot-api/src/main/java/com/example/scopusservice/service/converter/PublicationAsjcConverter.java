package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.PublicationAsjcDTO;
import com.example.scopusservice.model.entity.PublicationAsjc;
import org.springframework.stereotype.Component;

@Component
public class PublicationAsjcConverter {
    private final AsjcConverter asjcConverter;
    private final PublicationConverter publicationConverter;

    public PublicationAsjcConverter(AsjcConverter asjcConverter, PublicationConverter publicationConverter) {
        this.asjcConverter = asjcConverter;
        this.publicationConverter = publicationConverter;
    }

    public PublicationAsjc fromPublicationAsjcDTOToPublicationAsjc(PublicationAsjcDTO publicationAsjcDTO) {
        PublicationAsjc publicationAsjc = new PublicationAsjc();
        publicationAsjc.setPublicationId(publicationAsjcDTO.getPublicationId());
        publicationAsjc.setAsjcCode(publicationAsjcDTO.getAsjcCode());
        publicationAsjc.setAsjc(asjcConverter.fromAsjcDTOToAsjc(publicationAsjcDTO.getAsjcDTO()));
        publicationAsjc.setPublication(publicationConverter.fromPublicationDTOToPublication(publicationAsjcDTO.getPublicationDTO()));
        return publicationAsjc;
    }

    public PublicationAsjcDTO fromPublicationAsjcToPublicationAsjcDTO(PublicationAsjc publicationAsjc) {
        PublicationAsjcDTO publicationAsjcDTO = new PublicationAsjcDTO();
        publicationAsjcDTO.setPublicationId(publicationAsjc.getPublicationId());
        publicationAsjcDTO.setAsjcCode(publicationAsjc.getAsjcCode());
        publicationAsjcDTO.setAsjcDTO(asjcConverter.fromAsjcToAsjcDTO(publicationAsjc.getAsjc()));
        publicationAsjcDTO.setPublicationDTO(publicationConverter.fromPublicationToPublicationDTO(publicationAsjc.getPublication()));
        return publicationAsjcDTO;
    }
}
