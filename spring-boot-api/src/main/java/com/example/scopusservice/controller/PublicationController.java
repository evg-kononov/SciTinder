package com.example.scopusservice.controller;

import com.example.scopusservice.model.dto.PublicationDTO;
import com.example.scopusservice.service.contract.PublicationService;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/publication")
public class PublicationController {

    private final PublicationService publicationService;
    private static Logger log = Logger.getLogger(PublicationController.class.getName());

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping("/save")
    public ResponseEntity<PublicationDTO> savePublication(@RequestBody @Valid PublicationDTO publicationDTO) {
        log.info("Handling save PUBLICATION: " + publicationDTO);
        return ResponseEntity.ok(publicationService.savePublication(publicationDTO));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PublicationDTO>> findAllPublications() {
        log.info("Handling find all PUBLICATION's request");
        return ResponseEntity.ok(publicationService.findAll());
    }

    @GetMapping("/findById")
    public ResponseEntity<PublicationDTO> findById(@RequestParam Long id) throws EntityNotFoundException {
        log.info("Handling find PUBLICATION by id request: " + id);
        return ResponseEntity.ok(publicationService.findById(id));
    }

    @GetMapping("/findAllByName")
    public ResponseEntity<List<PublicationDTO>> findAllByName(@RequestParam String name) throws EntityNotFoundException {
        log.info("Handling find PUBLICATION by name request: " + name);
        return ResponseEntity.ok(publicationService.findAllByName(name));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        log.info("Handling delete PUBLICATION request: " + id);
        publicationService.deletePublication(id);
        return ResponseEntity.ok().build();
    }
}
