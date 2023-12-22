package com.example.scopusservice.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicationAsjcDTO {
    @JsonProperty("asjc_code")
    private Long asjcCode;
    @JsonProperty("publication_id")
    private Long publicationId;
    @JsonProperty("asjc")
    private AsjcDTO asjcDTO;
    @JsonProperty("publication")
    private PublicationDTO publicationDTO;


    public Long getAsjcCode() {
        return asjcCode;
    }

    public void setAsjcCode(Long asjcCode) {
        this.asjcCode = asjcCode;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public AsjcDTO getAsjcDTO() {
        return asjcDTO;
    }

    public void setAsjcDTO(AsjcDTO asjcDTO) {
        this.asjcDTO = asjcDTO;
    }

    public PublicationDTO getPublicationDTO() {
        return publicationDTO;
    }

    public void setPublicationDTO(PublicationDTO publicationDTO) {
        this.publicationDTO = publicationDTO;
    }
}
