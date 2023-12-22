package com.example.scopusservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthorDTO {

    private Long id;

    @NotEmpty(message = "AUTHOR name should not be empty!")
    @Size(min = 2, message = "AUTHOR name should have at least 2 characters")
    private String name;

    @JsonProperty("scopus_id")
    @Min(0)
    private Long scopusId;

    @JsonProperty("h_index")
    @Min(0)
    private Long hIndex;

    @JsonProperty("organization")
    private OrganizationDTO organizationDTO;


    public AuthorDTO(Long id, String name, Long scopusId, Long hIndex, OrganizationDTO organizationDTO) {
        this.id = id;
        this.name = name;
        this.scopusId = scopusId;
        this.hIndex = hIndex;
        this.organizationDTO = organizationDTO;
    }

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

    public Long getH_index() {
        return hIndex;
    }

    public void setH_index(Long hIndex) {
        this.hIndex = hIndex;
    }

    public OrganizationDTO getOrganizationDTO() {
        return organizationDTO;
    }

    public void setOrganizationDTO(OrganizationDTO organizationDTO) {
        this.organizationDTO = organizationDTO;
    }

    public static class Builder {
        private Long id;
        private String name;
        private Long scopusId;
        private Long hIndex;
        private OrganizationDTO organizationDTO;


        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withScopusId(Long scopusId) {
            this.scopusId = scopusId;
            return this;
        }

        public Builder withH_index(Long hIndex) {
            this.hIndex = hIndex;
            return this;
        }

        public Builder withOrganization(OrganizationDTO organizationDTO) {
            this.organizationDTO = organizationDTO;
            return this;
        }

        public AuthorDTO build() {
            return new AuthorDTO(id, name, scopusId, hIndex, organizationDTO);
        }
    }
}
