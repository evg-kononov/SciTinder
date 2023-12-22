package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.TopicDTO;
import com.example.scopusservice.model.entity.Topic;
import org.springframework.stereotype.Component;

@Component
public class TopicConverter {
    public Topic fromTopicDTOToTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setNum(topicDTO.getNum());
        topic.setName(topicDTO.getName());
        topic.setProminencePercentile(topicDTO.getProminencePercentile());
        return topic;
    }

    public TopicDTO fromTopicToTopicDTO(Topic topic) {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setNum(topic.getNum());
        topicDTO.setName(topic.getName());
        topicDTO.setProminencePercentile(topic.getProminencePercentile());
        return topicDTO;
    }
}
