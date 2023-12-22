package com.example.scopusservice.controller;

import com.example.scopusservice.model.dto.AsjcDTO;
import com.example.scopusservice.service.contract.AsjcService;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/asjc")
public class AsjcController {
    private final AsjcService asjcService;
    private static Logger log = Logger.getLogger(AsjcController.class.getName());

    public AsjcController(AsjcService asjcService) {
        this.asjcService = asjcService;
    }

    @PostMapping("/save")
    public ResponseEntity<AsjcDTO> saveAsjc(@RequestBody AsjcDTO asjcDTO) {
        log.info("Handling save ASJC: " + asjcDTO);
        return ResponseEntity.ok(asjcService.saveAsjc(asjcDTO));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AsjcDTO>> findAllAsjcs() throws EntityNotFoundException {
        log.info("Handling find all ASJC's request");
        return ResponseEntity.ok(asjcService.findAll());
    }

    @GetMapping("/findByCode")
    public ResponseEntity<AsjcDTO> findByCode(@RequestParam Long code) throws EntityNotFoundException {
        log.info("Handling find ASJC by code request: " + code);
        return ResponseEntity.ok(asjcService.findByCode(code));
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> deleteAsjc(@PathVariable Long code) {
        log.info("Handling delete ASJC request: " + code);
        asjcService.deleteAsjc(code);
        return ResponseEntity.ok().build();
    }
}
