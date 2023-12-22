package com.example.scopusservice.model.dto;


import com.example.scopusservice.model.entity.Publisher;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicationSourceDTO {

    private Long id;
    private String name;
    private String type;
    private String issn;
    @JsonProperty("scopus_id")
    private Long scopusId;
    @JsonProperty("publisher")
    private PublisherDTO publisherDTO;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public Long getScopusId() {
        return scopusId;
    }

    public void setScopusId(Long scopusId) {
        this.scopusId = scopusId;
    }

    public PublisherDTO getPublisherDTO() {
        return publisherDTO;
    }

    public void setPublisherDTO(PublisherDTO publisherDTO) {
        this.publisherDTO = publisherDTO;
    }
}
