package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Date {
    @Id
    @Basic(optional = false)
    @Column(name = "year", nullable = false)
    private Long year;


    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year);
    }
}
