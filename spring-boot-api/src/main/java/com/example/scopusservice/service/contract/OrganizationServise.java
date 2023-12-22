package com.example.scopusservice.service.contract;

import com.example.scopusservice.model.dto.OrganizationDTO;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrganizationServise {

    OrganizationDTO saveOrganization(OrganizationDTO organizationDTO);

    void deleteOrganization(Long organizationId);

    List<OrganizationDTO> findAllByName(String organizationName) throws EntityNotFoundException;

    OrganizationDTO findById(Long organizationId) throws EntityNotFoundException;

    List<OrganizationDTO> findAll();
}
