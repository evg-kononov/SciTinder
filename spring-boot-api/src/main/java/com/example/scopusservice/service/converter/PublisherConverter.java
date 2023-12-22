package com.example.scopusservice.service.converter;

import com.example.scopusservice.model.dto.PublisherDTO;
import com.example.scopusservice.model.entity.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherConverter {
    public Publisher fromPublisherDTOToPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        return publisher;
    }

    public PublisherDTO fromPublisherToPublisherDTO(Publisher publisher) {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        return publisherDTO;
    }
}
