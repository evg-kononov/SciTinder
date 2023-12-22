package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Topic {
    @Id
    @Basic(optional = false)
    @Column(name = "num", nullable = false)
    private Long num;

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Basic(optional = false)
    @Column(name = "prominence_percentile", nullable = false)
    private Double prominencePercentile;


    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getProminencePercentile() {
        return prominencePercentile;
    }

    public void setProminencePercentile(Double prominencePercentile) {
        this.prominencePercentile = prominencePercentile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;
        Topic topic = (Topic) o;
        return num.equals(topic.num) && name.equals(topic.name) && prominencePercentile.equals(topic.prominencePercentile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, name, prominencePercentile);
    }
}
