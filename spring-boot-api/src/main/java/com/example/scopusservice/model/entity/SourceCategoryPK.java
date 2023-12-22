package com.example.scopusservice.model.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class SourceCategoryPK implements Serializable {
    private Long publicationSourceId;
    private Long categoryId;
    private Long dateYear;


    public SourceCategoryPK(Long publicationSourceId, Long categoryId, Long dateYear) {
        this.publicationSourceId = publicationSourceId;
        this.categoryId = categoryId;
        this.dateYear = dateYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceCategoryPK that = (SourceCategoryPK) o;
        return publicationSourceId.equals(that.publicationSourceId) && categoryId.equals(that.categoryId) && dateYear.equals(that.dateYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationSourceId, categoryId, dateYear);
    }
}
