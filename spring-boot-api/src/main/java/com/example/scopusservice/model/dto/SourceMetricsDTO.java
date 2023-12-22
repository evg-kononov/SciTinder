package com.example.scopusservice.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SourceMetricsDTO {

    @JsonProperty("publication_source_id")
    private Long publicationSourceId;
    @JsonProperty("date_year")
    private Long dateYear;
    private Double snip;
    private Double sjr;
    @JsonProperty("cite_score")
    private Double citescore;
    @JsonProperty("sjr_best_quartile")
    private Short sjrBestQuartile;
    private Long h_index;


    public Long getPublicationSourceId() {
        return publicationSourceId;
    }

    public void setPublicationSourceId(Long publicationSourceId) {
        this.publicationSourceId = publicationSourceId;
    }

    public Long getDateYear() {
        return dateYear;
    }

    public void setDateYear(Long dateYear) {
        this.dateYear = dateYear;
    }

    public Double getSnip() {
        return snip;
    }

    public void setSnip(Double snip) {
        this.snip = snip;
    }

    public Double getSjr() {
        return sjr;
    }

    public void setSjr(Double sjr) {
        this.sjr = sjr;
    }

    public Double getCitescore() {
        return citescore;
    }

    public void setCitescore(Double citescore) {
        this.citescore = citescore;
    }

    public Short getSjrBestQuartile() {
        return sjrBestQuartile;
    }

    public void setSjrBestQuartile(Short sjrBestQuartile) {
        this.sjrBestQuartile = sjrBestQuartile;
    }

    public Long getH_index() {
        return h_index;
    }

    public void setH_index(Long h_index) {
        this.h_index = h_index;
    }
}
