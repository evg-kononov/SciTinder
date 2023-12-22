package com.example.scopusservice.repository;

import com.example.scopusservice.model.entity.PublicationAuthor;
import com.example.scopusservice.model.entity.PublicationAuthorPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationAuthorRepository extends JpaRepository<PublicationAuthor, PublicationAuthorPK> {
    List<PublicationAuthor> findAllByAuthorId(Long authorId);
    List<PublicationAuthor> findAllByPublicationId(Long publicationId);
}
