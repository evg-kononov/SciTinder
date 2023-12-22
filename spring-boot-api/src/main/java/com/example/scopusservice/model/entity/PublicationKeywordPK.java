package com.example.scopusservice.model.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class PublicationKeywordPK implements Serializable {
    private Long publicationId;
    private Long keywordId;


    public PublicationKeywordPK(Long publicationId, Long keywordId) {
        this.publicationId = publicationId;
        this.keywordId = keywordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationKeywordPK that = (PublicationKeywordPK) o;
        return publicationId.equals(that.publicationId) && keywordId.equals(that.keywordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationId, keywordId);
    }
}
