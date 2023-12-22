package com.example.scopusservice.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

public class AsjcDTO {

    private Long code;
    private String field;
    @JsonProperty("subject_area")
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
    public String toString() {
        return "AsjcDTO{" +
                "code=" + code +
                ", field='" + field + '\'' +
                ", subjectArea='" + subjectArea + '\'' +
                '}';
    }
}
