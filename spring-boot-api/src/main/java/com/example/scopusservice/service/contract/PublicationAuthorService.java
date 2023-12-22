package com.example.scopusservice.service.contract;

import com.example.scopusservice.model.dto.PublicationAuthorDTO;
import com.example.scopusservice.model.entity.PublicationAuthorPK;
import com.example.scopusservice.util.exeption.EntityNotFoundException;

import java.util.List;

public interface PublicationAuthorService {

    PublicationAuthorDTO savePublicationAuthor(PublicationAuthorDTO publicationAuthorDTO);

    void deletePublicationAuthor(PublicationAuthorPK publicationAuthorId);

    List<PublicationAuthorDTO> findAllByAuthorId(Long authorId) throws EntityNotFoundException;

    List<PublicationAuthorDTO> findAllByPublicationId(Long publicationId) throws EntityNotFoundException;

    List<PublicationAuthorDTO> findAll();
}
