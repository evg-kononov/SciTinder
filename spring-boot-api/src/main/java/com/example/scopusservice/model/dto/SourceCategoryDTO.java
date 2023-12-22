package com.example.scopusservice.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SourceCategoryDTO {

    @JsonProperty("publication_source_id")
    private Long publicationSourceId;
    @JsonProperty("category_id")
    private Long categoryId;
    @JsonProperty("date_year")
    private Long dateYear;
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
}
