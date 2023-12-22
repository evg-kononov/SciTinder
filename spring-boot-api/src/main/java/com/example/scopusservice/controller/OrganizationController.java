package com.example.scopusservice.controller;

import com.example.scopusservice.model.dto.OrganizationDTO;
import com.example.scopusservice.service.contract.OrganizationServise;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    private final OrganizationServise organizationServise;
    private static Logger log = Logger.getLogger(AuthorController.class.getName());

    public OrganizationController(OrganizationServise organizationServise) {
        this.organizationServise = organizationServise;
    }

    @PostMapping("/save")
    public ResponseEntity<OrganizationDTO> saveOrganization(@RequestBody OrganizationDTO organizationDTO) {
        log.info("Handling save ORGANIZATION: " + organizationDTO);
        return ResponseEntity.ok(organizationServise.saveOrganization(organizationDTO));
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<OrganizationDTO>> findAllOrganizations() {
        log.info("Handling find all ORGANIZATION's request");
        return ResponseEntity.ok(organizationServise.findAll());
    }

    @GetMapping("/findById")
    public ResponseEntity<OrganizationDTO> findById(@RequestParam Long id) throws EntityNotFoundException {
        log.info("Handling find ORGANIZATION by id request: " + id);
        return ResponseEntity.ok(organizationServise.findById(id));
    }

    @GetMapping("/findAllByName")
    public ResponseEntity<List<OrganizationDTO>> findAllByName(@RequestParam String name) throws EntityNotFoundException {
        log.info("Handling find ORGANIZATION by name request: " + name);
        return ResponseEntity.ok(organizationServise.findAllByName(name));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        log.info("Handling delete ORGANIZATION request: " + id);
        organizationServise.deleteOrganization(id);
        return ResponseEntity.ok().build();
    }
}
