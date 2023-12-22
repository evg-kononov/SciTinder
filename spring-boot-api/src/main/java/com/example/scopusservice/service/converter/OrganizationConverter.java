package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.OrganizationDTO;
import com.example.scopusservice.model.entity.Organization;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class OrganizationConverter {

    private final CountryConverter countryConverter;
    private final SectorConverter sectorConverter;

    public OrganizationConverter(CountryConverter countryConverter, SectorConverter sectorConverter) {
        this.countryConverter = countryConverter;
        this.sectorConverter = sectorConverter;
    }

    public Organization fromOrganizationDTOToOrganization(OrganizationDTO organizationDTO) {
        Organization organization = new Organization();
        organization.setId(organizationDTO.getId());
        organization.setName(organizationDTO.getName());
        organization.setScopusId(organizationDTO.getScopusId());
        if (!isNull(organizationDTO.getCountryDTO()))
            organization.setCountry(countryConverter.fromCountryDTOToCountry(organizationDTO.getCountryDTO()));
        if (!isNull(organizationDTO.getSectorDTO()))
            organization.setSector(sectorConverter.fromSectorDTOToSector(organizationDTO.getSectorDTO()));
        return organization;
    }

    public OrganizationDTO fromOrganizationToOrganizationDTO(Organization organization) {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        organizationDTO.setScopusId(organization.getScopusId());
        if (!isNull(organization.getCountry()))
            organizationDTO.setCountryDTO(countryConverter.fromCountryToCountryDTO(organization.getCountry()));
        if (!isNull(organization.getSector()))
            organizationDTO.setSectorDTO(sectorConverter.fromSectorToSectorDTO(organization.getSector()));
        return organizationDTO;
    }
}
