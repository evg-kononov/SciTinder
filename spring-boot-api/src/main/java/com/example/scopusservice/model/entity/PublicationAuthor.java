package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "publication_author", schema = "public", catalog = "scopus")
@IdClass(PublicationAuthorPK.class)
public class PublicationAuthor {
    @Id
    @Basic(optional = false)
    @Column(name = "publication_id", nullable = false)
    private Long publicationId;

    @Id
    @Basic(optional = false)
    @Column(name = "author_id", nullable = false)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationAuthor that = (PublicationAuthor) o;
        return publicationId.equals(that.publicationId) && authorId.equals(that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationId, authorId);
    }
}
