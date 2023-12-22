package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.AuthorDTO;
import com.example.scopusservice.model.entity.Author;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class AuthorConverter {

    private final OrganizationConverter organizationConverter;

    public AuthorConverter(OrganizationConverter organizationConverter) {
        this.organizationConverter = organizationConverter;
    }

    public Author fromAuthorDTOToAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setScopusId(authorDTO.getScopusId());
        author.setH_index(authorDTO.getH_index());
        if (!isNull(authorDTO.getOrganizationDTO()))
            author.setOrganization(organizationConverter.fromOrganizationDTOToOrganization(authorDTO.getOrganizationDTO()));
        return author;
    }

    public AuthorDTO fromAuthorToAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO.Builder()
                .withId(author.getId())
                .withName(author.getName())
                .withScopusId(author.getScopusId())
                .withH_index(author.getH_index())
                .build();
        if (!isNull(author.getOrganization()))
            authorDTO.setOrganizationDTO(organizationConverter.fromOrganizationToOrganizationDTO(author.getOrganization()));
        return authorDTO;
    }
}
