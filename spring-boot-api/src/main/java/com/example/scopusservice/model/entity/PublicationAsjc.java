package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@IdClass(PublicationAsjcPK.class)
@Table(name = "publication_asjc", schema = "public", catalog = "scopus")
public class PublicationAsjc {
    @Id
    @Column(name = "asjc_code")
    private Long asjcCode;

    @Id
    @Column(name = "publication_id")
    private Long publicationId;

    @ManyToOne(targetEntity = Asjc.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "asjc_code", insertable = false, updatable = false)
    private Asjc asjc;

    @ManyToOne(targetEntity = Publication.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", insertable = false, updatable = false)
    private Publication publication;


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

    public Asjc getAsjc() {
        return asjc;
    }

    public void setAsjc(Asjc asjc) {
        this.asjc = asjc;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}
