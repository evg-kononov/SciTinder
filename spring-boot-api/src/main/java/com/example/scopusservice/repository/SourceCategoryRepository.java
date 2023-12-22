package com.example.scopusservice.repository;

import com.example.scopusservice.model.entity.SourceCategory;
import com.example.scopusservice.model.entity.SourceCategoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceCategoryRepository extends JpaRepository<SourceCategory, SourceCategoryPK> {
    SourceCategory findByPublicationSourceId(Long publicationSourceId);
    SourceCategory findByCategoryId(Long categoryId);
    SourceCategory findByDateYear(Long dateYear);
}
