package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.PublicationKeywordDTO;
import com.example.scopusservice.model.entity.PublicationKeyword;
import org.springframework.stereotype.Component;

@Component
public class PublicationKeywordConverter {
    public PublicationKeyword fromPublicationKeywordDTOToPublicationKeyword(PublicationKeywordDTO publicationKeywordDTO) {
        PublicationKeyword publicationKeyword = new PublicationKeyword();
        publicationKeyword.setPublicationId(publicationKeywordDTO.getPublicationId());
        publicationKeyword.setKeywordId(publicationKeywordDTO.getKeywordId());
        return publicationKeyword;
    }

    public PublicationKeywordDTO fromPublicationKeywordToPublicationKeywordDTO(PublicationKeyword publicationKeyword) {
        PublicationKeywordDTO publicationKeywordDTO = new PublicationKeywordDTO();
        publicationKeywordDTO.setPublicationId(publicationKeyword.getPublicationId());
        publicationKeywordDTO.setKeywordId(publicationKeyword.getKeywordId());
        return publicationKeywordDTO;
    }
}
