package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "source_metrics", schema = "public", catalog = "scopus")
@IdClass(SourceMetricsPK.class)
public class SourceMetrics {
    @Id
    @Basic(optional = false)
    @Column(name = "publication_source_id", nullable = false)
    private Long publicationSourceId;

    @Id
    @Basic(optional = false)
    @Column(name = "date_year", nullable = false)
    private Long dateYear;

    @Column(name = "snip")
    private Double snip;

    @Column(name = "sjr")
    private Double sjr;

    @Column(name = "citescore")
    private Double citescore;

    @Column(name = "sjr_best_quartile", nullable = true)
    private Short sjrBestQuartile;

    @Column(name = "h_index")
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SourceMetrics)) return false;
        SourceMetrics that = (SourceMetrics) o;
        return publicationSourceId.equals(that.publicationSourceId) && dateYear.equals(that.dateYear) && Objects.equals(snip, that.snip) && Objects.equals(sjr, that.sjr) && Objects.equals(citescore, that.citescore) && Objects.equals(sjrBestQuartile, that.sjrBestQuartile) && Objects.equals(h_index, that.h_index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationSourceId, dateYear, snip, sjr, citescore, sjrBestQuartile, h_index);
    }
}
