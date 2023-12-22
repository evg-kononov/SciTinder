package com.example.scopusservice.repository;

import com.example.scopusservice.model.entity.SourceMetrics;
import com.example.scopusservice.model.entity.SourceMetricsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceMetricsRepository extends JpaRepository<SourceMetrics, SourceMetricsPK> {
    SourceMetrics findByPublicationSourceId(Long publicationSourceId);
    SourceMetrics findByDateYear(Long dateYear);
}
