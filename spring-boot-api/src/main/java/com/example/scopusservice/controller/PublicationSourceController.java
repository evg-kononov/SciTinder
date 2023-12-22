package com.example.scopusservice.controller;


import com.example.scopusservice.model.dto.PublicationSourceDTO;
import com.example.scopusservice.service.contract.PublicationSourceService;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/publicationSource")
public class PublicationSourceController {

    private final PublicationSourceService publicationSourceService;
    private static Logger log = Logger.getLogger(PublicationSourceController.class.getName());

    public PublicationSourceController(PublicationSourceService publicationSourceService) {
        this.publicationSourceService = publicationSourceService;
    }

    @PostMapping("/save")
    public ResponseEntity<PublicationSourceDTO> savePublicationSource(@RequestBody @Valid PublicationSourceDTO publicationSourceDTO) {
        log.info("Handling save PUBLICATION_SOURCE: " + publicationSourceDTO);
        return ResponseEntity.ok(publicationSourceService.savePublicationSource(publicationSourceDTO));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PublicationSourceDTO>> findAllPublicationSources() {
        log.info("Handling find all PUBLICATION_SOURCE's request");
        return ResponseEntity.ok(publicationSourceService.findAll());
    }

    @GetMapping("/findById")
    public ResponseEntity<PublicationSourceDTO> findById(@RequestParam Long id) throws EntityNotFoundException {
        log.info("Handling find PUBLICATION_SOURCE by id request: " + id);
        return ResponseEntity.ok(publicationSourceService.findById(id));
    }

    @GetMapping("/findAllByName")
    public ResponseEntity<List<PublicationSourceDTO>> findAllByName(@RequestParam String name) throws EntityNotFoundException {
        log.info("Handling find PUBLICATION_SOURCE by name request: " + name);
        return ResponseEntity.ok(publicationSourceService.findAllByName(name));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePublicationSource(@PathVariable Long id) {
        log.info("Handling delete PUBLICATION_SOURCE request: " + id);
        publicationSourceService.deletePublicationSource(id);
        return ResponseEntity.ok().build();
    }
}
