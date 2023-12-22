package com.example.scopusservice.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class OrganizationDTO {

    private Long id;
    private String name;
    @JsonProperty("scopus_id")
    private Long scopusId;
    @JsonProperty("country")
    private CountryDTO countryDTO;
    @JsonProperty("sector")
    private SectorDTO sectorDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getScopusId() {
        return scopusId;
    }

    public void setScopusId(Long scopusId) {
        this.scopusId = scopusId;
    }

    public CountryDTO getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }

    public SectorDTO getSectorDTO() {
        return sectorDTO;
    }

    public void setSectorDTO(SectorDTO sectorDTO) {
        this.sectorDTO = sectorDTO;
    }
}
