package com.example.scopusservice.repository;

import com.example.scopusservice.model.entity.PublicationKeyword;
import com.example.scopusservice.model.entity.PublicationKeywordPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationKeywordRepository extends JpaRepository<PublicationKeyword, PublicationKeywordPK> {
    PublicationKeyword findByKeywordId(Long keywordId);
    PublicationKeyword findByPublicationId(Long publicationId);
}
