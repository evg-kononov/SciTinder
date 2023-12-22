package com.example.scopusservice.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicationAuthorDTO {

    @JsonProperty("publication_id")
    private Long publicationId;
    @JsonProperty("author_id")
    private Long authorId;


    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
