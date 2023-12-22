package com.example.scopusservice.repository;

import com.example.scopusservice.model.entity.TopicCluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicClusterRepository extends JpaRepository<TopicCluster, Long> {
    TopicCluster findByName(String name);
}
