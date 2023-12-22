package com.example.scopusservice.repository;

import com.example.scopusservice.model.entity.PublicationSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationSourceRepository extends JpaRepository<PublicationSource, Long> {
    @Query("select p from PublicationSource p left join fetch p.publisher where p.name = ?1")
    List<PublicationSource> findAllByName(String name);
    PublicationSource findByScopusId(Long scopusId);
    PublicationSource findByIssn(String issn);
}
