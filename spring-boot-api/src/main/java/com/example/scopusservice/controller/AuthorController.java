package com.example.scopusservice.controller;

import com.example.scopusservice.model.dto.AuthorDTO;
import com.example.scopusservice.service.contract.AuthorService;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;
    private static Logger log = Logger.getLogger(AuthorController.class.getName());

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/save")
    public ResponseEntity<AuthorDTO> saveAuthor(@RequestBody @Valid AuthorDTO authorDTO) {
        log.info("Handling save AUTHOR: " + authorDTO);
        return ResponseEntity.ok(authorService.saveAuthor(authorDTO));
    }

    @GetMapping("/findById")
    public ResponseEntity<AuthorDTO> findById(@RequestParam Long id) throws EntityNotFoundException {
        log.info("Handling find AUTHOR by id request: " + id);
        return ResponseEntity.ok(authorService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        log.info("Handling delete AUTHOR request: " + id);
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByScopusId")
    public ResponseEntity<AuthorDTO> findByScopusId(@RequestParam Long scopusId) throws EntityNotFoundException {
        log.info("Handling find AUTHOR by scopusId request: " + scopusId);
        return ResponseEntity.ok(authorService.findByScopusId(scopusId));
    }

    @GetMapping("/findAll")
    public ResponseEntity<Map<String, Object>> findAllAuthors(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "5") int size) throws EntityNotFoundException {
        log.info("Handling find all AUTHOR's request");
        return authorService.findAll(page, size);
    }

    @GetMapping("/findByNameLike")
    public ResponseEntity<Map<String, Object>> findByNameLike(@RequestParam String firstname,
                                                              @RequestParam String lastname,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "5") int size) throws EntityNotFoundException {
        log.info("Handling find AUTHOR by name like request: " + firstname + " " + lastname);
        return authorService.findByNameLike(firstname, lastname, page, size);
    }

    @GetMapping("/findByNameContaining")
    public ResponseEntity<Map<String, Object>> findByNameContaining(@RequestParam String name,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "5") int size) throws EntityNotFoundException {
        log.info("Handling find AUTHOR by name containing request: " + name);
        return authorService.findByNameContaining(name, page, size);
    }
}
