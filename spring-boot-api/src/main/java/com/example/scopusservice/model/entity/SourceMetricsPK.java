package com.example.scopusservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class SourceMetricsPK implements Serializable {
    private Long publicationSourceId;
    private Long dateYear;


    public SourceMetricsPK(Long publicationSourceId, Long dateYear) {
        this.publicationSourceId = publicationSourceId;
        this.dateYear = dateYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceMetricsPK that = (SourceMetricsPK) o;
        return publicationSourceId.equals(that.publicationSourceId) && dateYear.equals(that.dateYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationSourceId, dateYear);
    }
}
