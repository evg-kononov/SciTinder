package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "publication_keyword", schema = "public", catalog = "scopus")
@IdClass(PublicationKeywordPK.class)
public class PublicationKeyword {
    @Id
    @Basic(optional = false)
    @Column(name = "publication_id", nullable = false)
    private Long publicationId;

    @Id
    @Basic(optional = false)
    @Column(name = "keyword_id", nullable = false)
    private Long keywordId;


    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Long getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationKeyword that = (PublicationKeyword) o;
        return publicationId.equals(that.publicationId) && keywordId.equals(that.keywordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationId, keywordId);
    }
}
