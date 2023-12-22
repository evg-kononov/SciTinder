package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.TopicClusterDTO;
import com.example.scopusservice.model.entity.TopicCluster;
import org.springframework.stereotype.Component;

@Component
public class TopicClusterConverter {
    public TopicCluster fromTopicClusterDTOToTopicCluster(TopicClusterDTO topicClusterDTO) {
        TopicCluster topicCluster = new TopicCluster();
        topicCluster.setNum(topicClusterDTO.getNum());
        topicCluster.setName(topicClusterDTO.getName());
        topicCluster.setProminencePercentile(topicClusterDTO.getProminencePercentile());
        return topicCluster;
    }

    public TopicClusterDTO fromTopicClusterToTopicClusterDTO(TopicCluster topicCluster) {
        TopicClusterDTO topicClusterDTO = new TopicClusterDTO();
        topicClusterDTO.setNum(topicCluster.getNum());
        topicClusterDTO.setName(topicCluster.getName());
        topicClusterDTO.setProminencePercentile(topicCluster.getProminencePercentile());
        return topicClusterDTO;
    }
}
