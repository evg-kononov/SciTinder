package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "publication_source", schema = "public", catalog = "scopus")
public class PublicationSource {
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @SequenceGenerator(
            name = "publication_source_id_seq",
            sequenceName = "publication_source_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "publication_source_id_seq"
    )
    private Long id;

    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", length = 255)
    private String type;

    @Column(name = "issn", length = 255)
    private String issn;

    @Column(name = "scopus_id", unique = true)
    private Long scopusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicationSource)) return false;
        PublicationSource that = (PublicationSource) o;
        return id.equals(that.id) && name.equals(that.name) && Objects.equals(type, that.type) && Objects.equals(issn, that.issn) && Objects.equals(scopusId, that.scopusId) && Objects.equals(publisher, that.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, issn, scopusId, publisher);
    }
}
