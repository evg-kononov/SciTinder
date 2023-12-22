package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "source_category", schema = "public", catalog = "scopus")
@IdClass(SourceCategoryPK.class)
public class SourceCategory {
    @Id
    @Basic(optional = false)
    @Column(name = "publication_source_id", nullable = false)
    private Long publicationSourceId;

    @Id
    @Basic(optional = false)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Id
    @Basic(optional = false)
    @Column(name = "date_year", nullable = false)
    private Long dateYear;

    @Column(name = "quartile")
    private Short quartile;


    public Long getPublicationSourceId() {
        return publicationSourceId;
    }

    public void setPublicationSourceId(Long publicationSourceId) {
        this.publicationSourceId = publicationSourceId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getDateYear() {
        return dateYear;
    }

    public void setDateYear(Long dateYear) {
        this.dateYear = dateYear;
    }

    public Short getQuartile() {
        return quartile;
    }

    public void setQuartile(Short quartile) {
        this.quartile = quartile;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceCategory that = (SourceCategory) o;
        return publicationSourceId.equals(that.publicationSourceId) && categoryId.equals(that.categoryId) && dateYear.equals(that.dateYear) && Objects.equals(quartile, that.quartile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationSourceId, categoryId, dateYear, quartile);
    }
}
