package com.example.scopusservice.repository;

import com.example.scopusservice.model.entity.Asjc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsjcRepository extends JpaRepository<Asjc, Long> {
}
