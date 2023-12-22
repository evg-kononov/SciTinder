package com.example.scopusservice.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class PublicationDTO {

    private Long id;
    @Size(min = 2, message = "PUBLICATION name should have at least 2 characters")
    private String name;

    private String type;

    @JsonProperty("abstract")
    private String abstr;

    private Boolean english;

    @JsonProperty("pages_num")
    private Integer pagesNum;

    private String doi;

    private String eid;

    @JsonProperty("pubmed_id")
    private Long pubmedId;

    @JsonProperty("views_num")
    private Long viewsNum;

    @JsonProperty("citations_num")
    private Long citationsNum;

    @JsonProperty("open_access")
    private String openAccess;

    @JsonProperty("correspondence_address")
    private String correspondenceAddress;

    @JsonProperty("date")
    private DateDTO dateDTO;

    @JsonProperty("topic_cluster")
    private TopicClusterDTO topicClusterDTO;

    @JsonProperty("topic")
    private TopicDTO topicDTO;

    @JsonProperty("publication_source")
    private PublicationSourceDTO publicationSourceDTO;


    public PublicationDTO(Long id, String name, String type, String abstr, Boolean english, Integer pagesNum, String doi, String eid, Long pubmedId, Long viewsNum, Long citationsNum, String openAccess, String correspondenceAddress, DateDTO dateDTO, TopicClusterDTO topicClusterDTO, TopicDTO topicDTO, PublicationSourceDTO publicationSourceDTO) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.abstr = abstr;
        this.english = english;
        this.pagesNum = pagesNum;
        this.doi = doi;
        this.eid = eid;
        this.pubmedId = pubmedId;
        this.viewsNum = viewsNum;
        this.citationsNum = citationsNum;
        this.openAccess = openAccess;
        this.correspondenceAddress = correspondenceAddress;
        this.dateDTO = dateDTO;
        this.topicClusterDTO = topicClusterDTO;
        this.topicDTO = topicDTO;
        this.publicationSourceDTO = publicationSourceDTO;
    }

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

    public DateDTO getDateDTO() {
        return dateDTO;
    }

    public void setDateDTO(DateDTO dateDTO) {
        this.dateDTO = dateDTO;
    }

    public TopicClusterDTO getTopicClusterDTO() {
        return topicClusterDTO;
    }

    public void setTopicClusterDTO(TopicClusterDTO topicClusterDTO) {
        this.topicClusterDTO = topicClusterDTO;
    }

    public TopicDTO getTopicDTO() {
        return topicDTO;
    }

    public void setTopicDTO(TopicDTO topicDTO) {
        this.topicDTO = topicDTO;
    }

    public PublicationSourceDTO getPublicationSourceDTO() {
        return publicationSourceDTO;
    }

    public void setPublicationSourceDTO(PublicationSourceDTO publicationSourceDTO) {
        this.publicationSourceDTO = publicationSourceDTO;
    }


    public static class Builder {
        private Long id;
        private String name;
        private String type;
        private String abstr;
        private Boolean english;
        private Integer pagesNum;
        private String doi;
        private String eid;
        private Long pubmedId;
        private Long viewsNum;
        private Long citationsNum;
        private String openAccess;
        private String correspondenceAddress;
        private DateDTO dateDTO;
        private TopicClusterDTO topicClusterDTO;
        private TopicDTO topicDTO;
        private PublicationSourceDTO publicationSourceDTO;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withAbstr(String abstr) {
            this.abstr = abstr;
            return this;
        }

        public Builder withEnglish(Boolean english) {
            this.english = english;
            return this;
        }

        public Builder withPagesNum(Integer pagesNum) {
            this.pagesNum = pagesNum;
            return this;
        }

        public Builder withDoi(String doi) {
            this.doi = doi;
            return this;
        }

        public Builder withEid(String eid) {
            this.eid = eid;
            return this;
        }

        public Builder withPubmedId(Long pubmedId) {
            this.pubmedId = pubmedId;
            return this;
        }

        public Builder withViewsNum(Long viewsNum) {
            this.viewsNum = viewsNum;
            return this;
        }

        public Builder withCitationsNum(Long citationsNum) {
            this.citationsNum = citationsNum;
            return this;
        }

        public Builder withOpenAccess(String openAccess) {
            this.openAccess = openAccess;
            return this;
        }

        public Builder withCorrespondenceAddress(String correspondenceAddress) {
            this.correspondenceAddress = correspondenceAddress;
            return this;
        }

        public Builder withDate(DateDTO dateDTO) {
            this.dateDTO = dateDTO;
            return this;
        }

        public Builder withTopicCluster(TopicClusterDTO topicClusterDTO) {
            this.topicClusterDTO = topicClusterDTO;
            return this;
        }

        public Builder withTopic(TopicDTO topicDTO) {
            this.topicDTO = topicDTO;
            return this;
        }

        public Builder withPublicationSource(PublicationSourceDTO publicationSourceDTO) {
            this.publicationSourceDTO = publicationSourceDTO;
            return this;
        }

        public PublicationDTO build() {
            return new PublicationDTO(id, name, type, abstr, english, pagesNum, doi, eid, pubmedId, viewsNum, citationsNum, openAccess, correspondenceAddress, dateDTO, topicClusterDTO, topicDTO, publicationSourceDTO);
        }
    }
}
