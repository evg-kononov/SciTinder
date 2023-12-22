package com.example.scopusservice.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class PublicationAuthorPK implements Serializable {
    @JsonProperty("publication_id")
    private Long publicationId;
    @JsonProperty("author_id")
    private Long authorId;

    public PublicationAuthorPK() {
    }

    public PublicationAuthorPK(Long publicationId, Long authorId) {
        this.publicationId = publicationId;
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationAuthorPK that = (PublicationAuthorPK) o;
        return publicationId.equals(that.publicationId) && authorId.equals(that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationId, authorId);
    }
}
