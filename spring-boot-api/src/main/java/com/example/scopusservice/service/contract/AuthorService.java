package com.example.scopusservice.service.contract;

import com.example.scopusservice.model.dto.AuthorDTO;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AuthorService {

    AuthorDTO saveAuthor(AuthorDTO authorDTO);

    void deleteAuthor(Long authorId);

    AuthorDTO findById(Long authorId) throws EntityNotFoundException;

    AuthorDTO findByScopusId(Long scopusId) throws EntityNotFoundException;

    ResponseEntity<Map<String, Object>> findAll(int page, int size) throws EntityNotFoundException;

    ResponseEntity<Map<String, Object>> findByNameLike(String firstname,
                                                       String lastname,
                                                       int page,
                                                       int size) throws EntityNotFoundException;

    ResponseEntity<Map<String, Object>> findByNameContaining(String name,
                                                             int page,
                                                             int size) throws EntityNotFoundException;
}