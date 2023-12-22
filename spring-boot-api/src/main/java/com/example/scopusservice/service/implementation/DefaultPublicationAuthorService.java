package com.example.scopusservice.service.implementation;

import com.example.scopusservice.model.dto.PublicationAuthorDTO;
import com.example.scopusservice.model.entity.Author;
import com.example.scopusservice.model.entity.PublicationAuthor;
import com.example.scopusservice.model.entity.PublicationAuthorPK;
import com.example.scopusservice.repository.PublicationAuthorRepository;
import com.example.scopusservice.service.contract.PublicationAuthorService;
import com.example.scopusservice.service.converter.PublicationAuthorConverter;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultPublicationAuthorService implements PublicationAuthorService {

    private final PublicationAuthorRepository publicationAuthorRepository;
    private final PublicationAuthorConverter publicationAuthorConverter;

    public DefaultPublicationAuthorService(PublicationAuthorRepository publicationAuthorRepository, PublicationAuthorConverter publicationAuthorConverter) {
        this.publicationAuthorRepository = publicationAuthorRepository;
        this.publicationAuthorConverter = publicationAuthorConverter;
    }


    @Override
    public PublicationAuthorDTO savePublicationAuthor(PublicationAuthorDTO publicationAuthorDTO) {
        PublicationAuthor savedPublicationAuthor = publicationAuthorRepository.save(publicationAuthorConverter.fromPublicationAuthorDTOToPublicationAuthor(publicationAuthorDTO));
        return publicationAuthorConverter.fromPublicationAuthorToPublicationAuthorDTO(savedPublicationAuthor);
    }

    @Override
    public void deletePublicationAuthor(PublicationAuthorPK publicationAuthorId) {
        publicationAuthorRepository.deleteById(publicationAuthorId);
    }

    @Override
    public List<PublicationAuthorDTO> findAllByAuthorId(Long authorId) throws EntityNotFoundException {
        List<PublicationAuthor> publicationAuthors = publicationAuthorRepository.findAllByAuthorId(authorId);
        if (publicationAuthors.isEmpty()) {
            throw new EntityNotFoundException(Author.class, "id", String.valueOf(authorId));
        }
        return publicationAuthors
                .stream()
                .map(publicationAuthorConverter::fromPublicationAuthorToPublicationAuthorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicationAuthorDTO> findAllByPublicationId(Long publicationId) throws EntityNotFoundException {
        List<PublicationAuthor> publicationAuthors = publicationAuthorRepository.findAllByPublicationId(publicationId);
        if (publicationAuthors.isEmpty()) {
            throw new EntityNotFoundException(Author.class, "id", String.valueOf(publicationId));
        }
        return publicationAuthors
                .stream()
                .map(publicationAuthorConverter::fromPublicationAuthorToPublicationAuthorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PublicationAuthorDTO> findAll() {
        List<PublicationAuthor> publicationAuthors = publicationAuthorRepository.findAll();
        if (publicationAuthors.isEmpty()) {
            throw new jakarta.persistence.EntityNotFoundException("Table Publication_Author is empty!");
        }
        return publicationAuthors
                .stream()
                .map(publicationAuthorConverter::fromPublicationAuthorToPublicationAuthorDTO)
                .collect(Collectors.toList());
    }
}
