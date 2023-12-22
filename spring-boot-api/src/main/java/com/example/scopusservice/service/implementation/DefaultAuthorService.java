package com.example.scopusservice.service.implementation;

import com.example.scopusservice.model.dto.AuthorDTO;
import com.example.scopusservice.model.entity.Author;
import com.example.scopusservice.repository.AuthorRepository;
import com.example.scopusservice.service.contract.AuthorService;
import com.example.scopusservice.service.converter.AuthorConverter;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DefaultAuthorService implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;

    public DefaultAuthorService(AuthorRepository authorRepository, AuthorConverter authorConverter) {
        this.authorRepository = authorRepository;
        this.authorConverter = authorConverter;
    }

    @Override
    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {
        Author savedAuthor = authorRepository.save(authorConverter.fromAuthorDTOToAuthor(authorDTO));
        return authorConverter.fromAuthorToAuthorDTO(savedAuthor);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public AuthorDTO findById(Long authorId) throws EntityNotFoundException {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException(
                Author.class, "id", authorId.toString()
        ));
        return authorConverter.fromAuthorToAuthorDTO(author);
    }

    @Override
    public AuthorDTO findByScopusId(Long scopusId) throws EntityNotFoundException {
        Author author = authorRepository.findAuthorByScopusId(scopusId);
        if (author == null) {
            throw new EntityNotFoundException(Author.class, "scopus_id", scopusId.toString());
        }
        return authorConverter.fromAuthorToAuthorDTO(author);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAll(int page, int size) throws EntityNotFoundException {
        List<AuthorDTO> authorsDTO;
        Pageable paging = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "hIndex").and(Sort.by("id")));
        Page<Author> pageAuthors = authorRepository.findAll(paging);

        authorsDTO = pageAuthors
                .getContent()
                .stream()
                .map(authorConverter::fromAuthorToAuthorDTO)
                .collect(Collectors.toList());

        if (authorsDTO.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("authors", authorsDTO);
        response.put("currentPage", pageAuthors.getNumber());
        response.put("pageSize", size);
        response.put("totalItems", pageAuthors.getTotalElements());
        response.put("totalPages", pageAuthors.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findByNameLike(String firstname,
                                                              String lastname,
                                                              int page,
                                                              int size) throws EntityNotFoundException {
        List<AuthorDTO> authorsDTO;
        Pageable paging = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "hIndex").and(Sort.by("id")));
        Page<Author> pageAuthors = authorRepository.findByNameAndName(firstname, lastname, paging);

        authorsDTO = pageAuthors
                .getContent()
                .stream()
                .map(authorConverter::fromAuthorToAuthorDTO)
                .collect(Collectors.toList());

        if (authorsDTO.isEmpty()) {
            throw new EntityNotFoundException(Author.class, "name like", firstname + " " + lastname);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("authors", authorsDTO);
        response.put("currentPage", pageAuthors.getNumber());
        response.put("pageSize", size);
        response.put("totalItems", pageAuthors.getTotalElements());
        response.put("totalPages", pageAuthors.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> findByNameContaining(String name,
                                                                    int page,
                                                                    int size) throws EntityNotFoundException {
        List<AuthorDTO> authorsDTO;
        Pageable paging = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "hIndex").and(Sort.by("id")));
        Page<Author> pageAuthors = authorRepository.findByName(name, paging);

        authorsDTO = pageAuthors
                .getContent()
                .stream()
                .map(authorConverter::fromAuthorToAuthorDTO)
                .collect(Collectors.toList());

        if (authorsDTO.isEmpty()) {
            throw new EntityNotFoundException(Author.class, "name containing", name);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("authors", authorsDTO);
        response.put("currentPage", pageAuthors.getNumber());
        response.put("pageSize", size);
        response.put("totalItems", pageAuthors.getTotalElements());
        response.put("totalPages", pageAuthors.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}