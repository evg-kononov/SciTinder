package com.example.scopusservice.service.implementation;

import com.example.scopusservice.model.dto.OrganizationDTO;
import com.example.scopusservice.model.entity.Organization;
import com.example.scopusservice.repository.OrganizationRepository;
import com.example.scopusservice.service.contract.OrganizationServise;
import com.example.scopusservice.service.converter.OrganizationConverter;
import com.example.scopusservice.util.exeption.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DefaultOrganizationService implements OrganizationServise {

    private final OrganizationRepository organizationRepository;
    private final OrganizationConverter organizationConverter;

    public DefaultOrganizationService(OrganizationRepository organizationRepository, OrganizationConverter organizationConverter) {
        this.organizationRepository = organizationRepository;
        this.organizationConverter = organizationConverter;
    }


    @Override
    public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) {
        Organization savedOrganization = organizationRepository.save(organizationConverter.fromOrganizationDTOToOrganization(organizationDTO));
        return organizationConverter.fromOrganizationToOrganizationDTO(savedOrganization);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationRepository.deleteById(organizationId);
    }

    @Override
    public List<OrganizationDTO> findAllByName(String organizationName) throws EntityNotFoundException {
        List<Organization> organizations = organizationRepository.findAllByName(organizationName);
        if (organizations.isEmpty()) {
            throw new EntityNotFoundException(Organization.class, "name", organizationName);
        }
        return organizations
                .stream()
                .map(organizationConverter::fromOrganizationToOrganizationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDTO findById(Long organizationId) throws EntityNotFoundException {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() ->
                new EntityNotFoundException(Organization.class, "id", organizationId.toString()));
        return organizationConverter.fromOrganizationToOrganizationDTO(organization);
    }

    @Override
    public List<OrganizationDTO> findAll() {
        List<Organization> organizations = organizationRepository.findAll();
        if (organizations.isEmpty()) {
            throw new jakarta.persistence.EntityNotFoundException("Table Organization is empty!");
        }
        return organizations
                .stream()
                .map(organizationConverter::fromOrganizationToOrganizationDTO)
                .collect(Collectors.toList());
    }
}
