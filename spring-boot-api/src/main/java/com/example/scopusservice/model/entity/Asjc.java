package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "asjc", schema = "public", catalog = "scopus")
public class Asjc {
    @Id
    @Basic(optional = false)
    @Column(name = "code", nullable = false)
    private Long code;

    @Basic(optional = false)
    @Column(name = "field", nullable = false, length = 255)
    private String field;

    @Basic(optional = false)
    @Column(name = "subject_area", nullable = false, length = 255)
    private String subjectArea;


    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(String subjectArea) {
        this.subjectArea = subjectArea;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asjc that = (Asjc) o;
        return Objects.equals(code, that.code) && Objects.equals(field, that.field) && Objects.equals(subjectArea, that.subjectArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, field, subjectArea);
    }
}
