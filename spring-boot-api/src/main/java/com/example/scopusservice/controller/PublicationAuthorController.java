package com.example.scopusservice.controller;

import com.example.scopusservice.model.dto.AuthorDTO;
import com.example.scopusservice.model.dto.PublicationAuthorDTO;
import com.example.scopusservice.model.entity.PublicationAuthorPK;
import com.example.scopusservice.service.contract.PublicationAuthorService;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/publicationAuthor")
public class PublicationAuthorController {

    private final PublicationAuthorService publicationAuthorService;
    private static Logger log = Logger.getLogger(PublicationAuthorController.class.getName());

    public PublicationAuthorController(PublicationAuthorService publicationAuthorService) {
        this.publicationAuthorService = publicationAuthorService;
    }

    @PostMapping("/save")
    public ResponseEntity<PublicationAuthorDTO> savePublicationAuthor(@RequestBody @Valid PublicationAuthorDTO publicationAuthorDTO) {
        log.info("Handling save PUBLICATION_AUTHOR: " + publicationAuthorDTO);
        return ResponseEntity.ok(publicationAuthorService.savePublicationAuthor(publicationAuthorDTO));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PublicationAuthorDTO>> findAllPublicationAuthors() {
        log.info("Handling find all PUBLICATION_AUTHOR's request");
        return ResponseEntity.ok(publicationAuthorService.findAll());
    }

    @GetMapping("/findByAuthorId")
    public ResponseEntity<List<PublicationAuthorDTO>> findByAuthorId(@RequestParam Long authorId) throws EntityNotFoundException {
        log.info("Handling find PUBLICATION_AUTHOR by author_id request: " + authorId);
        return ResponseEntity.ok(publicationAuthorService.findAllByAuthorId(authorId));
    }

    @GetMapping("/findByPublicationId")
    public ResponseEntity<List<PublicationAuthorDTO>> findByPublicationId(@RequestParam Long publicationId) throws EntityNotFoundException {
        log.info("Handling find PUBLICATION_AUTHOR by publication_id request: " + publicationId);
        return ResponseEntity.ok(publicationAuthorService.findAllByPublicationId(publicationId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePublicationAuthor(@RequestBody PublicationAuthorPK id) {
        log.info("Handling delete PUBLICATION_AUTHOR request: " + id);
        publicationAuthorService.deletePublicationAuthor(id);
        return ResponseEntity.ok().build();
    }
}
