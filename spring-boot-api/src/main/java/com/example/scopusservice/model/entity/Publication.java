package com.example.scopusservice.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Publication {
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @SequenceGenerator(
            name = "publication_id_seq",
            sequenceName = "publication_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "publication_id_seq"
    )
    private Long id;

    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", length = 255)
    private String type;

    @Column(name = "abstract")
    private String abstr;

    @Column(name = "english")
    private Boolean english;

    @Column(name = "pages_num")
    private Integer pagesNum;

    @Column(name = "doi", length = 255)
    private String doi;

    @Column(name = "eid", unique = true, length = 255)
    private String eid;

    @Column(name = "pubmed_id")
    private Long pubmedId;

    @Column(name = "views_num")
    private Long viewsNum;

    @Column(name = "citations_num")
    private Long citationsNum;

    @Column(name = "open_access", length = 255)
    private String openAccess;

    @Column(name = "correspondence_address")
    private String correspondenceAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date_year")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_cluster_num")
    private TopicCluster topicCluster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_num")
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_source_id")
    private PublicationSource publicationSource;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbstr() {
        return abstr;
    }

    public void setAbstr(String abstr) {
        this.abstr = abstr;
    }

    public Boolean getEnglish() {
        return english;
    }

    public void setEnglish(Boolean english) {
        this.english = english;
    }

    public Integer getPagesNum() {
        return pagesNum;
    }

    public void setPagesNum(Integer pagesNum) {
        this.pagesNum = pagesNum;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public Long getPubmedId() {
        return pubmedId;
    }

    public void setPubmedId(Long pubmedId) {
        this.pubmedId = pubmedId;
    }

    public Long getViewsNum() {
        return viewsNum;
    }

    public void setViewsNum(Long viewsNum) {
        this.viewsNum = viewsNum;
    }

    public Long getCitationsNum() {
        return citationsNum;
    }

    public void setCitationsNum(Long citationsNum) {
        this.citationsNum = citationsNum;
    }

    public String getOpenAccess() {
        return openAccess;
    }

    public void setOpenAccess(String openAccess) {
        this.openAccess = openAccess;
    }

    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TopicCluster getTopicCluster() {
        return topicCluster;
    }

    public void setTopicCluster(TopicCluster topicCluster) {
        this.topicCluster = topicCluster;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public PublicationSource getPublicationSource() {
        return publicationSource;
    }

    public void setPublicationSource(PublicationSource publicationSource) {
        this.publicationSource = publicationSource;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publication)) return false;
        Publication that = (Publication) o;
        return id.equals(that.id) && name.equals(that.name) && Objects.equals(type, that.type) && Objects.equals(abstr, that.abstr) && Objects.equals(english, that.english) && Objects.equals(pagesNum, that.pagesNum) && Objects.equals(doi, that.doi) && Objects.equals(eid, that.eid) && Objects.equals(pubmedId, that.pubmedId) && Objects.equals(viewsNum, that.viewsNum) && Objects.equals(citationsNum, that.citationsNum) && Objects.equals(openAccess, that.openAccess) && Objects.equals(correspondenceAddress, that.correspondenceAddress) && Objects.equals(date, that.date) && Objects.equals(topicCluster, that.topicCluster) && Objects.equals(topic, that.topic) && Objects.equals(publicationSource, that.publicationSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, abstr, english, pagesNum, doi, eid, pubmedId, viewsNum, citationsNum, openAccess, correspondenceAddress, date, topicCluster, topic, publicationSource);
    }
}
