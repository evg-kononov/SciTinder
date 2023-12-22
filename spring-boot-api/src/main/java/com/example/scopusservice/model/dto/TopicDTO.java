package com.example.scopusservice.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TopicDTO {

    private Long num;
    private String name;
    @JsonProperty("prominence_percentile")
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
}
