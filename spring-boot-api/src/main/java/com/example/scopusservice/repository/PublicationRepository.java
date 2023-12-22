package com.example.scopusservice.repository;

import com.example.scopusservice.model.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    @Query("select p from Publication p " +
            "left join fetch p.date " +
            "left join fetch p.topicCluster " +
            "left join fetch p.topic " +
            "left join fetch p.publicationSource " +
            "where p.name = ?1")
    List<Publication> findAllByName(String name);
}
