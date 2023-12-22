package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.PublicationDTO;
import com.example.scopusservice.model.entity.Publication;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class PublicationConverter {

    private final AsjcConverter asjcConverter;
    private final DateConverter dateConverter;
    private final TopicClusterConverter topicClusterConverter;
    private final TopicConverter topicConverter;
    private final PublicationSourceConverter publicationSourceConverter;

    public PublicationConverter(AsjcConverter asjcConverter, DateConverter dateConverter, TopicClusterConverter topicClusterConverter, TopicConverter topicConverter, PublicationSourceConverter publicationSourceConverter) {
        this.asjcConverter = asjcConverter;
        this.dateConverter = dateConverter;
        this.topicClusterConverter = topicClusterConverter;
        this.topicConverter = topicConverter;
        this.publicationSourceConverter = publicationSourceConverter;
    }

    public Publication fromPublicationDTOToPublication(PublicationDTO publicationDTO) {
        Publication publication = new Publication();
        publication.setId(publicationDTO.getId());
        publication.setName(publicationDTO.getName());
        publication.setType(publicationDTO.getType());
        publication.setAbstr(publicationDTO.getAbstr());
        publication.setEnglish(publicationDTO.getEnglish());
        publication.setPagesNum(publicationDTO.getPagesNum());
        publication.setDoi(publicationDTO.getDoi());
        publication.setEid(publicationDTO.getEid());
        publication.setPubmedId(publicationDTO.getPubmedId());
        publication.setViewsNum(publicationDTO.getViewsNum());
        publication.setCitationsNum(publicationDTO.getCitationsNum());
        publication.setOpenAccess(publicationDTO.getOpenAccess());
        publication.setCorrespondenceAddress(publicationDTO.getCorrespondenceAddress());
        if (!isNull(publicationDTO.getDateDTO()))
            publication.setDate(dateConverter.fromDateDTOToDate(publicationDTO.getDateDTO()));
        if (!isNull(publicationDTO.getTopicClusterDTO()))
            publication.setTopicCluster(topicClusterConverter.fromTopicClusterDTOToTopicCluster(publicationDTO.getTopicClusterDTO()));
        if (!isNull(publicationDTO.getTopicDTO()))
            publication.setTopic(topicConverter.fromTopicDTOToTopic(publicationDTO.getTopicDTO()));
        if (!isNull(publicationDTO.getPublicationSourceDTO()))
            publication.setPublicationSource(publicationSourceConverter.fromPublicationSourceDTOToPublicationSource(publicationDTO.getPublicationSourceDTO()));
//        publication.setAsjcs(
//                publicationDTO.getAsjcsDTO()
//                        .stream()
//                        .map(asjcConverter::fromAsjcDTOToAsjc)
//                        .collect(Collectors.toSet()));
        return publication;
    }

    public PublicationDTO fromPublicationToPublicationDTO(Publication publication) {
        PublicationDTO publicationDTO = new PublicationDTO.Builder()
                .withId(publication.getId())
                .withName(publication.getName())
                .withType(publication.getType())
                .withAbstr(publication.getAbstr())
                .withEnglish(publication.getEnglish())
                .withPagesNum(publication.getPagesNum())
                .withDoi(publication.getDoi())
                .withEid(publication.getEid())
                .withPubmedId(publication.getPubmedId())
                .withViewsNum(publication.getViewsNum())
                .withCitationsNum(publication.getCitationsNum())
                .withOpenAccess(publication.getOpenAccess())
                .withCorrespondenceAddress(publication.getCorrespondenceAddress())
                .build();
        if (!isNull(publication.getDate()))
            publicationDTO.setDateDTO(dateConverter.fromDateToDateDTO(publication.getDate()));
        if (!isNull(publication.getTopicCluster()))
            publicationDTO.setTopicClusterDTO(topicClusterConverter.fromTopicClusterToTopicClusterDTO(publication.getTopicCluster()));
        if (!isNull(publication.getTopic()))
            publicationDTO.setTopicDTO(topicConverter.fromTopicToTopicDTO(publication.getTopic()));
        if (!isNull(publication.getPublicationSource()))
            publicationDTO.setPublicationSourceDTO(publicationSourceConverter.fromPublicationSourceToPublicationSourceDTO(publication.getPublicationSource()));
//        publicationDTO.setAsjcsDTO(publication.getAsjcs()
//                .stream()
//                .map(asjcConverter::fromAsjcToAsjcDTO)
//                .collect(Collectors.toSet()));
        return publicationDTO;
    }
}
