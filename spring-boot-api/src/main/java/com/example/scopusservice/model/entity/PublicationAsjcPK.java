package com.example.scopusservice.model.entity;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;


public class PublicationAsjcPK implements Serializable {
    private Long asjcCode;
    private Long publicationId;

    public PublicationAsjcPK() {
    }

    public PublicationAsjcPK(Long asjcCode, Long publicationId) {
        this.asjcCode = asjcCode;
        this.publicationId = publicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationAsjcPK that = (PublicationAsjcPK) o;
        return asjcCode.equals(that.asjcCode) && publicationId.equals(that.publicationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asjcCode, publicationId);
    }
}
